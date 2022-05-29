package ua.railian.ajax.testapp.presentation.compose.ui.contact.details

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class, ExperimentalComposeUiApi::class)
@Composable
fun ContactDetailsItem(
    editable: Boolean,
    label: String?,
    value: String,
    onValueChange: (String) -> Unit,
    accentColor: Color = MaterialTheme.colors.primary
) {

    val verticalPadding by animateDpAsState(
        targetValue = if (editable) 8.dp else 0.dp,
        animationSpec = tween(durationMillis = 600)
    )

    val backgroundColor by animateColorAsState(
        targetValue = when (editable) {
            true -> accentColor.copy(alpha = TextFieldDefaults.BackgroundOpacity)
            else -> MaterialTheme.colors.surface.copy(alpha = 0f)
        }
    )
    val unfocusedIndicatorColor by animateColorAsState(
        targetValue = when (editable) {
            true -> MaterialTheme.colors.onSurface.copy(alpha = TextFieldDefaults.UnfocusedIndicatorLineOpacity)
            else -> MaterialTheme.colors.surface.copy(alpha = 0f)
        }
    )

    CompositionLocalProvider(
        LocalTextSelectionColors provides TextSelectionColors(
            handleColor = accentColor,
            backgroundColor = accentColor.copy(alpha = .4f)
        )
    ) {
        val bringIntoViewRequester = remember { BringIntoViewRequester() }
        val scope = rememberCoroutineScope()
        val keyboardController = LocalSoftwareKeyboardController.current

        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .bringIntoViewRequester(bringIntoViewRequester)
                .onFocusEvent {
                    if (it.isFocused || it.hasFocus) {
                        scope.launch {
                            delay(250)
                            bringIntoViewRequester.bringIntoView()
                        }
                    }
                }
                .padding(
                    horizontal = 16.dp,
                    vertical = verticalPadding
                )
                .clickable(editable) {},
            value = value,
            onValueChange = onValueChange,
            readOnly = !editable,
            maxLines = 1,
            label = label?.let { { Text(text = label) } },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = backgroundColor,
                focusedLabelColor = MaterialTheme.colors.onSurface,
                focusedIndicatorColor = accentColor,
                unfocusedIndicatorColor = unfocusedIndicatorColor,
                cursorColor = accentColor
            ),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onAny = { keyboardController?.hide() })
        )
    }
}