package ua.railian.ajax.testapp.presentation.compose.ui.contact.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DeleteContactAlert(
    onDeleteRequest: () -> Unit,
    onDismissRequest: () -> Unit
) {
    AlertDialog(
        title = { Text(text = "Are you sure?") },
        text = { Text(text = "Do you want to delete this contact?") },
        buttons = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        end = 12.dp,
                        bottom = 4.dp
                    ),
                horizontalArrangement = Arrangement.End
            ) {
                TextButton(
                    onClick = onDismissRequest,
                    content = { Text(text = "CANCEL") }
                )
                TextButton(
                    onClick = onDeleteRequest,
                    content = { Text(text = "DELETE") }
                )
            }
        },
        onDismissRequest = onDismissRequest
    )
}