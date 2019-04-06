package com.lithium.kapitalize

import com.lithium.kapitalize.languages.EnglishLanguage
import com.lithium.kapitalize.languages.Language
import com.lithium.kapitalize.languages.SpecialRules

class Kapitalize(private val language: Language = EnglishLanguage(
    SpecialRules.NONE
)
) {

    fun capitalize(input: String): String {
        return language.transform(input)
    }
}