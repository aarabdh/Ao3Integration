package aarabdh.ao3integration.parse

import aarabdh.ao3integration.domain.ChapterList
import aarabdh.ao3integration.util.*
import org.jsoup.nodes.Document

fun parseNavigatePageForChapterList(doc: Document): ChapterList {
    return ChapterList(doc.select(CSS_CHAPTER_LIST).map {
        it.attr("href")
    })
}


