# Kapitalize ![Build Status](https://travis-ci.com/LithiumSR/Kapitalize.svg?branch=master) [![License: GPL v3](https://img.shields.io/badge/License-GPL%20v3-blue.svg)](https://www.gnu.org/licenses/gpl-3.0)

Kapitalize is a library to capitalize people's names.
This library is written using Kotlin and the JDK8.

Kapitlize is not perfect and never will, some names may be incorrectly capitalized.

At the time of writing the library supports namecases that includes:
Mc, Mac, al, el, ap, da, de, delle, della, di, du, del, der, 
    la, le, lo, van and von.
    
Kapitalize correctly deals with names which contain apostrophies and hyphens too.

There are "special rules" for the following languages: Italian, Spanish and Hebrew.
## Getting started

### Compile from sources
- `git clone` or download this repo.
- Open a terminal in the directory where the sources are stored.
- Execute `gradlew assemble` .

### Add to your project

Kapitalize can be easily added to your existing project through Maven or Gradle.

**Maven**

1) Add the JitPack repository
```
<repositories>
	<repository>
	    <id>jitpack.io</id>
		<url>https://jitpack.io</url>
	</repository>
</repositories>
```
2) Add the dependency
```
<dependency>
    <groupId>com.github.LithiumSR</groupId>
    <artifactId>Kapitalize</artifactId>
    <version>1.0.0</version>
</dependency>
```

**Gradle**

1) Add it in your root build.gradle at the end of repositories:
```
allprojects {
    repositories {
		maven { url 'https://jitpack.io' }
	}
}
```
2) Add the dependency
```
dependencies {
    implementation 'com.github.LithiumSR:Kapitalize:1.0.0'
}
```

### Examples
```
// Create a kapitalize object to analyze english language with no special rules
val kapitalize = Kapitalize()
// Create a kapitalize object with italian-specific rules
val kapitalizeItalian = Kapitalize(EnglishLanguage(SpecialRules.ITALIAN)


// Time to capitalize everythin!
println(kapitalize.capitalize("hEnRy vIi") // output: "Henry VIII"
println(kapitalize.capitalize("hEnRy vIi") // output: "Henry VIII"
println(kapitalize.capitalize("O'CALLAGHAN") // output: "O'Callaghan"
println(kapitalize.capitalize(" mckenna ") // output: "McKenna"
println(kapitalize.capitalize("LEIGH-WILLIAMS ") // output: "Leigh-Williams"
println(kapitalizeItalian.capitalize("silvio dei giudici") // output: "Silvio Dei Giudici

 ```
 
 ## Acknowledgments
 
 * Thanks to Mark Summerfield and Barbie for their Perl library [Lingua::EN::NameCase](https://metacpan.org/pod/Lingua::EN::NameCase) on which Kapitalize is inspired from.
