package lithium.kapitalize

import lithium.kapitalize.languages.EnglishLanguage
import lithium.kapitalize.languages.Language
import lithium.kapitalize.languages.SpecialRules

class Kapitalize(private val language: Language = EnglishLanguage(
    SpecialRules.NONE
)
) {

    fun capitalize(input: String): String {
        return language.transform(input)
    }
}