package org.tomcurran.smeedaviation.challenges.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
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

    private val _authStateManager : AuthStateManager
    private val _activitiesApi : ActivitiesApi

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
        viewModelScope.launch(Dispatchers.IO) {
            try {
                var activitiesNearJune = getActivitySummariesNearJune()
                val detailedRunsInJune = activitiesNearJune
                    .filter { it.type == ActivityType.run }
                    .filter { it.startDateLocal?.month == Month.JUNE }
                    .map { async { _activitiesApi.getActivityById(it.id!!, false) } }
                    .awaitAll()
//                    .filter { it.bestEfforts?.any { effort -> effort.name == "1 mile" } ?: false }

                val fastestOneMileRunInJune = detailedRunsInJune.minBy {
                    it.bestEfforts?.firstOrNull { effort -> effort.name == "1 mile" }?.movingTime ?: Int.MAX_VALUE
                }

                var oneMileSeconds = fastestOneMileRunInJune?.bestEfforts?.firstOrNull { effort -> effort.name == "1 mile" }?.movingTime

                if (oneMileSeconds != null && oneMileSeconds != Int.MAX_VALUE) {
                    val oneMileDuration = Duration.ofSeconds(oneMileSeconds.toLong())
                    val oneMileTimeString = String.format(
                        "%d min, %d sec",
                        oneMileDuration.toMinutes(),
                        oneMileDuration.minusMinutes(oneMileDuration.toMinutes()).seconds
                    )
                    viewModelScope.launch {
                        _fastestOneMileRun.value = oneMileTimeString
                    }
                } else {
                    viewModelScope.launch {
                        _fastestOneMileRun.value = "not yet completed"
                    }
                }
            } catch (e: Exception) {
                viewModelScope.launch {
                    _fastestOneMileRun.value = "error"
                }
            }
        }
    }

    private fun getActivitySummariesNearJune(): List<SummaryActivity> {
        val instantNearEndOfMay = OffsetDateTime.parse("2020-05-29T00:00:00+00:00").toInstant()
        val instantNearStartOfJuly = OffsetDateTime.parse("2020-07-02T00:00:00+00:00").toInstant()
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
        return activitySummariesNearJune
    }
}