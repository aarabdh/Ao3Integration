package aarabdh.ao3integration

import aarabdh.ao3integration.domain.Chapter
import aarabdh.ao3integration.domain.ChapterList
import aarabdh.ao3integration.domain.StoryEntry
import aarabdh.ao3integration.parse.parseChapterPage
import aarabdh.ao3integration.parse.parseNavigatePageForChapterList
import aarabdh.ao3integration.parse.parsePageForStoryEntries
import aarabdh.ao3integration.util.*

object API {
    @JvmStatic
    fun getTagWorks(tagName: String): List<StoryEntry>? {
        return Request.getResponse(URLHelper.getTagWorksUrl(tagName))?.let { parsePageForStoryEntries(it) }
    }

    @JvmStatic
    fun getSearchWorks(searchString: String): List<StoryEntry>? {
        return Request.getResponse(URLHelper.getSearchString(searchString))?.let { parsePageForStoryEntries(it) }
    }

    @JvmStatic
    fun getChaptersOfStory(storyId: Long): ChapterList? {
        return Request.getResponse(URLHelper.getNavigatePageForStory(storyId))?.let { parseNavigatePageForChapterList(it) }
    }

    @JvmStatic
    fun getChapter(chapterId: Long): Chapter? {
        return Request.getResponse(URLHelper.chapterFromId(chapterId))?.let { parseChapterPage(it, chapterId) }
    }

    @JvmStatic
    fun getAuthorWorks(authorId: Long): List<StoryEntry> {
        TODO("Yet to be implemented.")
    }
}