package org.tomcurran.smeedaviation.challenges.ui.main

import android.app.Application
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import org.tomcurran.smeedaviation.challenges.R
import org.tomcurran.smeedaviation.challenges.ui.theme.ChallengesTheme
import org.tomcurran.smeedaviation.challenges.util.EventObserver

@Composable
fun MainScreen(
    navigateToMain: () -> Unit,
    viewModel: MainViewModel = viewModel(
        modelClass = MainViewModel::class.java,
        factory = MainViewModelProviderFactory(LocalContext.current.applicationContext as Application)
    )
) {
    val runJuneTime: String by viewModel.fastestOneMileRunJune.observeAsState("")
    val runJulyTime: String by viewModel.fastestOneMileRunJuly.observeAsState("")
    viewModel.navigateToLogin.observe(
        LocalLifecycleOwner.current,
        EventObserver { navigateToMain() })
    MainContent(runJuneTime, runJulyTime)
}

@Composable
fun MainContent(runJuneTime: String, runJulyTime: String) {
    val typography = MaterialTheme.typography
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.app_name)) },
            )
        },
        content = {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = stringResource(R.string.fastest_running_mile_during_june),
                    modifier = Modifier.padding(8.dp),
                    style = typography.body1,
                )
                Text(
                    text = runJuneTime,
                    modifier = Modifier.padding(8.dp),
                    style = typography.body1,
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = stringResource(R.string.fastest_running_mile_during_july),
                    modifier = Modifier.padding(8.dp),
                    style = typography.body1,
                )
                Text(
                    text = runJulyTime,
                    modifier = Modifier.padding(8.dp),
                    style = typography.body1,
                )
            }
        },
    )
}

@Preview("Main screen")
@Preview("Main screen (dark)", uiMode = UI_MODE_NIGHT_YES)
@Preview("Main screen (big font)", fontScale = 1.5f)
@Preview("Main screen (large screen)", device = Devices.PIXEL_C)
@Composable
fun DefaultPreview() {
    ChallengesTheme {
        MainContent("10 min, 01 sec", "10 min, 01 sec")
    }
}