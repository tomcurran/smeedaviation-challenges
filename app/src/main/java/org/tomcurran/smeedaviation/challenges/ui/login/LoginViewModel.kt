package org.tomcurran.smeedaviation.challenges.ui.login

import android.app.Activity
import android.app.Application
import android.content.Intent
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import net.openid.appauth.*
import net.openid.appauth.connectivity.DefaultConnectionBuilder
import org.tomcurran.smeedaviation.challenges.R
import org.tomcurran.smeedaviation.challenges.util.AuthStateManager
import org.tomcurran.smeedaviation.challenges.util.Event

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    companion object {
        private const val CLIENT_SECRET = "test"

        private const val CLIENT_ID = "49167"
        private const val SCOPE = "activity:read_all"
        private const val AUTH_ENDPOINT = "https://www.strava.com/oauth/mobile/authorize"
        private const val TOKEN_ENDPOINT = "https://www.strava.com/api/v3/oauth/token"
        private const val REDIRECT_URI = "challenges.smeedaviation.tomcurran.org://auth"
    }

    private val _authStateManager = AuthStateManager.getInstance(getApplication())

    private var _authService : AuthorizationService? = null

    private val _launchIntent = MutableLiveData<Event<Intent>>()
    val launchIntent: LiveData<Event<Intent>>
        get() = _launchIntent

    private val _navigateToMain = MutableLiveData<Event<Unit>>()
    val navigateToMain: LiveData<Event<Unit>> = _navigateToMain

    init {
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
                        _navigateToMain.value = Event(Unit)
                    }
                }
            }
        }
    }
}