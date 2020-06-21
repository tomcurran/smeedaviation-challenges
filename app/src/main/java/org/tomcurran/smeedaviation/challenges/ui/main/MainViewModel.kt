package org.tomcurran.smeedaviation.challenges.ui.main

import android.app.Activity
import android.app.Application
import android.content.Intent
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import net.openid.appauth.*
import net.openid.appauth.connectivity.DefaultConnectionBuilder
import org.openapitools.client.apis.ActivitiesApi
import org.openapitools.client.infrastructure.ApiClient
import org.openapitools.client.models.ActivityType
import org.openapitools.client.models.SummaryActivity
import org.tomcurran.smeedaviation.challenges.R
import org.tomcurran.smeedaviation.challenges.util.AuthStateManager
import org.tomcurran.smeedaviation.challenges.util.Event
import java.lang.Exception
import java.time.*

class MainViewModel(application: Application) : AndroidViewModel(application) {

    companion object {
        private const val STRAVA_PAGE_SIZE_MAX = 200

        private const val CLIENT_SECRET = "test"

        private const val CLIENT_ID = "49167"
        private const val SCOPE = "activity:read_all"
        private const val AUTH_ENDPOINT = "https://www.strava.com/oauth/mobile/authorize"
        private const val TOKEN_ENDPOINT = "https://www.strava.com/api/v3/oauth/token"
        private const val REDIRECT_URI = "challenges.smeedaviation.tomcurran.org://auth"
    }

    private val _authStateManager : AuthStateManager
    private val _activitiesApi : ActivitiesApi

    private var _authService : AuthorizationService? = null

    private val _launchIntent = MutableLiveData<Event<Intent>>()
    val launchIntent: LiveData<Event<Intent>>
        get() = _launchIntent

    private val _fastestOneMileRun = MutableLiveData<String>()
    val fastestOneMileRun: LiveData<String>
        get() = _fastestOneMileRun

    init {
        _fastestOneMileRun.value = "loading..."
        _authStateManager = AuthStateManager.getInstance(getApplication())
        ApiClient.accessToken = _authStateManager.current.accessToken
        _activitiesApi = ActivitiesApi()
        load()
    }

    fun auth() {
        val authState = AuthState(AuthorizationServiceConfiguration(AUTH_ENDPOINT.toUri(), TOKEN_ENDPOINT.toUri()))
        _authStateManager.replace(authState)

        val authRequest = AuthorizationRequest.Builder(
            _authStateManager.current.authorizationServiceConfiguration!!,
            CLIENT_ID,
            ResponseTypeValues.CODE,
            REDIRECT_URI.toUri()
        ).setScope(SCOPE).build()

        val appAuthConfiguration = AppAuthConfiguration.Builder()
            .setConnectionBuilder(DefaultConnectionBuilder.INSTANCE)
            .build()
        _authService = AuthorizationService(getApplication(), appAuthConfiguration)

        val authIntent = _authService!!.createCustomTabsIntentBuilder(authRequest.toUri())
            .setToolbarColor(ContextCompat.getColor(getApplication(), R.color.colorPrimary))
            .build()
        val intent = _authService!!.getAuthorizationRequestIntent(authRequest, authIntent)
        _launchIntent.value = Event(intent)
    }

    fun processAuth(resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && data != null) {
            val response = AuthorizationResponse.fromIntent(data)
            val ex = AuthorizationException.fromIntent(data)

            if (response != null || ex != null) {
                _authStateManager.updateAfterAuthorization(response, ex)
                if (response != null) {
                    _authService!!.performTokenRequest(
                        response.createTokenExchangeRequest(mapOf("client_secret" to CLIENT_SECRET)),
                        _authStateManager.current.clientAuthentication
                    ) { tokenResponse, tokenEx ->
                        _authStateManager.updateAfterTokenResponse(tokenResponse, tokenEx)
                    }
                }
            }
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