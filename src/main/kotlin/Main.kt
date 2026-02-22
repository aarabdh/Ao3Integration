package aarabdh.ao3integration

import aarabdh.ao3integration.domain.ResponseEnum
import aarabdh.ao3integration.domain.ResponseObject
import aarabdh.ao3integration.util.URLHelper

fun main() {
    val url = URLHelper.getSearchString("Harry Potter", 4)
    val url2 = "https://archiveofourown.org/tags/Harry%20Potter%20-%20J*d*%20K*d*%20Rowling/works"
    val url3 = "https://archiveofourown.org/works/76328156"
    val responseDoc = Request.getResponse(url3)
    if (responseDoc != null) {
        val respDoc = ResponseObject(ResponseEnum.SEARCH_STRING, responseDoc)
//        println(parsePageForStoryEntries(responseDoc))
        println(respDoc)
    }
}