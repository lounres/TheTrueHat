package dev.lounres.thetruehat.client.desktop.uiTemplates

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import dev.lounres.thetruehat.api.localization.Language
import dev.lounres.thetruehat.client.common.ui.CircleButtonWithImage
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource


@OptIn(ExperimentalResourceApi::class)
@Composable
fun TheTrueHatPageWithHatTemplateUI(
    backButtonEnabled: Boolean,
    onBackButtonClick: () -> Unit,
    onLanguageChange: (Language) -> Unit,
    onFeedbackButtonClick: () -> Unit,
    onHatButtonClick: () -> Unit,
    pageContent: @Composable ColumnScope.() -> Unit,
) {
    TheTrueHatPageTemplateUI(
        backButtonEnabled = backButtonEnabled,
        onBackButtonClick = onBackButtonClick,
        onLanguageChange = onLanguageChange,
        onFeedbackButtonClick = onFeedbackButtonClick,
        pageHeader = {
            CircleButtonWithImage(
                image = painterResource("hat.png"),
                onClick = onHatButtonClick,
            )
        },
        pageContent = pageContent,
    )
}