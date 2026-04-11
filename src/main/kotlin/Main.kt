package aarabdh.ao3integration

import aarabdh.ao3integration.domain.ResponseEnum
import aarabdh.ao3integration.domain.ResponseObject
import aarabdh.ao3integration.util.URLHelper

fun main() {
    val url = URLHelper.getSearchString("Harry Potter", 4)
    val url2 = "https://archiveofourown.org/tags/Harry%20Potter%20-%20J*d*%20K*d*%20Rowling/works"
    val url3 = "https://archiveofourown.org/works/76328156"
    """
        <p>Hi, <u>My</u> <em>name</em> <strike>is</strike> <strong>Albert</strong>.</p>
        <p>I am me.</p>
        <p>&nbsp;</p>
        <p>Left text</p>
        <p>&nbsp;</p>
        <p align="center">Center Text</p>
        <p>&nbsp;</p>
        <p align="right">Right Text<br /><br /></p>
        <p>&nbsp;</p>
        <p align="justify">Justify Text</p>
        <p>&nbsp;</p>
        <p dir="rtl">Trying other sided paragraph</p>
        <p dir="ltr">&nbsp;</p>
        <hr />
        <p>&nbsp;</p>
        <blockquote>
        <p>Quoted Text</p>
        </blockquote>
        <p>&nbsp;</p>
        <p>&nbsp;</p>
    """.trimIndent()
    val responseDoc = Request.getResponse(url3)
    if (responseDoc != null) {
        val respDoc = ResponseObject(ResponseEnum.SEARCH_STRING, responseDoc)
        println(respDoc)
    }
}