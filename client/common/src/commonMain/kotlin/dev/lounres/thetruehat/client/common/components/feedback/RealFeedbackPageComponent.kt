package dev.lounres.thetruehat.client.common.components.feedback

import dev.lounres.thetruehat.api.localization.Language


public class RealFeedbackPageComponent(
    override val backButtonEnabled: Boolean,
    override val onBackButtonClick: () -> Unit,
    override val onLanguageChange: (Language) -> Unit,
    override val onFeedbackButtonClick: () -> Unit,
    override val onHatButtonClick: () -> Unit,
    override val sendFeedback: (feedback: String, sendAdditionalData: Boolean) -> Unit,
): FeedbackPageComponent