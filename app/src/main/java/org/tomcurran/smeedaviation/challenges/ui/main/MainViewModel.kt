package org.tomcurran.smeedaviation.challenges.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import okhttp3.OkHttpClient
import org.openapitools.client.apis.ActivitiesApi
import org.openapitools.client.infrastructure.ApiClient
import org.openapitools.client.infrastructure.ClientException
import org.openapitools.client.models.ActivityType
import org.openapitools.client.models.SummaryActivity
import org.tomcurran.smeedaviation.challenges.util.AuthStateManager
import org.tomcurran.smeedaviation.challenges.util.Event
import java.net.HttpURLConnection
import java.time.Duration
import java.time.Instant
import java.time.Month
import java.time.OffsetDateTime

class MainViewModel(application: Application) : AndroidViewModel(application) {

    companion object {
        private const val STRAVA_PAGE_SIZE_MAX = 200
        private const val STRAVA_ONE_MILE_BEST_EFFORT_NAME = "1 mile"
    }

    private val _authStateManager = AuthStateManager.getInstance(getApplication())
    private val _activitiesApi = ActivitiesApi()

    private val _fastestOneMileRunJune = MutableLiveData<String>("loading...")
    val fastestOneMileRunJune: LiveData<String> = _fastestOneMileRunJune

    private val _fastestOneMileRunJuly = MutableLiveData<String>("loading...")
    val fastestOneMileRunJuly: LiveData<String> = _fastestOneMileRunJuly

    private val _navigateToLogin = MutableLiveData<Event<Unit>>()
    val navigateToLogin: LiveData<Event<Unit>> = _navigateToLogin

    init {
        ApiClient.builder = OkHttpClient.Builder().authenticator(TokenAuthenticator(application))
        ApiClient.accessToken = _authStateManager.current.accessToken
        if (!_authStateManager.current.isAuthorized) {
            _navigateToLogin.value = Event(Unit)
        } else {
            load()
        }
    }

    private fun load() {
        viewModelScope.launch {
            try {
                val instantNearEndOfMay = OffsetDateTime.parse("2020-05-29T00:00:00+00:00").toInstant()
                val instantNearStartOfAugust = OffsetDateTime.parse("2020-08-02T00:00:00+00:00").toInstant()
                val activities = getActivitySummaries(instantNearEndOfMay, instantNearStartOfAugust)
                val juneActivities = activities.filter { it.startDateLocal?.month == Month.JUNE }
                val julyActivities = activities.filter { it.startDateLocal?.month == Month.JULY }

                val fastestOneMileRunJuneDeferred = async {
                    _fastestOneMileRunJune.value = getBestEffortDuration(
                        juneActivities,
                        ActivityType.run,
                        STRAVA_ONE_MILE_BEST_EFFORT_NAME
                    )
                }

                val fastestOneMileRunJulyDeferred = async {
                    _fastestOneMileRunJuly.value = getBestEffortDuration(
                        julyActivities,
                        ActivityType.run,
                        STRAVA_ONE_MILE_BEST_EFFORT_NAME
                    )
                }

                awaitAll(fastestOneMileRunJuneDeferred, fastestOneMileRunJulyDeferred)
            } catch (clientException: ClientException) {
                if (clientException.statusCode == HttpURLConnection.HTTP_UNAUTHORIZED) {
                    _navigateToLogin.value = Event(Unit)
                } else {
                    unhandledError();
                }
            } catch (e: Exception) {
                unhandledError();
            }
        }
    }

    private fun unhandledError() {
        _fastestOneMileRunJune.value = "error"
        _fastestOneMileRunJuly.value = "error"
    }

    private suspend fun getBestEffortDuration(
        activitySummaries: List<SummaryActivity>,
        activityType: ActivityType,
        effortName: String
    ): String {
        try {
            val activityIds = activitySummaries
                .filter { it.type == activityType }
                .mapNotNull { it.id }

            val fastestOneMileInJune = getActivitiesDetails(activityIds).minBy {
                it.bestEfforts?.firstOrNull { effort -> effort.name == effortName }?.movingTime
                    ?: Int.MAX_VALUE
            }

            val oneMileSeconds =
                fastestOneMileInJune?.bestEfforts?.firstOrNull { effort -> effort.name == effortName }?.movingTime

            return if (oneMileSeconds != null && oneMileSeconds != Int.MAX_VALUE) {
                val oneMileDuration = Duration.ofSeconds(oneMileSeconds.toLong())
                String.format(
                    "%d min, %d sec",
                    oneMileDuration.toMinutes(),
                    oneMileDuration.minusMinutes(oneMileDuration.toMinutes()).seconds
                )
            } else {
                "not yet completed"
            }
        } catch (e: Exception) {
            return "error"
        }
    }

    private suspend fun getActivitySummaries(after: Instant, before: Instant) =
        withContext(Dispatchers.IO) {
            val activitySummaries = mutableListOf<SummaryActivity>()
            var page = 0
            do {
                page++
                val activitySummaryPage = _activitiesApi.getLoggedInAthleteActivities(
                    before.epochSecond.toInt(),
                    after.epochSecond.toInt(),
                    page,
                    STRAVA_PAGE_SIZE_MAX
                )
                activitySummaries.addAll(activitySummaryPage)
            } while (activitySummaryPage.isNotEmpty())
            activitySummaries
        }

    private suspend fun getActivitiesDetails(activityIds: List<Long>) =
        withContext(Dispatchers.IO) {
            activityIds
                .map { async { _activitiesApi.getActivityById(it, false) } }
                .awaitAll()
        }
}