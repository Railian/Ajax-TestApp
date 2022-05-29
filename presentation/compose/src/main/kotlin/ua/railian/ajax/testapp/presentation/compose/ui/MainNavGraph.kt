package ua.railian.ajax.testapp.presentation.compose.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import ua.railian.ajax.testapp.presentation.compose.ui.contact.details.ContactDetailsScreen
import ua.railian.ajax.testapp.presentation.compose.ui.contact.list.ContactListScreen

@Composable
fun MainNavGraph(
    navController: NavHostController = rememberNavController()
) {

    var darkTheme by remember { mutableStateOf(false) }

    NavHost(navController = navController, startDestination = "contacts") {
        composable(route = "contacts") {
            ContactListScreen(
                darkTheme = darkTheme,
                onThemeToggle = { darkTheme = !darkTheme },
                onContactClick = { navController.navigate(route = "contact/${it.id}") }
            )
        }
        composable(
            route = "contact/{id}",
            arguments = listOf(navArgument("id") { type = NavType.LongType })
        ) {
            ContactDetailsScreen(
                darkTheme = darkTheme,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}