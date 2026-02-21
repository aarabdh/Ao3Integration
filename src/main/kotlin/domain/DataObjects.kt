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
    val category: List<Category>,
    val complete: Boolean,
    val warning: Warning
)

data class Tag(
    val id: Long,
    val type: TagType,
    val name: String,
    val link: Link
)

data class Author(val name: String, val link: Link)

data class ResponseObject(val type: ResponseEnum, val document: Document)

data class Link(val link: String) {
    fun toURL(): String {
        return URLHelper.AO3_SITE_MAIN + link
    }
}