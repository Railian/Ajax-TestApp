package ua.railian.ajax.testapp.presentation.compose.ui.contact.list

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import ua.railian.ajax.testapp.domain.contract.entity.Contact
import ua.railian.ajax.testapp.presentation.compose.theme.AjaxTestAppTheme

@Composable
fun ContactListScreen(
    viewModel: ContactListViewModel = hiltViewModel<ContactListViewModelImpl>(),
    darkTheme: Boolean = isSystemInDarkTheme(),
    onThemeToggle: () -> Unit,
    onContactClick: (Contact) -> Unit
) {
    AjaxTestAppTheme(darkTheme) {

        val systemUiController = rememberSystemUiController()
        val primarySurface = MaterialTheme.colors.primarySurface

        SideEffect { systemUiController.setStatusBarColor(primarySurface) }

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = viewModel.title) },
                    actions = {
                        IconButton(
                            onClick = onThemeToggle,
                            content = {
                                Icon(
                                    imageVector = when (MaterialTheme.colors.isLight) {
                                        true -> Icons.Default.DarkMode
                                        else -> Icons.Default.LightMode
                                    },
                                    contentDescription = "Toggle theme"
                                )
                            }
                        )
                    }
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