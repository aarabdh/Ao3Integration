package aarabdh.ao3integration.util

interface URLHelper {
    companion object {
        const val AO3_SITE_MAIN = "https://archiveofourown.org"
        const val SEARCH_WORKS = "/works/search"
        const val SEARCH_KEYWORDS = "/works/search?commit=Search&page={PAGE_NUMBER}&work_search%5Bquery%5D={SEARCH_QUERY}&work_search%5Btitle%5D=&work_search%5Bcreators%5D=&work_search%5Brevised_at%5D=&work_search%5Bcomplete%5D=&work_search%5Bcrossover%5D=&work_search%5Bsingle_chapter%5D=0&work_search%5Bword_count%5D=&work_search%5Blanguage_id%5D=&work_search%5Bfandom_names%5D=&work_search%5Brating_ids%5D=&work_search%5Bcharacter_names%5D=&work_search%5Brelationship_names%5D=&work_search%5Bfreeform_names%5D=&work_search%5Bhits%5D=&work_search%5Bkudos_count%5D=&work_search%5Bcomments_count%5D=&work_search%5Bbookmarks_count%5D=&work_search%5Bsort_column%5D=_score&work_search%5Bsort_direction%5D=desc"

        fun getSearchString(search: String, page: Int = 1): String {
            val sanitizedSearch = search.replace(" ", "+")
            return AO3_SITE_MAIN + SEARCH_KEYWORDS.replace("{SEARCH_QUERY}", sanitizedSearch).replace("{PAGE_NUMBER}", page.toString())
        }
    }
}