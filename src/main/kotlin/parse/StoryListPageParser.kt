package aarabdh.ao3integration.parse

import aarabdh.ao3integration.domain.Category
import aarabdh.ao3integration.domain.Link
import aarabdh.ao3integration.domain.Rating
import aarabdh.ao3integration.domain.StoryEntry
import aarabdh.ao3integration.domain.Tag
import aarabdh.ao3integration.domain.TagType
import aarabdh.ao3integration.domain.Warning
import aarabdh.ao3integration.util.*
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements

fun parsePageForStoryEntries(doc: Document): List<StoryEntry> {
    return doc.body().select(CSS_STORY_ENTRIES).map {
        parseStoryEntry(it)
    }
}

fun parseStoryEntry(workElement: Element): StoryEntry {
    val titleAnchor = workElement.selectFirst(CSS_STORY_LIST_TITLE)
        ?: error("Title not found")

    val title = titleAnchor.text()
    val storyLink = Link(titleAnchor.attr("href"), false)
    val id = storyLink.link.split("/").last().trim().toLongOrNull() ?: 0

    val summary = workElement.selectFirst(CSS_STORY_LIST_SUMMARY)?.text()?.trim()
        ?: ""

    val requiredTags = workElement.select(CSS_REQUIRED_TAGS)

    val rating = Rating.fromValue(requiredTags[0].text())
    val warning = Warning.getWarnings(requiredTags[1].text())
    val categories = Category.getCategories(requiredTags[2].text())
    val complete = isComplete(requiredTags[3].text())

    val language = workElement.selectFirst(CSS_LANGUAGE)?.text()?.trim()
        ?: "Unknown"

    val chaptersText = workElement.selectFirst(CSS_CHAPTER_COUNT)?.text()?.trim()
        ?: "0/0"

    val chaptersCount = chaptersText.substringBefore("/").toIntOrNull()
        ?: 0

    val kudosCount = workElement.selectFirst(CSS_KUDOS)?.text()?.replace(",", "")?.toIntOrNull()
        ?: 0

    val hitCount = workElement.selectFirst(CSS_HITS)?.text()?.replace(",", "")?.toLongOrNull()
        ?: 0

    val commentCount = workElement.selectFirst(CSS_COMMENTS)?.text()?.replace(",", "")?.toIntOrNull()
        ?: 0

    val tags = parseTags(workElement.select(CSS_TAGS))

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
        hitCount = hitCount,
        categories = categories,
        complete = complete,
        warning = warning
    )
}

private fun isComplete(str: String): Boolean {
    return "Complete Work" == str
}

private fun parseTags(tagElements: Elements): List<Tag> {
    val tags = mutableListOf<Tag>()
    for (tagType in TagType.entries) {
        addTags(tagElements, tags, tagType)
    }
    return tags
}

private fun addTags(tagElements: Elements, response: MutableList<Tag>, type: TagType) {
    val relationshipTags = tagElements.select(type.css)
    for (element in relationshipTags) {
        response.add(element.tag(type))
    }
}

private fun Element.tag(type: TagType): Tag {
    return Tag(
        type,
        this.text(),
        Link(this.select("a").attr("href"), false)
    )
}