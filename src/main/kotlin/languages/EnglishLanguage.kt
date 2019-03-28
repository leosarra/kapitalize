package languages

import java.lang.StringBuilder

class EnglishLanguage (private val specialRules: SpecialRules = SpecialRules.NONE) :Language  {

    private val capitalizeAfter = listOf("-", "'")
    private val lowercaseWordsHebrew = listOf("ben","bat")
    private val capitalizedWordsItalian = listOf("da", "de", "del", "della", "di", "delle","lo", "dei")
    private val capitalizedWordsSpanish = listOf("el", "la")
    private val lowercaseWords = listOf("al","ap","della","delle","da","de","di","du","del","el","la","lo","van","von")
    private val mcMacExceptions = listOf("macevicius","machado","machar","machin","machlin","macias","maciulis","mackie", "mackle",
    "macklin","macquarie","macomber", "macin","mackintosh","macken","machen","machiel","maciol","mackell","macklem","mackrell","maclin","mackey","mackley","machell","machon")

    override fun transform(input: String): String {
        val words = input.trim().toLowerCase().replace(Regex(" +"), " ").split(" ")
        val builder = StringBuilder()
        words.forEach {
            var word = it

            for (item in capitalizeAfter) {
                if (word.contains(item)) {
                    word = KapitalizeHelper.capitalizeAfterChar(item,word)
                }
            }

            if(KapitalizeHelper.isRomanNumerals(word)) {
                builder.append("${word.toUpperCase()} ")
                return@forEach
            }

            if (specialRules == SpecialRules.HEBREW && word in lowercaseWordsHebrew) {
                builder.append("${word.toLowerCase()} ")
                return@forEach
            }

            if (specialRules == SpecialRules.ITALIAN && word in capitalizedWordsItalian) {
                builder.append("${word.capitalize()} ")
                return@forEach
            }

            if (specialRules == SpecialRules.SPANISH && word in capitalizedWordsSpanish) {
                builder.append("${word.capitalize()} ")
                return@forEach
            }

            if (word in lowercaseWords) {
                if (word=="van" && input.startsWith(it)) builder.append("${it.capitalize()} ")
                else builder.append("$it ")
                return@forEach
            }

            if((word.startsWith("mac") || word.startsWith("mc")) && word.length>6) {
                if (word.last() !in listOf('a','c','i','o','j') && word !in mcMacExceptions) {
                    val ret = if (word.startsWith("mac")) KapitalizeHelper.capitalizeAfterChar("mac",word) else KapitalizeHelper.capitalizeAfterChar("mc",word)
                    builder.append("${ret.capitalize()} ")
                    return@forEach
                }
            }
            builder.append("${word.capitalize()} ")
        }
        return builder.toString().trim().replace(Regex(" +")," ")
    }



}