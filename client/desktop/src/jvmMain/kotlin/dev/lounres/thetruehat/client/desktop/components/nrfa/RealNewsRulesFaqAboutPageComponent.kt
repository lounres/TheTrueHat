package dev.lounres.thetruehat.client.desktop.components.nrfa

import dev.lounres.thetruehat.api.localization.Language


class RealNewsRulesFaqAboutPageComponent(
    override val backButtonEnabled: Boolean,
    override val onBackButtonClick: () -> Unit,
    override val onLanguageChange: (language: Language) -> Unit,
    override val onFeedbackButtonClick: () -> Unit,
) : NewsRulesFaqAboutPageComponent {
    override val onNewsButtonClick: () -> Unit = { TODO() }
    override val onRulesButtonClick: () -> Unit = { TODO() }
    override val onFaqButtonClick: () -> Unit = { TODO() }
    override val onAboutButtonClick: () -> Unit = { TODO() }
}