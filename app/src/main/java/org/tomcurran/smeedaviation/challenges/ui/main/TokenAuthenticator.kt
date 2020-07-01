package org.tomcurran.smeedaviation.challenges.ui.main

import android.app.Application
import kotlinx.coroutines.runBlocking
import net.openid.appauth.AuthorizationException
import net.openid.appauth.AuthorizationService
import net.openid.appauth.TokenResponse
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import org.tomcurran.smeedaviation.challenges.BuildConfig
import org.tomcurran.smeedaviation.challenges.util.AuthStateManager
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class TokenAuthenticator(private val application: Application) : Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? = runBlocking {
        val authStateManager = AuthStateManager.getInstance(application)

        var accessToken = authStateManager.current.accessToken
        var authorizationHeader = response.request.headers["Authorization"]

        if (accessToken != null && authorizationHeader != null
            && authorizationHeader.contains(accessToken)
        ) {
            val authService = AuthorizationService(application)
            val tokenRequest =
                authStateManager.current.createTokenRefreshRequest(mapOf("client_secret" to BuildConfig.STRAVA_API_CLIENT_SECRET))
            val (tokenResponse, tokenEx) = suspendCoroutine<Pair<TokenResponse?, AuthorizationException?>> { continuation ->
                authService.performTokenRequest(
                    tokenRequest, authStateManager.current.clientAuthentication
                ) { tokenResponse, tokenEx ->
                    continuation?.resume(Pair(tokenResponse, tokenEx))
                }
            }
            authStateManager.updateAfterTokenResponse(tokenResponse, tokenEx)
            if (tokenEx != null) {
                return@runBlocking null
            }
        }

        accessToken = authStateManager.current.accessToken
        authorizationHeader = response.request.headers["Authorization"]

        if (accessToken == null ||
            (authorizationHeader != null && authorizationHeader.contains(accessToken))
        ) {
            return@runBlocking null
        }

        return@runBlocking response.request.newBuilder()
            .header("Authorization", "Bearer ${authStateManager.current.accessToken} ")
            .build();
    }
}