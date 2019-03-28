import languages.EnglishLanguage
import languages.SpecialRules
import org.junit.jupiter.api.Test

class EnglishLanguageTest {

    @Test
    fun testMacMcNames(){
        assert(Kapitalize().capitalize(" mactestunit ") == "MacTestunit")
        assert(Kapitalize().capitalize(" mctestunit ") == "McTestunit")
        assert(Kapitalize().capitalize(" mAc") == "Mac")
    }

    @Test
    fun testMacMcNamesExceptions(){
        assert(Kapitalize().capitalize(" macKinTosH") == "Mackintosh")
    }

    @Test
    fun testItalianNames(){
        assert(Kapitalize(EnglishLanguage(SpecialRules.ITALIAN)).capitalize("SILVIO DE GIUDICI") == "Silvio De Giudici")
        assert(Kapitalize(EnglishLanguage(SpecialRules.ITALIAN)).capitalize("SILVIO DELLA ROSA") == "Silvio Della Rosa")
        assert(Kapitalize(EnglishLanguage(SpecialRules.ITALIAN)).capitalize("mario d'amato") == "Mario D'Amato")
    }

    @Test
    fun testEnglishNames(){
        assert(Kapitalize().capitalize("KEITH") == "Keith")
        assert(Kapitalize().capitalize("LEIGH-WILLIAMS") == "Leigh-Williams")
        assert(Kapitalize().capitalize("O'CALLAGHAN") == "O'Callaghan")
        assert(Kapitalize().capitalize("ST. JOHN") == "St. John")
    }

    @Test
    fun testExceptions(){
        assert(Kapitalize().capitalize("VON STREIT") == "von Streit")
        assert(Kapitalize().capitalize("VAN DyKe") == "van Dyke")
        assert(Kapitalize().capitalize("AP LLWYD DAFYDD") == "ap Llwyd Dafydd")
    }

    @Test
    fun testNamesWithRomanNumerals(){
        assert(Kapitalize().capitalize("henry viii") == "Henry VIII")
        assert(Kapitalize().capitalize("louis xiv") == "Louis XIV")
    }
}