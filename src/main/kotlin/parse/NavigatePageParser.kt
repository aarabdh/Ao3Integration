package aarabdh.ao3integration.parse

import aarabdh.ao3integration.domain.ChapterList
import org.jsoup.nodes.Document

fun parseNavigatePageForChapterList(doc: Document): ChapterList {
    return ChapterList(doc.select("ol.chapter.index.group li a[href]").map {
        it.attr("href")
    })
}


