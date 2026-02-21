package aarabdh.ao3integration

import okhttp3.OkHttpClient
import okhttp3.Request
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

fun main() {
    val client = OkHttpClient()

    val request = Request.Builder()
        .url(Ao3HelperStrings.AO3_SITE_MAIN)
        .get()
        .build()
    var document: Document? = null
    client.newCall(request).execute().use { response ->
        if (!response.isSuccessful) {
            throw RuntimeException("Unexpected code $response")
        }

        val body = response.body?.string()
        document = Jsoup.parse(body!!)
    }

    if (document != null) {
        val links = document.select("a")
        for (link in links) {
            val href = link.attr("abs:href")  // resolves relative URLs
            val text = link.text()
            println("Link: $href | Text: $text")
        }
    }
}