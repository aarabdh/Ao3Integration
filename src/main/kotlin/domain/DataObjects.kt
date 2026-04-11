package aarabdh.ao3integration.domain

import aarabdh.ao3integration.util.URLHelper
import org.jsoup.nodes.Document
import java.util.Date

data class StoryEntry(
    val id: Long,
    val title: String,
    val summary: String,
    val tags: List<Tag>,
    val link: Link,
    val rating: Rating,
    val language: String,
    val chaptersCount: Int,
    val kudosCount: Int,
    val commentCount: Int,
    val hitCount: Long,
    val categories: List<Category>,
    val warning: List<Warning>,
    val complete: Boolean,
)

data class Chapter(
    val id: Long,
    val title: String,
    val number: Int,
    val link: String,
    val publicationDate: Date,
    val coCreatorsList: List<Creator>,
    val summary: String?,
    val notesAtStart: String?,
    val notesAtEnd: String?,
    val rawHtml: String,
    val transformedText: String
)

class ChapterList(list: List<String>): ArrayList<String>(list), List<String>

data class Creator(val name: String, val link: String)

data class Tag(
    val type: TagType,
    val name: String,
    val link: Link
)

data class Author(val name: String, val link: Link)

data class ResponseObject(val type: ResponseEnum, val document: Document)

data class Link(val link: String, val paramsExist: Boolean) {
    fun toURL(page: Int = 1): String {
        val postFix = if (paramsExist) {
            "&page=$page"
        } else {
            "?page=$page"
        }
        return URLHelper.AO3_SITE_MAIN + link + postFix
    }
}