package languages

import KapitalizeHelper

class EnglishLanguage(private val specialRules: SpecialRules = SpecialRules.NONE) : Language {

    private val capitalizeAfter = listOf("-", "'")
    private val lowercaseWordsHebrew = Regex("ben|bat")
    private val capitalizedWordsItalian = Regex("da|de|del|della|di|delle|lo|dei")
    private val capitalizedWordsSpanish = Regex("el|la")
    private val lowercaseWords = Regex("al|ap|della|delle|da|de|di|du|del|el|la|lo|van|von")
    private val mcMacExceptions =
        Regex(".*[acioj]|macevicius|machado|machar|machin|machlin|macias|maciulis|mackie|mackle|macklin|macquarie|macomber|macin|mackintosh|macken|machen|machiel|maciol|mackell|macklem|mackrell|maclin|mackey|mackley|machell|machon")
    private val postnomial = listOf("VC","GC","KG","LG","KT","LT","KP","GCB","OM","GCSI","GCMG","GCIE","GCVO","GBE","CH","KCB","DCB","KCSI","KCMG","DCMG","KCIE","KCVO","DCVO","KBE","DBE","CB","CSI","CMG","CIE","CVO","CBE","DSO","LVO","OBE","ISO","MVO","MBE","IOM","CGC","RRC","DSC","MC","DFC","AFC","ARRC","OBI","DCM","CGM","GM","IDSM","DSM","MM","DFM","AFM","SGM","IOMCPM","QGM","RVM","BEM","QPM","QFSM","QAM","CPM","MSM","ERD","VD","TD","UD","ED","RD","VRD","AEPC","ADC","QHP","QHS","QHDS","QHNS","QHC","SCJ","J","LJ","QS","SL","QC","KC","JP","DL","MP","MSP",
                                    "MSYP","AM","AMMLA","MEP","","DBEnv","DConstMgt","DREst","EdD","DPhil","PhD","DLitt","DSocSci","MD","EngD","DD","LLDDProf","MA","MArch","MAnth","MSc","MMORSE","MMath","MMathStat","MPharm","MPhil","MSc","MSci","MStMRes","MEng","MChem","MBiochem","MSocSc","MMus","LLM","BCL","MPhys","MComp","MAcc","MFin","MBA","MPAMEd","MEP","MEnt","MCGI","MGeol","MLitt","MEarthSc","MClinRes","BA","BSc","LLB","BEng","MBChB","FdAFdSc","FdEng","PgDip","PgD","PgCert","PgC","PgCLTHE","AUH","AKC","AUS","HNC","HNCert","HND","HNDipDipHE","Dip",
                                    "OND","CertHE","ACSM","MCSM","DIC","AICSM","ARSM","ARCS","LLB","LLM","BCL","MJur","DPhilPhD","LLD","DipLP","FCILEx","GCILEx","ACILEx","CQSW","DipSW","BSW","MSW","FCILT","CMILT","MILT","CPLCTP","CML","PLS","CTL","DLP","PLog","EJLog","ESLog","EMLog","JrLog","Log","SrLog","BArch","MArch","ARBRIBA","RIAS","RIAI","RSAW","MB","BM","BS","BCh","BChir","MRCS","FRCS","MS","MCh.","MRCP","FRCP","MRCPCHFRCPCH","MRCPath","MFPM","FFPM","BDS","MRCPsych","FRCPsych","MRCOG","FRCOG","MCEM","FCEM","FRCAFFPMRCA","MRCGP","FRCGP","BSc","MScChiro",
                                    "MChiro","MSc","DC","LFHOM","MFHOM","FFHOM","FADO","FBDOFCOptom","MCOptom","MOst","DPT","MCSP","FCSP.","SROT","MSCR","FSCR.","CPhT","RN","VN","RVN","BVScBVetMed","VetMB","BVM&S","MRCVS","FRCVS","FAWM","PGCAP","PGCHE","PGCE","PGDE","BEd","NPQH","QTSCSci","CSciTeach","RSci","RSciTech","CEng","IEng","EngTech","ICTTech","DEM","MM","CMarEngCMarSci","CMarTech","IMarEng","MarEngTech","RGN","SRN","RMN","RSCN","SEN","EN","RNMH","RN","RM","RN1RNA","RN2","RN3","RNMH","RN4","RN5","RNLD","RN6","RN8","RNC","RN7","RN9","RHV","RSN","ROH",
                                    "RFHN","SPANSPMH","SPCN","SPLD","SPHP","SCHM","SCLD","SPCC","SPDN","V100","V200","V300","LPE","MSc")

    var capitalizePostNominalsInitials: Boolean = true
    var capitalizeRomanNumerals: Boolean = true

    override fun transform(input: String): String {
        val processedInput = input.trim().toLowerCase().replace(Regex(" +"), " ")
        val words = processedInput.split(" ")
        val builder = StringBuilder()
        words.forEachIndexed { index, it ->
            run {
                var word = it

                for (item in capitalizeAfter) {
                    if (word.contains(item)) {
                        word = KapitalizeHelper.capitalizeAfterChar(item, word)
                    }
                }

                if (capitalizeRomanNumerals && KapitalizeHelper.isRomanNumerals(word)) {
                    builder.append("${word.toUpperCase()} ")
                    return@forEachIndexed
                }

                if (specialRules == SpecialRules.HEBREW && lowercaseWordsHebrew.matches(word)) {
                    builder.append("${word.toLowerCase()} ")
                    return@forEachIndexed
                }

                if (specialRules == SpecialRules.ITALIAN && capitalizedWordsItalian.matches(word)) {
                    builder.append("${word.capitalize()} ")
                    return@forEachIndexed
                }

                if (specialRules == SpecialRules.SPANISH && capitalizedWordsSpanish.matches(word)) {
                    builder.append("${word.capitalize()} ")
                    return@forEachIndexed
                }

                if (lowercaseWords.matches(word)) {
                    //Special rule for forename Van
                    if (Regex("van.*").matches(processedInput) && index == 0) builder.append("${word.capitalize()} ") else builder.append(
                        "$word "
                    )
                    return@forEachIndexed
                }

                if ((word.startsWith("mac") || word.startsWith("mc")) && word.length > 6) {
                    if (!mcMacExceptions.matches(word)) {
                        val ret = if (word.startsWith("mac")) KapitalizeHelper.capitalizeAfterChar(
                            "mac",
                            word
                        ) else KapitalizeHelper.capitalizeAfterChar("mc", word)
                        builder.append("${ret.capitalize()} ")
                        return@forEachIndexed
                    }
                }

                if (capitalizePostNominalsInitials) postnomial.forEach {
                    if (word == it.toLowerCase()) {
                        builder.append("$it ")
                        return@forEachIndexed
                    }
                }
                builder.append("${word.capitalize()} ")
            }
        }
        return builder.toString().trim().replace(Regex(" +"), " ")
    }


}