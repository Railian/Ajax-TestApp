package ua.railian.ajax.testapp.presentation.compose.ui.contact.list

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import ua.railian.ajax.testapp.domain.contract.entity.Contact
import ua.railian.ajax.testapp.presentation.compose.theme.AjaxTestAppTheme
import ua.railian.ajax.testapp.domain.contract.entity.DayNightMode
import ua.railian.ajax.testapp.presentation.compose.extension.toggle

@Composable
fun ContactListScreen(
    viewModel: ContactListViewModel = hiltViewModel<ContactListViewModelImpl>(),
    dayNightMode: DayNightMode,
    onDayNightModeChange: (DayNightMode) -> Unit,
    onContactClick: (Contact) -> Unit
) {
    AjaxTestAppTheme(
        darkTheme = when (dayNightMode) {
            DayNightMode.Day -> false
            DayNightMode.Night -> true
            DayNightMode.System -> isSystemInDarkTheme()
        }
    ) {

        val systemUiController = rememberSystemUiController()
        val primarySurface = MaterialTheme.colors.primarySurface

        SideEffect { systemUiController.setStatusBarColor(primarySurface) }

        Scaffold(
            topBar = {
                TopBar(
                    title = viewModel.title,
                    dayNightMode = dayNightMode,
                    onDayNightModeChange = onDayNightModeChange
                )
            },
            content = {
                val contacts by viewModel.contacts.collectAsState(initial = emptyList())
                val areContactsRefreshing by viewModel.areContactsRefreshing.collectAsState()
                ContactList(
                    contacts = contacts,
                    modifier = Modifier.fillMaxSize(),
                    isRefreshing = areContactsRefreshing,
                    onRefresh = viewModel::refreshContactsAsync,
                    onContactDelete = { viewModel.deleteContactAsync(it.id) },
                    onContactClick = onContactClick
                )
            })
    }
}

@Composable
private fun TopBar(
    title: String,
    dayNightMode: DayNightMode,
    onDayNightModeChange: (DayNightMode) -> Unit
) {
    TopAppBar(
        title = { Text(text = title) },
        actions = {
            Text(
                text = "Mode",
                style = MaterialTheme.typography.button
            )
            Spacer(modifier = Modifier.width(4.dp))
            IconButton(
                onClick = { onDayNightModeChange(dayNightMode.toggle) },
                content = {
                    Icon(
                        imageVector = when (dayNightMode) {
                            DayNightMode.Day -> Icons.Default.LightMode
                            DayNightMode.Night -> Icons.Default.DarkMode
                            DayNightMode.System -> Icons.Default.BrightnessAuto
                        },
                        contentDescription = "Toggle day/night mode"
                    )
                }
            )
        }
    )
}
