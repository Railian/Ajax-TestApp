package ua.railian.ajax.testapp.presentation.compose.ui.contact.list

import android.content.res.Configuration
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import ua.railian.ajax.testapp.domain.contract.entity.Contact
import ua.railian.ajax.testapp.domain.contract.extension.fullName
import ua.railian.ajax.testapp.presentation.compose.extension.color
import ua.railian.ajax.testapp.presentation.compose.theme.AjaxTestAppTheme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ContactItem(
    contact: Contact,
    modifier: Modifier = Modifier,
    cardModifier: Modifier = Modifier,
    elevation: Dp = 8.dp,
    labelColor: Color = contact.color,
    labelWidth: Dp = 8.dp,
    imageShape: Shape = CircleShape,
    imagePlaceholder: Painter = rememberVectorPainter(image = Icons.Default.AccountCircle),
    imageError: Painter = imagePlaceholder,
    deleteState: DismissState = rememberDismissState(),
    deleteColor: Color = MaterialTheme.colors.error,
    deleteIcon: Painter = rememberVectorPainter(Icons.Default.Delete),
    onClick: ((Contact) -> Unit),
    onDelete: ((Contact) -> Unit)
) {
    if (deleteState.isDismissed(DismissDirection.EndToStart)) {
        onDelete(contact)
    }
    SwipeToDismiss(
        modifier = modifier,
        state = deleteState,
        directions = setOf(DismissDirection.EndToStart),
        dismissThresholds = { FractionalThreshold(.35f) },
        background = {
            BackgroundForDelete(
                modifier = Modifier.fillMaxSize(),
                state = deleteState,
                color = deleteColor,
                icon = deleteIcon
            )
        },
        dismissContent = {
            ContactItemCard(
                contact = contact,
                modifier = Modifier
                    .clickable { onClick(contact) }
                    .then(cardModifier),
                elevation = elevation,
                labelColor = labelColor,
                labelWidth = labelWidth,
                imageShape = imageShape,
                imagePlaceholder = imagePlaceholder,
                imageError = imageError,
            )
        }
    )
}


@Composable
private fun ContactItemCard(
    contact: Contact,
    modifier: Modifier = Modifier,
    elevation: Dp = 8.dp,
    labelColor: Color = contact.color,
    labelWidth: Dp = 8.dp,
    imageShape: Shape = CircleShape,
    imagePlaceholder: Painter = rememberVectorPainter(image = Icons.Default.AccountCircle),
    imageError: Painter = imagePlaceholder
) {
    val rippleIndication = rememberRipple(color = labelColor)
    CompositionLocalProvider(LocalIndication provides rippleIndication) {

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize()
                .padding(all = 8.dp)
                .then(modifier),
            elevation = elevation
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(labelWidth)
                        .background(labelColor)
                )

                var isImageLoaded by remember { mutableStateOf(false) }
                val imageColorFilter = if (isImageLoaded) null
                else ColorFilter.tint(LocalContentColor.current)

                AsyncImage(
                    modifier = Modifier
                        .padding(
                            start = 16.dp,
                            end = 4.dp,
                            top = 8.dp,
                            bottom = 8.dp
                        )
                        .size(56.dp)
                        .clip(imageShape),
                    contentDescription = "Contact image",
                    model = contact.picture.thumbnail,
                    error = imageError,
                    onLoading = { isImageLoaded = false },
                    onError = { isImageLoaded = false },
                    onSuccess = { isImageLoaded = true },
                    colorFilter = imageColorFilter
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = 12.dp)
                ) {
                    Text(
                        text = contact.fullName,
                        style = MaterialTheme.typography.h5
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    CompositionLocalProvider(
                        LocalContentAlpha provides when (val current = LocalContentAlpha.current) {
                            ContentAlpha.disabled -> current
                            else -> ContentAlpha.medium
                        }
                    ) {
                        Text(
                            text = contact.email,
                            style = MaterialTheme.typography.body1
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun BackgroundForDelete(
    modifier: Modifier = Modifier,
    state: DismissState,
    color: Color = MaterialTheme.colors.error.copy(alpha = .8f),
    alignment: Alignment = Alignment.CenterEnd,
    icon: Painter = rememberVectorPainter(Icons.Default.Delete)
) {

    val contentColor by animateColorAsState(
        targetValue = when (state.targetValue) {
            DismissValue.Default -> LocalContentColor.current
            else -> color
        },
        animationSpec = tween(durationMillis = 900)
    )

    val scale by animateFloatAsState(
        targetValue = if (state.targetValue == DismissValue.Default) .9f else 1.1f,
        animationSpec = tween(durationMillis = 300)
    )

    Box(
        modifier = Modifier
            .padding(
                horizontal = 24.dp,
                vertical = 8.dp
            )
            .then(modifier),
        contentAlignment = alignment
    ) {
        CompositionLocalProvider(LocalContentColor provides contentColor) {
            Icon(
                modifier = Modifier.scale(scale),
                contentDescription = "Dismiss Icon",
                painter = icon
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ContactItemPreview() {
    AjaxTestAppTheme {
        Surface {
            ContactItem(
                contact = stubContact.copy(
                    firstName = "Yevhen",
                    lastName = "Railian",
                    email = "yevhen.railian@mail.ua"
                ),
                onClick = {},
                onDelete = {}
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ContactItemDeletePreview() {
    AjaxTestAppTheme {
        Surface {
            ContactItem(
                contact = stubContact,
                deleteState = rememberDismissState(initialValue = DismissValue.DismissedToStart),
                onClick = {},
                onDelete = {}
            )
        }
    }
}