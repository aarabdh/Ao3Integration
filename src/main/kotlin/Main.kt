package aarabdh.ao3integration

import aarabdh.ao3integration.domain.ResponseEnum
import aarabdh.ao3integration.domain.ResponseObject
import aarabdh.ao3integration.util.URLHelper

val requestSingleton = RequestSingleton()

fun main() {
    val url = URLHelper.getSearchString("Harry Potter")
    val responseDoc = requestSingleton.getResponse(url)
    if (responseDoc != null) {
        val respDoc = ResponseObject(ResponseEnum.SEARCH_STRING, responseDoc)
        println(responseDoc.body())
    }
}