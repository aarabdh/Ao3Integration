package aarabdh.ao3integration.domain

import aarabdh.ao3integration.util.URLHelper
import org.jsoup.nodes.Document

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