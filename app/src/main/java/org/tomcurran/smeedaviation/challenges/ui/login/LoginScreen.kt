package org.tomcurran.smeedaviation.challenges.ui.login

import android.app.Application
import android.content.res.Configuration
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import org.tomcurran.smeedaviation.challenges.R
import org.tomcurran.smeedaviation.challenges.ui.theme.ChallengesTheme
import org.tomcurran.smeedaviation.challenges.util.EventObserver

@Composable
fun LoginScreen(
    navigateToLogin: () -> Unit,
    viewModel: LoginViewModel = androidx.lifecycle.viewmodel.compose.viewModel(
        modelClass = LoginViewModel::class.java,
        factory = LoginViewModelProviderFactory(LocalContext.current.applicationContext as Application)
    )
) {
    val loggingIn: Boolean by viewModel.loggingIn.observeAsState(false)
    viewModel.navigateToMain.observe(
        LocalLifecycleOwner.current,
        EventObserver { navigateToLogin() })
    val activityResultLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { activityResult ->
        viewModel.onActivityResult(activityResult)
    }
    viewModel.startActivityForResult.observe(LocalLifecycleOwner.current, EventObserver { intent ->
        try {
            activityResultLauncher.launch(intent)
        } catch (e: Exception) {
            // intentionally left blank - onActivityResult will handle the failure
        }
    })
    LoginContent(loggingIn) { viewModel.login() }
}

@Composable
fun LoginContent(loggingIn: Boolean, onLoginClick: () -> Unit) {
    Scaffold(
        topBar = {
            val title = stringResource(R.string.app_name)
            TopAppBar(
                title = { Text(title) },
            )
        },
        content = {
            Box(Modifier.fillMaxSize()) {
                Button(
                    enabled = !loggingIn,
                    onClick = onLoginClick,
                    modifier = Modifier
                        .align(Alignment.Center)
                ) {
                    Text(stringResource(R.string.login))
                }
            }
        }
    )
}

@Preview("Login screen")
@Preview("Login screen (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview("Login screen (big font)", fontScale = 1.5f)
@Preview("Login screen (large screen)", device = Devices.PIXEL_C)
@Composable
fun DefaultPreview() {
    ChallengesTheme {
        LoginContent(false) {}
    }
}