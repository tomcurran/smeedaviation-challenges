package org.tomcurran.smeedaviation.challenges.ui.main

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import net.openid.appauth.*
import net.openid.appauth.connectivity.DefaultConnectionBuilder
import org.tomcurran.smeedaviation.challenges.R
import org.tomcurran.smeedaviation.challenges.databinding.MainFragmentBinding

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()

        const val CLIENT_SECRET = "test"

        const val AUTH_REQUEST_CODE = 0
        const val CLIENT_ID = "49167"
        const val SCOPE = "activity:read_all"
        const val AUTH_ENDPOINT = "https://www.strava.com/oauth/mobile/authorize"
        const val TOKEN_ENDPOINT = "https://www.strava.com/api/v3/oauth/token"
        const val REDIRECT_URI = "challenges.smeedaviation.tomcurran.org://auth"
    }

    private val viewModel: MainViewModel by lazy {
        ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    private var authService : AuthorizationService? = null
    private lateinit var authStateManager: AuthStateManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val binding = MainFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        authStateManager = AuthStateManager.getInstance(requireActivity())

        binding.button.setOnClickListener {

            val authState = AuthState(AuthorizationServiceConfiguration(AUTH_ENDPOINT.toUri(), TOKEN_ENDPOINT.toUri()))
            authStateManager.replace(authState)

            val authRequest = AuthorizationRequest.Builder(
                authStateManager.current.authorizationServiceConfiguration!!,
                CLIENT_ID,
                ResponseTypeValues.CODE,
                REDIRECT_URI.toUri()
            ).setScope(SCOPE).build()

            val appAuthConfiguration = AppAuthConfiguration.Builder()
                .setConnectionBuilder(DefaultConnectionBuilder.INSTANCE)
                .build()
            authService = AuthorizationService(requireActivity(), appAuthConfiguration)

            val authIntent = authService!!.createCustomTabsIntentBuilder(authRequest.toUri())
                .setToolbarColor(ContextCompat.getColor(requireActivity(), R.color.colorPrimary))
                .build()
            val intent = authService!!.getAuthorizationRequestIntent(authRequest, authIntent)
            startActivityForResult(intent, AUTH_REQUEST_CODE)
        }

        return binding.root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == AUTH_REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null) {

            val response = AuthorizationResponse.fromIntent(data)
            val ex = AuthorizationException.fromIntent(data)

            if (response != null || ex != null) {
                authStateManager.updateAfterAuthorization(response, ex)
                if (response != null) {
                    authService!!.performTokenRequest(
                        response.createTokenExchangeRequest(mapOf("client_secret" to CLIENT_SECRET)),
                        authStateManager.current.clientAuthentication
                    ) { tokenResponse, tokenEx ->
                        authStateManager.updateAfterTokenResponse(tokenResponse, tokenEx)
                    }
                }
            }
        }
    }
}