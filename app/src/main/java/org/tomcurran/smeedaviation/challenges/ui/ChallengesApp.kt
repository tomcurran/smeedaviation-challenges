package org.tomcurran.smeedaviation.challenges.ui

import androidx.compose.runtime.Composable
import org.tomcurran.smeedaviation.challenges.ui.theme.ChallengesTheme

@Composable
fun ChallengesApp() {
    ChallengesTheme {
        ChallengesNavGraph()
    }
}