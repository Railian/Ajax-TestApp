package ua.railian.ajax.testapp.presentation.compose.ui.contact.details

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import ua.railian.ajax.testapp.presentation.compose.extension.color
import ua.railian.ajax.testapp.presentation.compose.theme.AjaxTestAppTheme
import ua.railian.ajax.testapp.presentation.compose.ui.contact.accentSurface
import ua.railian.ajax.testapp.domain.contract.entity.DayNightMode

@Composable
fun ContactDetailsScreen(
    viewModel: ContactDetailsViewModel = hiltViewModel<ContactDetailsViewModelImpl>(),
    dayNightMode: DayNightMode,
    onBackClick: () -> Unit
) {

    AjaxTestAppTheme(
        darkTheme = when (dayNightMode) {
            DayNightMode.Day -> false
            DayNightMode.Night -> true
            DayNightMode.System -> isSystemInDarkTheme()
        }
    ) {

        val title = viewModel.title.collectAsState(initial = null).value.orEmpty()
        val contactState = viewModel.contact.collectAsState(initial = null)
        val contact = contactState.value ?: return@AjaxTestAppTheme

        var editable by remember { mutableStateOf(false) }
        var showSaveAlert by remember { mutableStateOf(false) }
        var showDeleteAlert by remember { mutableStateOf(false) }

        val systemUiController = rememberSystemUiController()
        val accentColor = remember(editable) { contact.color }
        val accentSurface = MaterialTheme.colors.accentSurface(accentColor)

        SideEffect { systemUiController.setStatusBarColor(color = accentSurface) }

        val backHandler = {
            when (editable && !showSaveAlert) {
                true -> showSaveAlert = true
                else -> onBackClick()
            }
        }

        BackHandler(onBack = backHandler)

        Scaffold(
            topBar = {
                ContactDetailsTopBar(
                    title = title,
                    backgroundColor = accentSurface,
                    onBackClick = backHandler,
                    onDeleteClick = { showDeleteAlert = true }
                )
            },
            content = {
                ContactDetails(
                    modifier = Modifier
                        .fillMaxSize(),
                    contact = contact,
                    editable = editable,
                    contentPadding = PaddingValues(bottom = 72.dp),
                    firstNameLabel = "First name",
                    lastNameLabel = "Last name",
                    emailLabel = "Email",
                    accentColor = accentColor,
                    onContactChange = viewModel::onContactChanged
                )
            },
            floatingActionButton = {
                EditSaveFloatingButton(editable = editable,
                    backgroundColor = accentColor,
                    onEdit = { editable = true },
                    onSave = {
                        viewModel.saveContactAsync()
                        editable = false
                    })
            })

        if (showSaveAlert) SaveContactDetailsAlert(
            onSaveRequest = {
                viewModel.saveContactAsync()
                onBackClick()
            },
            onDiscardRequest = { onBackClick() },
            onDismissRequest = { showSaveAlert = false }
        )

        if (showDeleteAlert) DeleteContactAlert(
            onDeleteRequest = {
                viewModel.deleteContactAsync()
                onBackClick()
            },
            onDismissRequest = { showDeleteAlert = false }
        )
    }
}

@Composable
private fun ContactDetailsTopBar(
    title: String,
    backgroundColor: Color,
    contentColor: Color = if (backgroundColor.luminance() > .5f) Color.Black else Color.White,
    onBackClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    TopAppBar(
        navigationIcon = {
            IconButton(
                onClick = onBackClick,
                content = {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Go back"
                    )
                }
            )
        },
        title = { Text(text = title) },
        actions = {
            IconButton(
                onClick = onDeleteClick,
                content = {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete contact"
                    )
                }
            )
        },
        backgroundColor = backgroundColor,
        contentColor = contentColor
    )
}

@Composable
fun EditSaveFloatingButton(
    editable: Boolean,
    backgroundColor: Color,
    contentColor: Color = if (backgroundColor.luminance() > .5) Color.Black else Color.White,
    onEdit: () -> Unit,
    onSave: () -> Unit
) {
    FloatingActionButton(
        onClick = {
            when (editable) {
                true -> onSave()
                else -> onEdit()
            }
        },
        backgroundColor = backgroundColor,
        contentColor = contentColor,
        content = {
            Icon(
                imageVector = when (editable) {
                    true -> Icons.Default.Save
                    else -> Icons.Default.Edit
                },
                contentDescription = when (editable) {
                    true -> "Save contact"
                    else -> "Edit contact"
                }
            )
        }
    )
}