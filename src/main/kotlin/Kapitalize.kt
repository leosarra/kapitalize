import languages.EnglishLanguage
import languages.Language
import languages.SpecialRules

class Kapitalize(private val language: Language = EnglishLanguage(SpecialRules.NONE)) {

    fun capitalize(input:String):String {
        return language.transform(input)
    }
}