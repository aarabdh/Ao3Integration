package aarabdh.ao3integration.domain

enum class TagType {
    WARNING,
    CHARACTER,
    FREEFORM
}

enum class Rating(val value: String) {
    NOT_RATED("Not Rated"),
    GEN_AUDIENCE("General Audiences"),
    TEEN_AND_UP("Teen And Up Audiences"),
    MATURE("Mature"),
    EXPLICIT("Explicit")
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
    OTHER("Other");
    companion object {
        fun getCategories(categoryStr: String): List<Category> {
            return categoryStr.split(",").map { it.trim() }.map { Category.valueOf(it) }
        }
    }
}

enum class Warning(val value: String) {
    CHOSE_NOT_GIVE("Creator Chose Not To Use Archive Warnings"),
    DEPICTIONS_VIOLENCE("Graphic Depictions Of Violence"),
    CHARACTER_DEATH("Major Character Death"),
    NO_ARCHIVE_WARNINGS("No Archive Warnings Apply"),
    NON_CON("Rape/Non-Con"),
    BAAAD_STUFF("Underage Sex")
}