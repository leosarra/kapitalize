class KapitalizeHelper {
    companion object {
        fun isRomanNumerals(input:String): Boolean {
            return input.toLowerCase().matches(Regex("^m{0,4}(cm|cd|d?c{0,3})(xc|xl|l?x{0,3})(ix|iv|v?i{0,3})$"))
        }

        fun capitalizeAfterChar(afterString:String, input:String): String {
            return if (input.contains(afterString)) {
                val chars = input.toLowerCase().toCharArray()
                val index = input.indexOf(afterString)+afterString.length
                if (index<input.length) chars[index] = chars[index].toUpperCase()
                String(chars)
            } else input
        }
    }
}