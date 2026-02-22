package aarabdh.ao3integration

import okhttp3.Headers
import okhttp3.OkHttpClient
import okhttp3.Request
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.util.concurrent.TimeUnit

object Request {
    private val client = OkHttpClient().newBuilder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .build()

    private val headers = Headers.Builder()
        .add("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/121.0.0.0 Safari/537.36")
        .add("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
        .add("Accept-Language", "en-US,en;q=0.5")
        .add("Connection", "keep-alive")
        .build()

    fun getResponse(url: String): Document? {
        val request = Request.Builder().url(url).headers(headers).get().build()
        return try {
            client.newCall(request).execute().use { response ->
                if (!response.isSuccessful) return null

                val bodyStream = response.body?.byteStream() ?: return null
                Jsoup.parse(bodyStream, null, url)
            }
        } catch (_: Exception) {
            null
        }
    }

    fun changeUserAgent(agent: String) {
        headers.newBuilder().removeAll("User-Agent")
            .add("User-Agent", agent)
            .build()
    }
}