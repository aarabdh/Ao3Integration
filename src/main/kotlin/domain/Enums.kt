package aarabdh.ao3integration.domain

enum class TagType(val css: String) {
    WARNING("li.warnings"),
    CHARACTER("li.characters"),
    FREEFORM("li.freeforms"),
    RELATIONSHIP("li.relationships")
}

enum class Rating(val value: String) {
    NOT_RATED("Not Rated"),
    GEN_AUDIENCE("General Audiences"),
    TEEN_AND_UP("Teen And Up Audiences"),
    MATURE("Mature"),
    EXPLICIT("Explicit"),
    DEFAULT("NEVER USED");

    companion object {
        private val map = entries.associateBy(Rating::value)
        fun fromValue(value: String): Rating = map[value] ?: DEFAULT
    }
}

enum class ResponseEnum {
    SEARCH_STRING,
    FANDOM_BROWSE
}

enum class Category(val value: String) {
    GEN("Gen"),
    HET("F/M"),
    SLASH("M/M"),
    FEMSLASH("F/F"),
    OTHER("Other"),
    MULTI("Multi"),
    NO_CATEGORY("No category"),
    DEFAULT("NEVER USED");

    companion object {
        private val map = Category.entries.associateBy(Category::value)
        fun fromValue(value: String): Category = map[value] ?: DEFAULT
        fun getCategories(categoryStr: String): List<Category> {
            return categoryStr.split(",").map { it.trim() }.map { Category.fromValue(it) }
        }
    }
}

enum class Warning(val value: String) {
    CHOSE_NOT_GIVE("Creator Chose Not To Use Archive Warnings"),
    DEPICTIONS_VIOLENCE("Graphic Depictions Of Violence"),
    CHARACTER_DEATH("Major Character Death"),
    NO_ARCHIVE_WARNINGS("No Archive Warnings Apply"),
    NON_CON("Rape/Non-Con"),
    BAAAD_STUFF("Underage Sex"),
    DEFAULT("NEVER USED");

    companion object {
        private val map = Warning.entries.associateBy(Warning::value)
        fun fromValue(value: String): Warning = map[value] ?: DEFAULT

        fun getWarnings(categoryStr: String): List<Warning> {
            return categoryStr.split(",").map { it.trim() }.map { Warning.fromValue(it) }
        }
    }
}