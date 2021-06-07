package org.tomcurran.smeedaviation.challenges.ui.login

import android.app.Activity
import android.app.Application
import android.content.Intent
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import net.openid.appauth.*
import net.openid.appauth.connectivity.DefaultConnectionBuilder
import org.tomcurran.smeedaviation.challenges.BuildConfig.STRAVA_API_CLIENT_SECRET
import org.tomcurran.smeedaviation.challenges.BuildConfig.STRAVA_PACKAGE_NAME
import org.tomcurran.smeedaviation.challenges.R
import org.tomcurran.smeedaviation.challenges.util.AuthStateManager
import org.tomcurran.smeedaviation.challenges.util.Event
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    companion object {
        private const val CLIENT_ID = "66591"
        private const val SCOPE = "activity:read_all"
        private const val AUTH_ENDPOINT = "https://www.strava.com/oauth/mobile/authorize"
        private const val TOKEN_ENDPOINT = "https://www.strava.com/api/v3/oauth/token"
        private const val REDIRECT_URI = "challenges.smeedaviation.tomcurran.org://auth"

        private const val AUTH_REQUEST_CODE = 0
    }

    data class StartActivityForResult(
        val intent: Intent,
        val requestCode: Int
    )

    private val _authStateManager = AuthStateManager.getInstance(getApplication())
    private var _authService = AuthorizationService(getApplication())

    private val _startActivityForResult = MutableLiveData<Event<StartActivityForResult>>()
    val startActivityForResult: LiveData<Event<StartActivityForResult>> = _startActivityForResult

    private val _navigateToMain = MutableLiveData<Event<Unit>>()
    val navigateToMain: LiveData<Event<Unit>> = _navigateToMain

    private val _loggingIn = MutableLiveData<Boolean>()
    val loggingIn: LiveData<Boolean> = _loggingIn

    init {
        _loggingIn.value = false
    }

    fun login() {
        _loggingIn.value = true
        viewModelScope.launch(Dispatchers.Default) {
            val authState = AuthState(
                AuthorizationServiceConfiguration(
                    AUTH_ENDPOINT.toUri(),
                    TOKEN_ENDPOINT.toUri()
                )
            )
            _authStateManager.replace(authState)

            val authRequest = AuthorizationRequest.Builder(
                _authStateManager.current.authorizationServiceConfiguration!!,
                CLIENT_ID,
                ResponseTypeValues.CODE,
                REDIRECT_URI.toUri()
            ).setScope(SCOPE).build()

            val authRequestIntent = if (isPackageInstalled(STRAVA_PACKAGE_NAME)) {
                AuthorizationManagementActivity.createStartForResultIntent(
                    getApplication(),
                    authRequest,
                    Intent(Intent.ACTION_VIEW, authRequest.toUri())
                )
            } else {
                _authService.getAuthorizationRequestIntent(
                    authRequest,
                    _authService.createCustomTabsIntentBuilder(authRequest.toUri())
                        .setToolbarColor(
                            ContextCompat.getColor(
                                getApplication(),
                                R.color.colorPrimary
                            )
                        )
                        .build()
                )
            }

            withContext(Dispatchers.Main) {
                _startActivityForResult.value =
                    Event(StartActivityForResult(authRequestIntent, AUTH_REQUEST_CODE))
            }
        }
    }

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        viewModelScope.launch(Dispatchers.IO) {
            if (requestCode == AUTH_REQUEST_CODE) {
                if (resultCode == Activity.RESULT_OK && data != null) {
                    val response = AuthorizationResponse.fromIntent(data)
                    val ex = AuthorizationException.fromIntent(data)

                    if (response != null || ex != null) {
                        _authStateManager.updateAfterAuthorization(response, ex)
                        if (response != null) {
                            val tokenRequest =
                                response.createTokenExchangeRequest(mapOf("client_secret" to STRAVA_API_CLIENT_SECRET))
                            val (tokenResponse, tokenEx) = suspendCoroutine<Pair<TokenResponse?, AuthorizationException?>> { continuation ->
                                _authService.performTokenRequest(
                                    tokenRequest, _authStateManager.current.clientAuthentication
                                ) { tokenResponse, tokenEx ->
                                    continuation?.resume(Pair(tokenResponse, tokenEx))
                                }
                            }
                            _authStateManager.updateAfterTokenResponse(tokenResponse, tokenEx)
                            withContext(Dispatchers.Main) {
                                _navigateToMain.value = Event(Unit)
                            }
                        }
                    }
                }

                withContext(Dispatchers.Main) {
                    _loggingIn.value = false
                }
            }
        }
    }

    private fun isPackageInstalled(packageName: String): Boolean {
        return try {
            getApplication<Application>().packageManager.getApplicationInfo(packageName, 0).enabled
        } catch (e: PackageManager.NameNotFoundException) {
            false
        }
    }
}