package com.lithium.kapitalize.languages

import com.lithium.kapitalize.Kapitalize

interface Language {
    fun transform(kapitalize: Kapitalize, input: String): String
}

enum class SpecialRules {
    NONE, ITALIAN, SPANISH, HEBREW
}