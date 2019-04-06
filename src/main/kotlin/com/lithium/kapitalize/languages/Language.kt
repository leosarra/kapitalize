package com.lithium.kapitalize.languages

interface Language {
    fun transform(input: String): String
}

enum class SpecialRules {
    NONE, ITALIAN, SPANISH, HEBREW
}