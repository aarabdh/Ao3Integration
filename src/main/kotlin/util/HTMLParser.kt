package aarabdh.ao3integration.util

import aarabdh.ao3integration.domain.Category
import aarabdh.ao3integration.domain.Link
import aarabdh.ao3integration.domain.Rating
import aarabdh.ao3integration.domain.StoryEntry
import aarabdh.ao3integration.domain.Tag
import aarabdh.ao3integration.domain.Warning
import org.jsoup.nodes.Element

class HTMLParser {
    companion object {
        fun parseStoryEntry(workElement: Element): StoryEntry {

            val id = workElement.id()
                .removePrefix("work_")
                .toLong()

            val titleAnchor = workElement.selectFirst("h4.heading > a")
                ?: error("Title not found")

            val title = titleAnchor.text()
            val storyLink = Link(titleAnchor.attr("href"))

            val summary = workElement
                .selectFirst("blockquote.summary")
                ?.text()
                ?.trim()
                ?: ""

            val requiredTags = workElement.select("ul.required-tags span")

            val rating = Rating.valueOf(requiredTags.text())
            val warning = Warning.valueOf(requiredTags.text())
            val category = Category.getCategories(requiredTags.text())
            val complete = isComplete(requiredTags.text())

            val language = workElement
                .selectFirst("dd.language")
                ?.text()
                ?.trim()
                ?: "Unknown"

            val chaptersText = workElement
                .selectFirst("dd.chapters")
                ?.text()
                ?.trim()
                ?: "0/0"

            val chaptersCount = chaptersText.substringBefore("/")
                .toIntOrNull() ?: 0

            val kudosCount = workElement
                .selectFirst("dd.kudos")
                ?.text()
                ?.replace(",", "")
                ?.toIntOrNull() ?: 0

            val commentCount = workElement
                .selectFirst("dd.comments")
                ?.text()
                ?.replace(",", "")
                ?.toIntOrNull() ?: 0

            val tags = parseTags(workElement.text())

            return StoryEntry(
                id = id,
                title = title,
                summary = summary,
                tags = tags,
                link = storyLink,
                rating = rating,
                language = language,
                chaptersCount = chaptersCount,
                kudosCount = kudosCount,
                commentCount = commentCount,
                category = category,
                complete = complete,
                warning = warning
            )
        }

        private fun isComplete(str: String): Boolean {
            return true
        }

        private fun parseTags(tagString: String): List<Tag> {
            return listOf()
        }
    }
}