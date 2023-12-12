package dev.lounres.thetruehat.client.desktop.components.home

import dev.lounres.thetruehat.api.localization.Language


class RealHomePageComponent(
    override val backButtonEnabled: Boolean,
    override val onBackButtonClick: () -> Unit,
    override val onLanguageChange: (language: Language) -> Unit,
    override val onFeedbackButtonClick: () -> Unit,
    override val onHatButtonClick: () -> Unit,
    override val onCreateButtonClick: () -> Unit,
    override val onEnterButtonClick: () -> Unit
) : HomePageComponent