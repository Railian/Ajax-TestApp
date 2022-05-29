package ua.railian.ajax.testapp.presentation.compose.ui.contact.list

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import ua.railian.ajax.testapp.domain.contract.entity.Contact
import ua.railian.ajax.testapp.presentation.compose.theme.AjaxTestAppTheme

@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@Composable
fun ContactList(
    contacts: List<Contact>,
    modifier: Modifier = Modifier,
    isRefreshing: Boolean = false,
    onRefresh: (() -> Unit)? = null,
    onContactClick: ((Contact) -> Unit),
    onContactDelete: ((Contact) -> Unit)
) {
    val refreshState = rememberSwipeRefreshState(isRefreshing)

    SwipeRefresh(
        state = refreshState,
        onRefresh = { onRefresh?.invoke() },
    ) {
        LazyColumn(
            modifier = Modifier.then(modifier),
            contentPadding = PaddingValues(all = 8.dp)
        ) {
            items(contacts, key = Contact::id) {
                ContactItem(
                    contact = it,
                    modifier = Modifier.animateItemPlacement(),
                    onClick = onContactClick,
                    onDelete = onContactDelete
                )
            }
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun Preview() {
    AjaxTestAppTheme {
        Surface {
            ContactList(
                listOf(
                    stubContact.copy(
                        id = 1,
                        firstName = "Yevhen",
                        lastName = "Railian",
                        email = "yevhen.railian@mail.ua"
                    ),
                    stubContact.copy(
                        id = 2,
                        firstName = "Dmitriy",
                        lastName = "Lekar",
                        email = "dmitry.lekar@mail.ua"
                    ),
                    stubContact.copy(
                        id = 3,
                        firstName = "Alia",
                        lastName = "Railian",
                        email = "alia.railian@mail.ua"
                    )
                ),
                onContactClick = {},
                onContactDelete = {}
            )
        }
    }
}