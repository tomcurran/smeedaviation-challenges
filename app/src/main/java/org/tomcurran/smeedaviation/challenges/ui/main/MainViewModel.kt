package org.tomcurran.smeedaviation.challenges.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import org.openapitools.client.apis.ActivitiesApi
import org.openapitools.client.infrastructure.ApiClient
import org.openapitools.client.models.ActivityType
import org.openapitools.client.models.SummaryActivity
import java.lang.Exception
import java.time.*

class MainViewModel : ViewModel() {

    companion object {
        private const val STRAVA_PAGE_SIZE_MAX = 200
    }

    private val _activitiesApi : ActivitiesApi

    private val _fastestOneMileRun = MutableLiveData<String>()
    val fastestOneMileRun: LiveData<String>
        get() = _fastestOneMileRun

    init {
        _fastestOneMileRun.value = "loading..."
        ApiClient.accessToken = "406cf72f7e225de1b3e63e2e70866db2c78882bd"
        _activitiesApi = ActivitiesApi()
        load()
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