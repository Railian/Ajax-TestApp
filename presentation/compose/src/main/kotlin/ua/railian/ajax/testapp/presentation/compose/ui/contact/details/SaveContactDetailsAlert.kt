package ua.railian.ajax.testapp.presentation.compose.ui.contact.details

import androidx.compose.foundation.layout.*
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SaveContactDetailsAlert(
    onSaveRequest: () -> Unit,
    onDiscardRequest: () -> Unit,
    onDismissRequest: () -> Unit
) {
    AlertDialog(
        title = { Text(text = "Save changes") },
        text = { Text(text = "You are editing this contact right now. Do you want to save changes before you go?") },
        buttons = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 12.dp,
                        end = 12.dp,
                        bottom = 4.dp
                    ),
                horizontalArrangement = Arrangement.End
            ) {
                TextButton(
                    onClick = onDiscardRequest,
                    content = { Text(text = "DISCARD") }
                )
                Spacer(modifier = Modifier.weight(1f))
                TextButton(
                    onClick = onDismissRequest,
                    content = { Text(text = "CANCEL") }
                )
                TextButton(
                    onClick = onSaveRequest,
                    content = { Text(text = "SAVE") }
                )
            }
        },
        onDismissRequest = onDismissRequest
    )
}