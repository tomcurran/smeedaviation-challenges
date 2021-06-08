package org.tomcurran.smeedaviation.challenges.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.tomcurran.smeedaviation.challenges.ui.login.LoginScreen
import org.tomcurran.smeedaviation.challenges.ui.main.MainScreen

object MainDestinations {
    const val MAIN_ROUTE = "main"
    const val LOGIN_ROUTE = "login"
}

@Composable
fun ChallengesNavGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: String = MainDestinations.MAIN_ROUTE,
) {
    val actions = remember(navController) { MainActions(navController) }
    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        composable(MainDestinations.MAIN_ROUTE) {
            MainScreen(actions.navigateToLogin)
        }
        composable(MainDestinations.LOGIN_ROUTE) {
            LoginScreen(actions.navigateToMain)
        }
    }
}

class MainActions(navController: NavHostController) {
    val navigateToMain: () -> Unit = {
        navController.navigate(MainDestinations.MAIN_ROUTE) {
            popUpTo(MainDestinations.LOGIN_ROUTE) { inclusive = true }
        }
    }
    val navigateToLogin: () -> Unit = {
        navController.navigate(MainDestinations.LOGIN_ROUTE) {
            popUpTo(MainDestinations.MAIN_ROUTE) { inclusive = true }
        }
    }
}