package ua.railian.ajax.testapp.presentation.compose.ui.main

import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
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
    viewModel: MainViewModel = hiltViewModel<MainViewModelImpl>(),
    navController: NavHostController = rememberNavController()
) {

    val dayNightMode by viewModel.dayNightMode.collectAsState()

    NavHost(navController = navController, startDestination = "contacts") {
        composable(route = "contacts") {
            ContactListScreen(
                dayNightMode = dayNightMode,
                onDayNightModeChange = viewModel::changeDayNightMode,
                onContactClick = { navController.navigate(route = "contact/${it.id}") }
            )
        }
        composable(
            route = "contact/{id}",
            arguments = listOf(navArgument("id") { type = NavType.LongType })
        ) {
            ContactDetailsScreen(
                dayNightMode = dayNightMode,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}