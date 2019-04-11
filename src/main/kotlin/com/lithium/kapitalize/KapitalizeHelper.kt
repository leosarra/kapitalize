package com.lithium.kapitalize

class KapitalizeHelper {
    companion object {
        fun isRomanNumerals(input: String): Boolean {
            return input.toLowerCase().matches(Regex("^m{0,4}(cm|cd|d?c{0,3})(xc|xl|l?x{0,3})(ix|iv|v?i{0,3})$"))
        }

        fun capitalizeAfterChar(kapitalize: Kapitalize?, afterString: String, input: String): String {
            return if (input.contains(afterString)) {
                val stringBuilder = StringBuilder()
                val list = input.split(afterString)
                stringBuilder.append(list[0] + afterString)
                if (list.size >= 2) if (kapitalize != null) stringBuilder.append(kapitalize.capitalize(list[1]).capitalize()) else stringBuilder.append(
                    list[1].capitalize()
                )
                list.forEachIndexed { index, s ->
                    if (index > 1) stringBuilder.append(s)
                }
                stringBuilder.toString()
            } else input
        }
    }
}