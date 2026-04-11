package aarabdh.ao3integration.parse

import aarabdh.ao3integration.domain.Chapter
import aarabdh.ao3integration.domain.Creator
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import java.text.SimpleDateFormat
import java.util.Date

private const val CSS_END_NOTES = "div.chapter.preface.group > div.end.notes.module > blockquote.userstuff"
private const val CSS_START_NOTES = "div.chapter.preface.group > div#notes.notes.module > blockquote.userstuff"
private const val CSS_TITLE = "div.chapter.preface.group > h3.title"
private const val CSS_CHAPTER = "div.chapter[id^=chapter-]"
private const val CSS_SUMMARY = "div.chapter.preface.group > div#summary.summary.module > blockquote.userstuff"
private const val CSS_BODY = "div.userstuff.module[role=article]"
private const val CSS_COCREATOR = "div.chapter.preface.group > h3.byline"
private const val CSS_PUBLISH_DATE = "dl.stats > dd.published"
private const val CSS_HEADING = "h3.landmark.heading#work"

fun parseChapterPage(doc: Document, chapterId: Long): Chapter {
    val dateString = doc.selectFirst(CSS_PUBLISH_DATE)!!.text()
    val publicationDate: Date = SimpleDateFormat("yyyy-MM-dd").parse(dateString)

    val chapterEl: Element = doc.selectFirst(CSS_CHAPTER)!!

    val chapterNumber = chapterEl.id().removePrefix("chapter-").toInt()

    val titleElement = chapterEl.selectFirst(CSS_TITLE)!!
    val chapterLink = titleElement.selectFirst("a")!!.attr("href")
    val fullTitleText = titleElement.text()
    val chapterTitle = fullTitleText.substringAfter(": ").trim()

    val coCreatorsList: List<Creator> = chapterEl.selectFirst(CSS_COCREATOR)
        ?.select("a[rel=author]")
        ?.map { Creator(it.text().trim(),it.attr("href")) } ?: emptyList()

    val summaryHtml = chapterEl.selectFirst(CSS_SUMMARY)?.text()?.trim()
    val notesAtStart = chapterEl.selectFirst(CSS_START_NOTES)?.text()?.trim()
    val notesAtEnd = chapterEl.selectFirst(CSS_END_NOTES)?.text()?.trim()

    val bodyEl = chapterEl.selectFirst(CSS_BODY)!!
    bodyEl.selectFirst(CSS_HEADING)?.remove()
    val rawHtml = bodyEl.html().trim()
    val transformedText = bodyEl.text().trim()

    return Chapter(
        id = chapterId,
        title = chapterTitle,
        number = chapterNumber,
        link = chapterLink,
        publicationDate = publicationDate,
        coCreatorsList = coCreatorsList,
        summary = summaryHtml,
        notesAtStart = notesAtStart,
        notesAtEnd = notesAtEnd,
        rawHtml = rawHtml,
        transformedText = transformedText
    )
}