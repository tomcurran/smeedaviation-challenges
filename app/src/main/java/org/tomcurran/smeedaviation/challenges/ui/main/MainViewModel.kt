package org.tomcurran.smeedaviation.challenges.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import org.openapitools.client.apis.ActivitiesApi
import org.openapitools.client.infrastructure.ApiClient
import org.openapitools.client.models.ActivityType
import org.openapitools.client.models.SummaryActivity
import org.tomcurran.smeedaviation.challenges.util.AuthStateManager
import org.tomcurran.smeedaviation.challenges.util.Event
import java.time.Duration
import java.time.Month
import java.time.OffsetDateTime

class MainViewModel(application: Application) : AndroidViewModel(application) {

    companion object {
        private const val STRAVA_PAGE_SIZE_MAX = 200
    }

    private val _authStateManager: AuthStateManager
    private val _activitiesApi: ActivitiesApi

    private val _fastestOneMileRun = MutableLiveData<String>()
    val fastestOneMileRun: LiveData<String> = _fastestOneMileRun

    private val _navigateToLogin = MutableLiveData<Event<Unit>>()
    val navigateToLogin: LiveData<Event<Unit>> = _navigateToLogin

    init {
        _fastestOneMileRun.value = "loading..."
        _authStateManager = AuthStateManager.getInstance(getApplication())
        ApiClient.accessToken = _authStateManager.current.accessToken
        _activitiesApi = ActivitiesApi()
        if (!_authStateManager.current.isAuthorized) {
            _navigateToLogin.value = Event(Unit)
        } else {
            load()
        }
    }

    private fun load() {
        viewModelScope.launch {
            try {
                val juneRunIds = getActivitySummariesNearJune()
                    .filter { it.type == ActivityType.run }
                    .filter { it.startDateLocal?.month == Month.JUNE }
                    .mapNotNull { it.id }

                val fastestOneMileRunInJune = getActivitiesDetails(juneRunIds).minBy {
                    it.bestEfforts?.firstOrNull { effort -> effort.name == "1 mile" }?.movingTime
                        ?: Int.MAX_VALUE
                }

                val oneMileSeconds =
                    fastestOneMileRunInJune?.bestEfforts?.firstOrNull { effort -> effort.name == "1 mile" }?.movingTime

                if (oneMileSeconds != null && oneMileSeconds != Int.MAX_VALUE) {
                    val oneMileDuration = Duration.ofSeconds(oneMileSeconds.toLong())
                    val oneMileTimeString = String.format(
                        "%d min, %d sec",
                        oneMileDuration.toMinutes(),
                        oneMileDuration.minusMinutes(oneMileDuration.toMinutes()).seconds
                    )
                    _fastestOneMileRun.value = oneMileTimeString
                } else {
                    _fastestOneMileRun.value = "not yet completed"
                }
            } catch (e: Exception) {
                _fastestOneMileRun.value = "error"
            }
        }
    }

    private suspend fun getActivitySummariesNearJune() =
        withContext(Dispatchers.IO) {
            val instantNearEndOfMay = OffsetDateTime.parse("2020-05-29T00:00:00+00:00").toInstant()
            val instantNearStartOfJuly =
                OffsetDateTime.parse("2020-07-02T00:00:00+00:00").toInstant()
            val activitySummariesNearJune = mutableListOf<SummaryActivity>()
            var page = 0
            do {
                page++
                val activitySummaries = _activitiesApi.getLoggedInAthleteActivities(
                    instantNearStartOfJuly.epochSecond.toInt(),
                    instantNearEndOfMay.epochSecond.toInt(),
                    page,
                    STRAVA_PAGE_SIZE_MAX
                )
                activitySummariesNearJune.addAll(activitySummaries)
            } while (activitySummaries.isNotEmpty())
            activitySummariesNearJune
        }

    private suspend fun getActivitiesDetails(activityIds: List<Long>) =
        withContext(Dispatchers.IO) {
            activityIds
                .map { async { _activitiesApi.getActivityById(it, false) } }
                .awaitAll()
        }
}