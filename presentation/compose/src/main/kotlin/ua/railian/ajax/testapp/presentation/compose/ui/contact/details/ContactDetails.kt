package ua.railian.ajax.testapp.presentation.compose.ui.contact.details

import android.content.res.Configuration
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import ua.railian.ajax.testapp.domain.contract.entity.Contact
import ua.railian.ajax.testapp.presentation.compose.extension.color
import ua.railian.ajax.testapp.presentation.compose.theme.AjaxTestAppTheme
import ua.railian.ajax.testapp.presentation.compose.ui.contact.list.stubContact

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ContactDetails(
    contact: Contact,
    editable: Boolean,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    verticalScrollState: ScrollState = rememberScrollState(),
    imageHeight: Dp = 240.dp,
    firstNameLabel: String? = null,
    lastNameLabel: String? = null,
    emailLabel: String? = null,
    accentColor: Color = contact.color,
    onContactChange: (Contact) -> Unit
) {

    val keyboardController = LocalSoftwareKeyboardController.current
    SideEffect { if (!editable) keyboardController?.hide() }

    Column(
        modifier = modifier
            .verticalScroll(verticalScrollState)
            .padding(contentPadding)
    ) {

        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(imageHeight),
            contentDescription = "Contact image",
            model = contact.picture.large ?: contact.picture.medium,
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(16.dp))

        ContactDetailsItem(
            editable = editable,
            label = firstNameLabel,
            value = contact.firstName,
            onValueChange = { onContactChange(contact.copy(firstName = it)) },
            accentColor = accentColor
        )

        ContactDetailsItem(
            editable = editable,
            label = lastNameLabel,
            value = contact.lastName,
            onValueChange = { onContactChange(contact.copy(lastName = it)) },
            accentColor = accentColor
        )

        ContactDetailsItem(
            editable = editable,
            label = emailLabel,
            value = contact.email,
            onValueChange = { onContactChange(contact.copy(email = it)) },
            accentColor = accentColor
        )

        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ContactDetailsPreview() {
    AjaxTestAppTheme {
        Surface {
            ContactDetails(
                contact = stubContact.copy(
                    firstName = "Yevhen",
                    lastName = "Railian",
                    email = "yevhen.railian@mail.ua"
                ),
                firstNameLabel = "First name",
                lastNameLabel = "Last name",
                emailLabel = "Email",
                editable = false,
                onContactChange = {}
            )
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun EditContactDetailsPreview() {
    AjaxTestAppTheme {
        Surface {
            ContactDetails(
                contact = stubContact.copy(
                    firstName = "Yevhen",
                    lastName = "Railian",
                    email = "yevhen.railian@mail.ua"
                ),
                firstNameLabel = "First name",
                lastNameLabel = "Last name",
                emailLabel = "Email",
                editable = true,
                onContactChange = {}
            )
        }
    }
}