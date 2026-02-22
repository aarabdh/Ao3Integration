import aarabdh.ao3integration.domain.*
import aarabdh.ao3integration.util.parsePageForStoryEntries
import aarabdh.ao3integration.util.parseStoryEntry
import org.jsoup.Jsoup
import kotlin.test.Test

class HTMLParserTest {

    @Test fun `fanfic entry gets parsed correctly`() {
        val testHtml = Jsoup.parse(workEntry)
        val response = parseStoryEntry(testHtml)
        assert(response.id != 0L)
        assert(response.rating != Rating.DEFAULT)
        assert(response.warning.takeWhile { it == Warning.DEFAULT }.isEmpty())
        assert(response.categories.takeWhile { it == Category.DEFAULT }.isEmpty())
    }

    @Test fun `parse whole page for fanfics correctly`() {
        val testHtml = Jsoup.parse(wholePage)
        val response = parsePageForStoryEntries(testHtml)
        for (entry in response) {
            assert(entry.id != 0L, { entry.toString() })
            assert(entry.rating != Rating.DEFAULT, { entry.toString() })
            assert(entry.warning.takeWhile { it == Warning.DEFAULT }.isEmpty(), { entry.toString() })
            assert(entry.categories.takeWhile { it == Category.DEFAULT }.isEmpty(), { entry.toString() })
        }
    }

    val wholePage = $$"""
        <body class="logged-out">
         <div id="outer" class="wrapper">
          <ul id="skiplinks">
           <li><a href="#main">Main Content</a></li>
          </ul>
          <noscript>
           <p id="javascript-warning">While we've done our best to make the core functionality of this site accessible without JavaScript, it will work better with it enabled. Please consider turning it on!</p>
          </noscript><!-- BEGIN header -->
          <header id="header" class="region">
           <h1 class="heading"><a href="/"><span>Archive of Our Own</span><sup> beta</sup><img alt="Archive of Our Own" class="logo" src="/images/ao3_logos/logo_42.png"></a></h1>
           <div id="login" class="dropdown">
            <p class="user actions"><a id="login-dropdown" href="/users/login?return_to=%2Fworks%2Fsearch%3F%26commit%3DSearch%26work_search%255Bquery%255D%3DHarry%2BPotter%26work_search%255Btitle%255D%3D%26work_search%255Bcreators%255D%3D%26work_search%255Brevised_at%255D%3D%26work_search%255Bcomplete%255D%3D%26work_search%255Bcrossover%255D%3D%26work_search%255Bsingle_chapter%255D%3D0%26work_search%255Bword_count%255D%3D%26work_search%255Blanguage_id%255D%3D%26work_search%255Bfandom_names%255D%3D%26work_search%255Brating_ids%255D%3D%26work_search%255Bcharacter_names%255D%3D%26work_search%255Brelationship_names%255D%3D%26work_search%255Bfreeform_names%255D%3D%26work_search%255Bhits%255D%3D%26work_search%255Bkudos_count%255D%3D%26work_search%255Bcomments_count%255D%3D%26work_search%255Bbookmarks_count%255D%3D%26work_search%255Bsort_column%255D%3D_score%26work_search%255Bsort_direction%255D%3Ddesc">Log In</a></p>
            <div id="small_login" class="simple login">
             <form class="new_user" id="new_user_session_small" action="/users/login?return_to=%2Fworks%2Fsearch%3F%26commit%3DSearch%26work_search%255Bquery%255D%3DHarry%2BPotter%26work_search%255Btitle%255D%3D%26work_search%255Bcreators%255D%3D%26work_search%255Brevised_at%255D%3D%26work_search%255Bcomplete%255D%3D%26work_search%255Bcrossover%255D%3D%26work_search%255Bsingle_chapter%255D%3D0%26work_search%255Bword_count%255D%3D%26work_search%255Blanguage_id%255D%3D%26work_search%255Bfandom_names%255D%3D%26work_search%255Brating_ids%255D%3D%26work_search%255Bcharacter_names%255D%3D%26work_search%255Brelationship_names%255D%3D%26work_search%255Bfreeform_names%255D%3D%26work_search%255Bhits%255D%3D%26work_search%255Bkudos_count%255D%3D%26work_search%255Bcomments_count%255D%3D%26work_search%255Bbookmarks_count%255D%3D%26work_search%255Bsort_column%255D%3D_score%26work_search%255Bsort_direction%255D%3Ddesc" accept-charset="UTF-8" method="post">
              <input type="hidden" name="authenticity_token" value="vB0uJG6lZ5jIKuPbOB5OaR3rYeJtoBvolgbpgpU0DCq7orbAhzF2wWJomLRuh0iV2NvfY1rfcryeKzGqO1ESqw" autocomplete="off">
              <dl>
               <dt>
                <label for="user_session_login_small">Username or email:</label>
               </dt>
               <dd>
                <input autocomplete="on" id="user_session_login_small" type="text" name="user[login]">
               </dd>
               <dt>
                <label for="user_session_password_small">Password:</label>
               </dt>
               <dd>
                <input id="user_session_password_small" type="password" name="user[password]">
               </dd>
              </dl>
              <p class="submit actions"><label for="user_remember_me_small" class="action"> <input type="checkbox" name="user[remember_me]" id="user_remember_me_small" value="1">Remember Me </label> <input type="submit" name="commit" value="Log In"></p>
             </form>
             <ul class="footnote actions">
              <li><a href="/users/password/new">Forgot password?</a></li>
              <li><a href="/invite_requests">Get an Invitation</a></li>
             </ul>
            </div>
           </div>
           <nav aria-label="Site">
            <ul class="primary navigation actions">
             <li class="dropdown"><a href="/menu/fandoms">Fandoms</a>
              <ul class="menu">
               <li><a href="/media">All Fandoms</a></li>
               <li id="medium_5"><a href="/media/Anime%20*a*%20Manga/fandoms">Anime &amp; Manga</a></li>
               <li id="medium_3"><a href="/media/Books%20*a*%20Literature/fandoms">Books &amp; Literature</a></li>
               <li id="medium_4"><a href="/media/Cartoons%20*a*%20Comics%20*a*%20Graphic%20Novels/fandoms">Cartoons &amp; Comics &amp; Graphic Novels</a></li>
               <li id="medium_7"><a href="/media/Celebrities%20*a*%20Real%20People/fandoms">Celebrities &amp; Real People</a></li>
               <li id="medium_2"><a href="/media/Movies/fandoms">Movies</a></li>
               <li id="medium_6"><a href="/media/Music%20*a*%20Bands/fandoms">Music &amp; Bands</a></li>
               <li id="medium_8"><a href="/media/Other%20Media/fandoms">Other Media</a></li>
               <li id="medium_30198"><a href="/media/Theater/fandoms">Theater</a></li>
               <li id="medium_1"><a href="/media/TV%20Shows/fandoms">TV Shows</a></li>
               <li id="medium_476"><a href="/media/Video%20Games/fandoms">Video Games</a></li>
               <li id="medium_9971"><a href="/media/Uncategorized%20Fandoms/fandoms">Uncategorized Fandoms</a></li>
              </ul></li>
             <li class="dropdown"><a href="/menu/browse">Browse</a>
              <ul class="menu">
               <li><a href="/works">Works</a></li>
               <li><a href="/bookmarks">Bookmarks</a></li>
               <li><a href="/tags">Tags</a></li>
               <li><a href="/collections">Collections</a></li>
              </ul></li>
             <li class="dropdown"><a href="/menu/search">Search</a>
              <ul class="menu">
               <li><a href="/works/search">Works</a></li>
               <li><a href="/bookmarks/search">Bookmarks</a></li>
               <li><a href="/tags/search">Tags</a></li>
               <li><a href="/people/search">People</a></li>
              </ul></li>
             <li class="dropdown"><a href="/menu/about">About</a>
              <ul class="menu">
               <li><a href="/about">About Us</a></li>
               <li><a href="/admin_posts">News</a></li>
               <li><a href="/faq">FAQ</a></li>
               <li><a href="/wrangling_guidelines">Wrangling Guidelines</a></li>
               <li><a href="/donate">Donate or Volunteer</a></li>
              </ul></li>
             <li class="search">
              <form class="search" id="search" role="search" aria-label="Work" action="/works/search" accept-charset="UTF-8" method="get">
               <fieldset>
                <p><label class="landmark" for="site_search">Work Search</label> <input class="text" id="site_search" aria-describedby="site_search_tooltip" type="text" name="work_search[query]"> <span class="tip" role="tooltip" id="site_search_tooltip">tip: "uchiha sasuke/uzumaki naruto" angst kudos&gt;10</span> <span class="submit actions"><input type="submit" value="Search" class="button"></span></p>
               </fieldset>
              </form></li>
            </ul>
           </nav>
           <div class="clear"></div>
          </header><!-- END header -->
          <div id="inner" class="wrapper">
           <!-- BEGIN sidebar --> <!-- END sidebar --> <!-- BEGIN main -->
           <div id="main" class="works-search region" role="main">
            <div class="flash"></div><!--Descriptive page names, messages and instructions-->
            <h2 class="heading">Search Results</h2>
            <h4 class="heading">You searched for: Harry Potter sort by: best match descending</h4><!--/descriptions--> <!--subnav-->
            <ul class="navigation actions" role="navigation">
             <li><a href="/works/search?commit=Search&amp;edit_search=true&amp;work_search%5Bbookmarks_count%5D=&amp;work_search%5Bcharacter_names%5D=&amp;work_search%5Bcomments_count%5D=&amp;work_search%5Bcomplete%5D=&amp;work_search%5Bcreators%5D=&amp;work_search%5Bcrossover%5D=&amp;work_search%5Bfandom_names%5D=&amp;work_search%5Bfreeform_names%5D=&amp;work_search%5Bhits%5D=&amp;work_search%5Bkudos_count%5D=&amp;work_search%5Blanguage_id%5D=&amp;work_search%5Bquery%5D=Harry+Potter&amp;work_search%5Brating_ids%5D=&amp;work_search%5Brelationship_names%5D=&amp;work_search%5Brevised_at%5D=&amp;work_search%5Bsingle_chapter%5D=0&amp;work_search%5Bsort_column%5D=_score&amp;work_search%5Bsort_direction%5D=desc&amp;work_search%5Btitle%5D=&amp;work_search%5Bword_count%5D=">Edit Your Search</a></li>
            </ul><!--/subnav--> <!--main content-->
            <h3 class="heading">589,685 Found <a class="help symbol question modal" title="Work search results help" href="/help/work-search-results-help.html"><span class="symbol question"><span>?</span></span></a></h3>
            <h3 class="landmark heading">Works List</h3>
            <ol class="work index group">
             <li id="work_73477941" class="work blurb group work-73477941 user-10830884" role="article"><!--title, author, fandom-->
              <div class="header module">
               <!-- updated_at=1761967690 -->
               <h4 class="heading"><a href="/works/73477941">Harry Potter? Harry Potter-Winchester</a> by <!-- do not cache --> <a rel="author" href="/users/Reggie4257/pseuds/Reggie4257">Reggie4257</a></h4>
               <h5 class="fandoms heading"><span class="landmark">Fandoms:</span> <a class="tag" href="/tags/Harry%20Potter%20-%20J*d*%20K*d*%20Rowling/works">Harry Potter - J. K. Rowling</a>, <a class="tag" href="/tags/Supernatural%20(TV%202005)/works">Supernatural (TV 2005)</a> &nbsp;</h5><!--required tags-->
               <ul class="required-tags">
                <li><a class="help symbol question modal" title="Symbols key" href="/help/symbols-key.html"><span class="rating-teen rating" title="Teen And Up Audiences"><span class="text">Teen And Up Audiences</span></span></a></li>
                <li><a class="help symbol question modal" title="Symbols key" href="/help/symbols-key.html"><span class="warning-no warnings" title="No Archive Warnings Apply"><span class="text">No Archive Warnings Apply</span></span></a></li>
                <li><a class="help symbol question modal" title="Symbols key" href="/help/symbols-key.html"><span class="category-multi category" title="F/M, Gen"><span class="text">F/M, Gen</span></span></a></li>
                <li><a class="help symbol question modal" title="Symbols key" href="/help/symbols-key.html"><span class="complete-no iswip" title="Work in Progress"><span class="text">Work in Progress</span></span></a></li>
               </ul>
               <p class="datetime">01 Nov 2025</p>
              </div><!--warnings again, cast, freeform tags-->
              <h6 class="landmark heading">Tags</h6>
              <ul class="tags commas">
               <li class="warnings"><strong><a class="tag" href="/tags/No%20Archive%20Warnings%20Apply/works">No Archive Warnings Apply</a></strong></li>
               <li class="relationships"><a class="tag" href="/tags/Harry%20Potter%20*a*%20Dean%20Winchester%20*a*%20Sam%20Winchester/works">Harry Potter &amp; Dean Winchester &amp; Sam Winchester</a></li>
               <li class="relationships"><a class="tag" href="/tags/Harry%20Potter%20*a*%20Sam%20Winchester/works">Harry Potter &amp; Sam Winchester</a></li>
               <li class="relationships"><a class="tag" href="/tags/Harry%20Potter%20*a*%20Dean%20Winchester/works">Harry Potter &amp; Dean Winchester</a></li>
               <li class="relationships"><a class="tag" href="/tags/Dean%20Winchester%20*a*%20Sam%20Winchester/works">Dean Winchester &amp; Sam Winchester</a></li>
               <li class="relationships"><a class="tag" href="/tags/Harry%20Potter%20*a*%20John%20Winchester/works">Harry Potter &amp; John Winchester</a></li>
               <li class="relationships"><a class="tag" href="/tags/Dean%20Winchester%20*a*%20John%20Winchester/works">Dean Winchester &amp; John Winchester</a></li>
               <li class="relationships"><a class="tag" href="/tags/John%20Winchester%20*a*%20Sam%20Winchester/works">John Winchester &amp; Sam Winchester</a></li>
               <li class="relationships"><a class="tag" href="/tags/Harry%20Potter%20*a*%20Dean%20Winchester%20*a*%20John%20Winchester%20*a*%20Sam%20Winchester/works">Harry Potter &amp; Dean Winchester &amp; John Winchester &amp; Sam Winchester</a></li>
               <li class="relationships"><a class="tag" href="/tags/Bobby%20Singer%20*a*%20Dean%20Winchester%20*a*%20Sam%20Winchester/works">Bobby Singer &amp; Dean Winchester &amp; Sam Winchester</a></li>
               <li class="relationships"><a class="tag" href="/tags/Bobby%20Singer%20*a*%20Dean%20Winchester/works">Bobby Singer &amp; Dean Winchester</a></li>
               <li class="relationships"><a class="tag" href="/tags/Harry%20Potter%20*a*%20Bobby%20Singer%20(Supernatural)/works">Harry Potter &amp; Bobby Singer (Supernatural)</a></li>
               <li class="relationships"><a class="tag" href="/tags/Bobby%20Singer%20*a*%20Sam%20Winchester/works">Bobby Singer &amp; Sam Winchester</a></li>
               <li class="relationships"><a class="tag" href="/tags/Bobby%20Singer%20*a*%20John%20Winchester/works">Bobby Singer &amp; John Winchester</a></li>
               <li class="characters"><a class="tag" href="/tags/Harry%20Potter/works">Harry Potter</a></li>
               <li class="characters"><a class="tag" href="/tags/Dean%20Winchester/works">Dean Winchester</a></li>
               <li class="characters"><a class="tag" href="/tags/Sam%20Winchester/works">Sam Winchester</a></li>
               <li class="characters"><a class="tag" href="/tags/John%20Winchester/works">John Winchester</a></li>
               <li class="characters"><a class="tag" href="/tags/Bobby%20Singer%20(Supernatural)/works">Bobby Singer (Supernatural)</a></li>
               <li class="characters"><a class="tag" href="/tags/Remus%20Lupin/works">Remus Lupin</a></li>
               <li class="characters"><a class="tag" href="/tags/Gabriel%20(Supernatural)/works">Gabriel (Supernatural)</a></li>
               <li class="characters"><a class="tag" href="/tags/Gabriel's%20Dog%20(Supernatural:%20Tall%20Tales)/works">Gabriel's Dog (Supernatural: Tall Tales)</a></li>
               <li class="freeforms"><a class="tag" href="/tags/James%20Potter%20is%20Not%20Harry%20Potter's%20Parent/works">James Potter is Not Harry Potter's Parent</a></li>
               <li class="freeforms"><a class="tag" href="/tags/Harry%20Potter%20is%20a%20Winchester%20(Supernatural)/works">Harry Potter is a Winchester (Supernatural)</a></li>
               <li class="freeforms"><a class="tag" href="/tags/Harry%20Potter%20is%20a%20Little%20Shit/works">Harry Potter is a Little Shit</a></li>
               <li class="freeforms"><a class="tag" href="/tags/Dean%20Winchester%20is%20Sam%20Winchester's%20Parent/works">Dean Winchester is Sam Winchester's Parent</a></li>
               <li class="freeforms"><a class="tag" href="/tags/Protective%20Dean%20Winchester/works">Protective Dean Winchester</a></li>
               <li class="freeforms"><a class="tag" href="/tags/Dean%20Winchester%20is%20Harry%20Potter's%20Parent/works">Dean Winchester is Harry Potter's Parent</a></li>
               <li class="freeforms"><a class="tag" href="/tags/Dean%20Winchester%20is%20Protective%20of%20Sam%20Winchester/works">Dean Winchester is Protective of Sam Winchester</a></li>
               <li class="freeforms"><a class="tag" href="/tags/Dean%20Winchester%20and%20Sam%20Winchester%20Use%20Their%20Words/works">Dean Winchester and Sam Winchester Use Their Words</a></li>
               <li class="freeforms"><a class="tag" href="/tags/John%20Winchester's%20A+%20Parenting/works">John Winchester's A+ Parenting</a></li>
               <li class="freeforms"><a class="tag" href="/tags/John%20Winchester%20Tries%20to%20be%20a%20Good%20Parent/works">John Winchester Tries to be a Good Parent</a></li>
               <li class="freeforms"><a class="tag" href="/tags/yet%20-%20Freeform/works">yet - Freeform</a></li>
               <li class="freeforms"><a class="tag" href="/tags/Absent%20John%20Winchester/works">Absent John Winchester</a></li>
               <li class="freeforms"><a class="tag" href="/tags/Bobby%20Singer%20is%20Dean%20and%20Sam%20Winchester's%20Parent/works">Bobby Singer is Dean and Sam Winchester's Parent</a></li>
               <li class="freeforms"><a class="tag" href="/tags/Parental%20Bobby%20Singer%20(Supernatural)/works">Parental Bobby Singer (Supernatural)</a></li>
               <li class="freeforms"><a class="tag" href="/tags/Protective%20Bobby%20Singer%20(Supernatural)/works">Protective Bobby Singer (Supernatural)</a></li>
               <li class="freeforms"><a class="tag" href="/tags/Awesome%20Bobby%20Singer%20(Supernatural)/works">Awesome Bobby Singer (Supernatural)</a></li>
               <li class="freeforms"><a class="tag" href="/tags/Harry%20Potter%20is%20Not%20a%20Potter/works">Harry Potter is Not a Potter</a></li>
               <li class="freeforms"><a class="tag" href="/tags/Pre-Hogwarts/works">Pre-Hogwarts</a></li>
               <li class="freeforms"><a class="tag" href="/tags/Kid%20Fic/works">Kid Fic</a></li>
               <li class="freeforms"><a class="tag" href="/tags/at%20least%20somewhat/works">at least somewhat</a></li>
               <li class="freeforms"><a class="tag" href="/tags/Time%20Skips/works">Time Skips</a></li>
               <li class="freeforms"><a class="tag" href="/tags/Fix-It%20of%20Sorts/works">Fix-It of Sorts</a></li>
               <li class="freeforms"><a class="tag" href="/tags/Tags%20May%20Change/works">Tags May Change</a></li>
              </ul><!--summary-->
              <h6 class="landmark heading">Summary</h6>
              <blockquote class="userstuff summary">
               <p>What if everything is the same...except that Harry is actually John Winchester's kid? John wasn't careful enough, and now he has a toddler on top of his other two sons and his hunt for the bastard who killed his wife.</p>
               <p>Rated Teen and Up for language and potential for hunt details.</p>
              </blockquote><!--stats-->
              <dl class="stats">
               <dt class="language">
                Language:
               </dt>
               <dd class="language" lang="en">
                English
               </dd>
               <dt class="words">
                Words:
               </dt>
               <dd class="words">
                1,025
               </dd>
               <dt class="chapters">
                Chapters:
               </dt>
               <dd class="chapters">
                1/?
               </dd>
               <dt class="kudos">
                Kudos:
               </dt>
               <dd class="kudos">
                <a href="/works/73477941#kudos">151</a>
               </dd>
               <dt class="bookmarks">
                Bookmarks:
               </dt>
               <dd class="bookmarks">
                <a href="/works/73477941/bookmarks">40</a>
               </dd>
               <dt class="hits">
                Hits:
               </dt>
               <dd class="hits">
                2,881
               </dd>
              </dl></li>
             <li id="work_41952030" class="work blurb group work-41952030 user-12149932" role="article"><!--title, author, fandom-->
              <div class="header module">
               <!-- updated_at=1668539075 -->
               <h4 class="heading"><a href="/works/41952030">Harry Potter and Harry Potter</a> by <!-- do not cache --> dark dhampir [archived by <a rel="author" href="/users/HarryPotterFanFicArchive_Archivist/pseuds/HarryPotterFanFicArchive_Archivist">HarryPotterFanFicArchive_Archivist</a>]</h4>
               <h5 class="fandoms heading"><span class="landmark">Fandoms:</span> <a class="tag" href="/tags/Harry%20Potter%20-%20J*d*%20K*d*%20Rowling/works">Harry Potter - J. K. Rowling</a> &nbsp;</h5><!--required tags-->
               <ul class="required-tags">
                <li><a class="help symbol question modal" title="Symbols key" href="/help/symbols-key.html"><span class="rating-explicit rating" title="Explicit"><span class="text">Explicit</span></span></a></li>
                <li><a class="help symbol question modal" title="Symbols key" href="/help/symbols-key.html"><span class="warning-choosenotto warnings" title="Creator Chose Not To Use Archive Warnings"><span class="text">Creator Chose Not To Use Archive Warnings</span></span></a></li>
                <li><a class="help symbol question modal" title="Symbols key" href="/help/symbols-key.html"><span class="category-multi category" title="F/M, Multi"><span class="text">F/M, Multi</span></span></a></li>
                <li><a class="help symbol question modal" title="Symbols key" href="/help/symbols-key.html"><span class="complete-yes iswip" title="Complete Work"><span class="text">Complete Work</span></span></a></li>
               </ul>
               <p class="datetime">21 Sep 2010</p>
              </div><!--warnings again, cast, freeform tags-->
              <h6 class="landmark heading">Tags</h6>
              <ul class="tags commas">
               <li class="warnings"><strong><a class="tag" href="/tags/Choose%20Not%20To%20Use%20Archive%20Warnings/works">Creator Chose Not To Use Archive Warnings</a></strong></li>
               <li class="characters"><a class="tag" href="/tags/Harry%20Potter/works">Harry Potter</a></li>
               <li class="freeforms"><a class="tag" href="/tags/Character%20Bashing/works">Character Bashing</a></li>
               <li class="freeforms"><a class="tag" href="/tags/Betrayal/works">Betrayal</a></li>
               <li class="freeforms"><a class="tag" href="/tags/Bonding/works">Bonding</a></li>
               <li class="freeforms"><a class="tag" href="/tags/HPFanFicArchive*d*com's%20Challenges/works">HPFanFicArchive.com's Challenges</a></li>
               <li class="freeforms"><a class="tag" href="/tags/Crossover/works">Crossover</a></li>
               <li class="freeforms"><a class="tag" href="/tags/Drama/works">Drama</a></li>
               <li class="freeforms"><a class="tag" href="/tags/Erotica/works">Erotica</a></li>
               <li class="freeforms"><a class="tag" href="/tags/Harems/works">Harems</a></li>
               <li class="freeforms"><a class="tag" href="/tags/Multi%20Pairing/works">Multi Pairing</a></li>
               <li class="freeforms"><a class="tag" href="/tags/Heir/works">Heir</a></li>
               <li class="freeforms"><a class="tag" href="/tags/Hogwarts%20Houses/works">Hogwarts Houses</a></li>
               <li class="freeforms"><a class="tag" href="/tags/manipulative/works">manipulative</a></li>
               <li class="freeforms"><a class="tag" href="/tags/mentor/works">mentor</a></li>
               <li class="freeforms"><a class="tag" href="/tags/Powerful%20Character/works">Powerful Character</a></li>
               <li class="freeforms"><a class="tag" href="/tags/Time%20Travel/works">Time Travel</a></li>
               <li class="freeforms"><a class="tag" href="/tags/War/works">War</a></li>
               <li class="freeforms"><a class="tag" href="/tags/Adventure/works">Adventure</a></li>
               <li class="freeforms"><a class="tag" href="/tags/Adult%20Content/works">Adult Content</a></li>
              </ul><!--summary-->
              <h6 class="landmark heading">Summary</h6>
              <blockquote class="userstuff summary">
               <p>The Battle of the Department of Mysteries goes a little differently when Harry gets some help from an alternate reality's verison of himself. The alternate Harry has come to save his others self from a dark wizard from his own reality, but he has other news. Ron and Dumbledore are Harry's enemies, and . . . Harry has twelve wives?! Crossover with the DArk Tower Saga and the Stargate franchise. Warning: has femslash.</p>
              </blockquote><!--stats-->
              <dl class="stats">
               <dt class="language">
                Language:
               </dt>
               <dd class="language" lang="en">
                English
               </dd>
               <dt class="words">
                Words:
               </dt>
               <dd class="words">
                11,053
               </dd>
               <dt class="chapters">
                Chapters:
               </dt>
               <dd class="chapters">
                <a href="/works/41952030/chapters/105298974">4</a>/4
               </dd>
               <dt class="collections">
                Collections:
               </dt>
               <dd class="collections">
                <a href="/works/41952030/collections">1</a>
               </dd>
               <dt class="comments">
                Comments:
               </dt>
               <dd class="comments">
                <a href="/works/41952030?show_comments=true&amp;view_full_work=true#comments">28</a>
               </dd>
               <dt class="kudos">
                Kudos:
               </dt>
               <dd class="kudos">
                <a href="/works/41952030?view_full_work=true#kudos">281</a>
               </dd>
               <dt class="bookmarks">
                Bookmarks:
               </dt>
               <dd class="bookmarks">
                <a href="/works/41952030/bookmarks">38</a>
               </dd>
               <dt class="hits">
                Hits:
               </dt>
               <dd class="hits">
                27,444
               </dd>
              </dl></li>
             <li id="work_13871511" class="work blurb group work-13871511 user-3542172" role="article"><!--title, author, fandom-->
              <div class="header module">
               <!-- updated_at=1771522010 -->
               <h4 class="heading"><a href="/works/13871511">Harry Potter meets Harry Potter</a> by <!-- do not cache --> <a rel="author" href="/users/CaelynAilene/pseuds/CaelynAilene">CaelynAilene</a></h4>
               <h5 class="fandoms heading"><span class="landmark">Fandoms:</span> <a class="tag" href="/tags/Harry%20Potter%20-%20J*d*%20K*d*%20Rowling/works">Harry Potter - J. K. Rowling</a> &nbsp;</h5><!--required tags-->
               <ul class="required-tags">
                <li><a class="help symbol question modal" title="Symbols key" href="/help/symbols-key.html"><span class="rating-general-audience rating" title="General Audiences"><span class="text">General Audiences</span></span></a></li>
                <li><a class="help symbol question modal" title="Symbols key" href="/help/symbols-key.html"><span class="warning-no warnings" title="No Archive Warnings Apply"><span class="text">No Archive Warnings Apply</span></span></a></li>
                <li><a class="help symbol question modal" title="Symbols key" href="/help/symbols-key.html"><span class="category-none category" title="No category"><span class="text">No category</span></span></a></li>
                <li><a class="help symbol question modal" title="Symbols key" href="/help/symbols-key.html"><span class="complete-yes iswip" title="Complete Work"><span class="text">Complete Work</span></span></a></li>
               </ul>
               <p class="datetime">19 Feb 2026</p>
              </div><!--warnings again, cast, freeform tags-->
              <h6 class="landmark heading">Tags</h6>
              <ul class="tags commas">
               <li class="warnings"><strong><a class="tag" href="/tags/No%20Archive%20Warnings%20Apply/works">No Archive Warnings Apply</a></strong></li>
               <li class="characters"><a class="tag" href="/tags/Harry%20Potter/works">Harry Potter</a></li>
               <li class="characters"><a class="tag" href="/tags/Draco%20Malfoy/works">Draco Malfoy</a></li>
               <li class="characters"><a class="tag" href="/tags/Ron%20Weasley/works">Ron Weasley</a></li>
               <li class="characters"><a class="tag" href="/tags/Hermione%20Granger/works">Hermione Granger</a></li>
               <li class="characters"><a class="tag" href="/tags/George%20Weasley/works">George Weasley</a></li>
               <li class="characters"><a class="tag" href="/tags/Fred%20Weasley/works">Fred Weasley</a></li>
              </ul><!--summary-->
              <h6 class="landmark heading">Summary</h6>
              <blockquote class="userstuff summary">
               <p>What happens when Harry and the gang discover first the muggle books, then the movies, and finally fanfiction? Hilarity and drama of course. (note: there are mentions of same sex couples)</p>
              </blockquote><!--stats-->
              <dl class="stats">
               <dt class="language">
                Language:
               </dt>
               <dd class="language" lang="en">
                English
               </dd>
               <dt class="words">
                Words:
               </dt>
               <dd class="words">
                8,579
               </dd>
               <dt class="chapters">
                Chapters:
               </dt>
               <dd class="chapters">
                <a href="/works/13871511/chapters/209603181">8</a>/8
               </dd>
               <dt class="comments">
                Comments:
               </dt>
               <dd class="comments">
                <a href="/works/13871511?show_comments=true&amp;view_full_work=true#comments">206</a>
               </dd>
               <dt class="kudos">
                Kudos:
               </dt>
               <dd class="kudos">
                <a href="/works/13871511?view_full_work=true#kudos">2,802</a>
               </dd>
               <dt class="bookmarks">
                Bookmarks:
               </dt>
               <dd class="bookmarks">
                <a href="/works/13871511/bookmarks">174</a>
               </dd>
               <dt class="hits">
                Hits:
               </dt>
               <dd class="hits">
                60,440
               </dd>
              </dl></li>
             <li id="work_63679033" class="work blurb group work-63679033 user-20069533" role="article"><!--title, author, fandom-->
              <div class="header module">
               <!-- updated_at=1741419627 -->
               <h4 class="heading"><a href="/works/63679033">Harry Potter</a> by <!-- do not cache --> <a rel="author" href="/users/AstrioBlack/pseuds/AstrioBlack">AstrioBlack</a></h4>
               <h5 class="fandoms heading"><span class="landmark">Fandoms:</span> <a class="tag" href="/tags/Harry%20Potter%20-%20J*d*%20K*d*%20Rowling/works">Harry Potter - J. K. Rowling</a> &nbsp;</h5><!--required tags-->
               <ul class="required-tags">
                <li><a class="help symbol question modal" title="Symbols key" href="/help/symbols-key.html"><span class="rating-notrated rating" title="Not Rated"><span class="text">Not Rated</span></span></a></li>
                <li><a class="help symbol question modal" title="Symbols key" href="/help/symbols-key.html"><span class="warning-yes warnings" title="Creator Chose Not To Use Archive Warnings, Graphic Depictions Of Violence"><span class="text">Creator Chose Not To Use Archive Warnings, Graphic Depictions Of Violence</span></span></a></li>
                <li><a class="help symbol question modal" title="Symbols key" href="/help/symbols-key.html"><span class="category-multi category" title="M/M, Multi"><span class="text">M/M, Multi</span></span></a></li>
                <li><a class="help symbol question modal" title="Symbols key" href="/help/symbols-key.html"><span class="complete-yes iswip" title="Complete Work"><span class="text">Complete Work</span></span></a></li>
               </ul>
               <p class="datetime">08 Mar 2025</p>
              </div><!--warnings again, cast, freeform tags-->
              <h6 class="landmark heading">Tags</h6>
              <ul class="tags commas">
               <li class="warnings"><strong><a class="tag" href="/tags/Choose%20Not%20To%20Use%20Archive%20Warnings/works">Creator Chose Not To Use Archive Warnings</a></strong></li>
               <li class="warnings"><strong><a class="tag" href="/tags/Graphic%20Depictions%20Of%20Violence/works">Graphic Depictions Of Violence</a></strong></li>
               <li class="relationships"><a class="tag" href="/tags/Harry%20Potter*s*Severus%20Snape/works">Harry Potter/Severus Snape</a></li>
               <li class="relationships"><a class="tag" href="/tags/Draco%20Malfoy*s*Fred%20Weasley*s*George%20Weasley/works">Draco Malfoy/Fred Weasley/George Weasley</a></li>
               <li class="characters"><a class="tag" href="/tags/Severus%20Snape/works">Severus Snape</a></li>
               <li class="characters"><a class="tag" href="/tags/Harry%20Potter/works">Harry Potter</a></li>
               <li class="characters"><a class="tag" href="/tags/Ron%20Weasley/works">Ron Weasley</a></li>
               <li class="characters"><a class="tag" href="/tags/Fred%20Weasley/works">Fred Weasley</a></li>
               <li class="characters"><a class="tag" href="/tags/George%20Weasley/works">George Weasley</a></li>
               <li class="characters"><a class="tag" href="/tags/Lucius%20Malfoy/works">Lucius Malfoy</a></li>
               <li class="characters"><a class="tag" href="/tags/Tom%20Riddle%20%7C%20Voldemort/works">Tom Riddle | Voldemort</a></li>
               <li class="freeforms"><a class="tag" href="/tags/I%20Wrote%20This%20Instead%20of%20Sleeping/works">I Wrote This Instead of Sleeping</a></li>
              </ul><!--summary--> <!--stats-->
              <dl class="stats">
               <dt class="language">
                Language:
               </dt>
               <dd class="language" lang="en">
                English
               </dd>
               <dt class="words">
                Words:
               </dt>
               <dd class="words">
                1,016
               </dd>
               <dt class="chapters">
                Chapters:
               </dt>
               <dd class="chapters">
                1/1
               </dd>
               <dt class="comments">
                Comments:
               </dt>
               <dd class="comments">
                <a href="/works/63679033?show_comments=true#comments">2</a>
               </dd>
               <dt class="kudos">
                Kudos:
               </dt>
               <dd class="kudos">
                <a href="/works/63679033#kudos">68</a>
               </dd>
               <dt class="bookmarks">
                Bookmarks:
               </dt>
               <dd class="bookmarks">
                <a href="/works/63679033/bookmarks">4</a>
               </dd>
               <dt class="hits">
                Hits:
               </dt>
               <dd class="hits">
                2,198
               </dd>
              </dl></li>
             <li id="work_57017317" class="work blurb group work-57017317 user-9" role="article"><!--title, author, fandom-->
              <div class="header module">
               <!-- updated_at=1725178237 -->
               <h4 class="heading"><a href="/works/57017317">harry potter</a> by <!-- do not cache --> <a rel="author" href="/users/orphan_account/pseuds/fishyman101">fishyman101 (orphan_account)</a></h4>
               <h5 class="fandoms heading"><span class="landmark">Fandoms:</span> <a class="tag" href="/tags/Harry%20Potter%20-%20J*d*%20K*d*%20Rowling/works">Harry Potter - J. K. Rowling</a> &nbsp;</h5><!--required tags-->
               <ul class="required-tags">
                <li><a class="help symbol question modal" title="Symbols key" href="/help/symbols-key.html"><span class="rating-teen rating" title="Teen And Up Audiences"><span class="text">Teen And Up Audiences</span></span></a></li>
                <li><a class="help symbol question modal" title="Symbols key" href="/help/symbols-key.html"><span class="warning-yes warnings" title="Graphic Depictions Of Violence, Major Character Death"><span class="text">Graphic Depictions Of Violence, Major Character Death</span></span></a></li>
                <li><a class="help symbol question modal" title="Symbols key" href="/help/symbols-key.html"><span class="category-gen category" title="Gen"><span class="text">Gen</span></span></a></li>
                <li><a class="help symbol question modal" title="Symbols key" href="/help/symbols-key.html"><span class="complete-yes iswip" title="Complete Work"><span class="text">Complete Work</span></span></a></li>
               </ul>
               <p class="datetime">30 Jun 2024</p>
              </div><!--warnings again, cast, freeform tags-->
              <h6 class="landmark heading">Tags</h6>
              <ul class="tags commas">
               <li class="warnings"><strong><a class="tag" href="/tags/Graphic%20Depictions%20Of%20Violence/works">Graphic Depictions Of Violence</a></strong></li>
               <li class="warnings"><strong><a class="tag" href="/tags/Major%20Character%20Death/works">Major Character Death</a></strong></li>
               <li class="characters"><a class="tag" href="/tags/Harry%20Potter/works">Harry Potter</a></li>
               <li class="characters"><a class="tag" href="/tags/Albus%20Dumbledore/works">Albus Dumbledore</a></li>
               <li class="characters"><a class="tag" href="/tags/Draco%20Malfoy/works">Draco Malfoy</a></li>
               <li class="freeforms"><a class="tag" href="/tags/Headshot/works">Headshot</a></li>
               <li class="freeforms"><a class="tag" href="/tags/Awesome/works">Awesome</a></li>
               <li class="freeforms"><a class="tag" href="/tags/Funny/works">Funny</a></li>
               <li class="freeforms"><a class="tag" href="/tags/Albus%20Dumbledore%20Being%20an%20Idiot/works">Albus Dumbledore Being an Idiot</a></li>
               <li class="freeforms"><a class="tag" href="/tags/Book%204:%20Harry%20Potter%20and%20the%20Goblet%20of%20Fire/works">Book 4: Harry Potter and the Goblet of Fire</a></li>
               <li class="freeforms"><a class="tag" href="/tags/Harry%20Potter%20Dies/works">Harry Potter Dies</a></li>
               <li class="freeforms"><a class="tag" href="/tags/harry%20potter%20gets%20shot/works">harry potter gets shot</a></li>
               <li class="freeforms"><a class="tag" href="/tags/draco%20kills%20Dumbledore/works">draco kills Dumbledore</a></li>
              </ul><!--summary-->
              <h6 class="landmark heading">Summary</h6>
              <blockquote class="userstuff summary">
               <p>headshot</p>
              </blockquote><!--stats-->
              <dl class="stats">
               <dt class="language">
                Language:
               </dt>
               <dd class="language" lang="en">
                English
               </dd>
               <dt class="words">
                Words:
               </dt>
               <dd class="words">
                22
               </dd>
               <dt class="chapters">
                Chapters:
               </dt>
               <dd class="chapters">
                <a href="/works/57017317/chapters/145005049">2</a>/2
               </dd>
               <dt class="comments">
                Comments:
               </dt>
               <dd class="comments">
                <a href="/works/57017317?show_comments=true&amp;view_full_work=true#comments">10</a>
               </dd>
               <dt class="kudos">
                Kudos:
               </dt>
               <dd class="kudos">
                <a href="/works/57017317?view_full_work=true#kudos">45</a>
               </dd>
               <dt class="bookmarks">
                Bookmarks:
               </dt>
               <dd class="bookmarks">
                <a href="/works/57017317/bookmarks">1</a>
               </dd>
               <dt class="hits">
                Hits:
               </dt>$j
               <dd class="hits">
                1,681
               </dd>
              </dl></li>
             <li id="work_8493349" class="work blurb group work-8493349 user-2512612" role="article"><!--title, author, fandom-->
              <div class="header module">
               <!-- updated_at=1668539084 -->
               <h4 class="heading"><a href="/works/8493349">Harry Potter</a> by <!-- do not cache --> <a rel="author" href="/users/Clove_The_Hufflepuff/pseuds/Clove_The_Hufflepuff">Clove_The_Hufflepuff</a></h4>
               <h5 class="fandoms heading"><span class="landmark">Fandoms:</span> <a class="tag" href="/tags/Harry%20Potter%20-%20J*d*%20K*d*%20Rowling/works">Harry Potter - J. K. Rowling</a> &nbsp;</h5><!--required tags-->
               <ul class="required-tags">
                <li><a class="help symbol question modal" title="Symbols key" href="/help/symbols-key.html"><span class="rating-notrated rating" title="Not Rated"><span class="text">Not Rated</span></span></a></li>
                <li><a class="help symbol question modal" title="Symbols key" href="/help/symbols-key.html"><span class="warning-no warnings" title="No Archive Warnings Apply"><span class="text">No Archive Warnings Apply</span></span></a></li>
                <li><a class="help symbol question modal" title="Symbols key" href="/help/symbols-key.html"><span class="category-het category" title="F/M"><span class="text">F/M</span></span></a></li>
                <li><a class="help symbol question modal" title="Symbols key" href="/help/symbols-key.html"><span class="complete-yes iswip" title="Complete Work"><span class="text">Complete Work</span></span></a></li>
               </ul>
               <p class="datetime">07 Nov 2016</p>
              </div><!--warnings again, cast, freeform tags-->
              <h6 class="landmark heading">Tags</h6>
              <ul class="tags commas">
               <li class="warnings"><strong><a class="tag" href="/tags/No%20Archive%20Warnings%20Apply/works">No Archive Warnings Apply</a></strong></li>
               <li class="relationships"><a class="tag" href="/tags/James%20Potter*s*Lily%20Evans%20Potter/works">James Potter/Lily Evans Potter</a></li>
               <li class="characters"><a class="tag" href="/tags/Harry%20Potter/works">Harry Potter</a></li>
               <li class="characters"><a class="tag" href="/tags/James%20Potter/works">James Potter</a></li>
               <li class="characters"><a class="tag" href="/tags/Lily%20Evans%20Potter/works">Lily Evans Potter</a></li>
               <li class="freeforms"><a class="tag" href="/tags/HarryPov/works">HarryPov</a></li>
              </ul><!--summary-->
              <h6 class="landmark heading">Summary</h6>
              <blockquote class="userstuff summary">
               <p>In which Harry remembers Lily and James as a baby</p>
               <p>These are 100 word Drabbles that i have written for two reasons. The first is because my Laptop is broken which makes it hard editing my stories on my phone. The Second is because it helps inspire me to write more.</p>
               <p>I will take requests just PM me or leave a comment. (It may take awhile to upload)</p>
               <p>Enjoy and don't forget to leave a Kudo ;)</p>
              </blockquote>
              <h6 class="landmark heading">Series</h6>
              <ul class="series">
               <li>Part <strong>1</strong> of <a href="/series/581461">MultiFandom Drabbles</a></li>
              </ul><!--stats-->
              <dl class="stats">
               <dt class="language">
                Language:
               </dt>
               <dd class="language" lang="en">
                English
               </dd>
               <dt class="words">
                Words:
               </dt>
               <dd class="words">
                100
               </dd>
               <dt class="chapters">
                Chapters:
               </dt>
               <dd class="chapters">
                1/1
               </dd>
               <dt class="comments">
                Comments:
               </dt>
               <dd class="comments">
                <a href="/works/8493349?show_comments=true#comments">6</a>
               </dd>
               <dt class="kudos">
                Kudos:
               </dt>
               <dd class="kudos">
                <a href="/works/8493349#kudos">136</a>
               </dd>
               <dt class="bookmarks">
                Bookmarks:
               </dt>
               <dd class="bookmarks">
                <a href="/works/8493349/bookmarks">4</a>
               </dd>
               <dt class="hits">
                Hits:
               </dt>
               <dd class="hits">
                4,202
               </dd>
              </dl></li>
             <li id="work_66772294" class="work blurb group work-66772294 user-24075853" role="article"><!--title, author, fandom-->
              <div class="header module">
               <!-- updated_at=1771177028 -->
               <h4 class="heading"><a href="/works/66772294">Harry Potter</a> by <!-- do not cache --> <a rel="author" href="/users/luciferxclaw/pseuds/luciferxclaw">luciferxclaw</a></h4>
               <h5 class="fandoms heading"><span class="landmark">Fandoms:</span> <a class="tag" href="/tags/Harry%20Potter%20-%20J*d*%20K*d*%20Rowling/works">Harry Potter - J. K. Rowling</a> &nbsp;</h5><!--required tags-->
               <ul class="required-tags">
                <li><a class="help symbol question modal" title="Symbols key" href="/help/symbols-key.html"><span class="rating-teen rating" title="Teen And Up Audiences"><span class="text">Teen And Up Audiences</span></span></a></li>
                <li><a class="help symbol question modal" title="Symbols key" href="/help/symbols-key.html"><span class="warning-choosenotto warnings" title="Creator Chose Not To Use Archive Warnings"><span class="text">Creator Chose Not To Use Archive Warnings</span></span></a></li>
                <li><a class="help symbol question modal" title="Symbols key" href="/help/symbols-key.html"><span class="category-multi category" title="Multi"><span class="text">Multi</span></span></a></li>
                <li><a class="help symbol question modal" title="Symbols key" href="/help/symbols-key.html"><span class="complete-no iswip" title="Work in Progress"><span class="text">Work in Progress</span></span></a></li>
               </ul>
               <p class="datetime">15 Feb 2026</p>
              </div><!--warnings again, cast, freeform tags-->
              <h6 class="landmark heading">Tags</h6>
              <ul class="tags commas">
               <li class="warnings"><strong><a class="tag" href="/tags/Choose%20Not%20To%20Use%20Archive%20Warnings/works">Creator Chose Not To Use Archive Warnings</a></strong></li>
               <li class="characters"><a class="tag" href="/tags/Harry%20Potter/works">Harry Potter</a></li>
               <li class="characters"><a class="tag" href="/tags/Hermione%20Granger/works">Hermione Granger</a></li>
               <li class="characters"><a class="tag" href="/tags/Draco%20Malfoy/works">Draco Malfoy</a></li>
               <li class="characters"><a class="tag" href="/tags/Sirius%20Black/works">Sirius Black</a></li>
               <li class="characters"><a class="tag" href="/tags/Remus%20Lupin/works">Remus Lupin</a></li>
               <li class="characters"><a class="tag" href="/tags/Ron%20Weasley/works">Ron Weasley</a></li>
               <li class="characters"><a class="tag" href="/tags/Severus%20Snape/works">Severus Snape</a></li>
               <li class="characters"><a class="tag" href="/tags/James%20Potter/works">James Potter</a></li>
               <li class="characters"><a class="tag" href="/tags/Lily%20Evans%20Potter/works">Lily Evans Potter</a></li>
               <li class="characters"><a class="tag" href="/tags/Albus%20Dumbledore/works">Albus Dumbledore</a></li>
               <li class="characters"><a class="tag" href="/tags/Regulus%20Black/works">Regulus Black</a></li>
               <li class="characters"><a class="tag" href="/tags/Minerva%20McGonagall/works">Minerva McGonagall</a></li>
               <li class="characters"><a class="tag" href="/tags/Peter%20Pettigrew/works">Peter Pettigrew</a></li>
               <li class="characters"><a class="tag" href="/tags/Oliver%20Wood/works">Oliver Wood</a></li>
               <li class="characters"><a class="tag" href="/tags/freaking%20all%20of%20them%20okay/works">freaking all of them okay</a></li>
               <li class="freeforms"><a class="tag" href="/tags/pls%20guys/works">pls guys</a></li>
               <li class="freeforms"><a class="tag" href="/tags/its%20over%20dont%20read%20this%20its%20never%20coming%20bsck/works">its over dont read this its never coming bsck</a></li>
              </ul><!--summary-->
              <h6 class="landmark heading">Summary</h6>
              <blockquote class="userstuff summary">
               <p>jkr sucks<br>
                i suck at writing<br>
                please sir, some more</p>
              </blockquote><!--stats-->
              <dl class="stats">
               <dt class="language">
                Language:
               </dt>
               <dd class="language" lang="en">
                English
               </dd>
               <dt class="words">
                Words:
               </dt>
               <dd class="words">
                323
               </dd>
               <dt class="chapters">
                Chapters:
               </dt>
               <dd class="chapters">
                <a href="/works/66772294/chapters/208881696">3</a>/?
               </dd>
               <dt class="comments">
                Comments:
               </dt>
               <dd class="comments">
                <a href="/works/66772294?show_comments=true&amp;view_full_work=true#comments">6</a>
               </dd>
               <dt class="kudos">
                Kudos:
               </dt>
               <dd class="kudos">
                <a href="/works/66772294?view_full_work=true#kudos">21</a>
               </dd>
               <dt class="hits">
                Hits:
               </dt>
               <dd class="hits">
                766
               </dd>
              </dl></li>
             <li id="work_7541962" class="work blurb group work-7541962 user-1842322" role="article"><!--title, author, fandom-->
              <div class="header module">
               <!-- updated_at=1668539079 -->
               <h4 class="heading"><a href="/works/7541962">Harry Potter</a> by <!-- do not cache --> <a rel="author" href="/users/jenism/pseuds/moderatemalfoy">moderatemalfoy (jenism)</a></h4>
               <h5 class="fandoms heading"><span class="landmark">Fandoms:</span> <a class="tag" href="/tags/Harry%20Potter%20-%20J*d*%20K*d*%20Rowling/works">Harry Potter - J. K. Rowling</a> &nbsp;</h5><!--required tags-->
               <ul class="required-tags">
                <li><a class="help symbol question modal" title="Symbols key" href="/help/symbols-key.html"><span class="rating-teen rating" title="Teen And Up Audiences"><span class="text">Teen And Up Audiences</span></span></a></li>
                <li><a class="help symbol question modal" title="Symbols key" href="/help/symbols-key.html"><span class="warning-yes warnings" title="Graphic Depictions Of Violence, No Archive Warnings Apply, Rape/Non-Con"><span class="text">Graphic Depictions Of Violence, No Archive Warnings Apply, Rape/Non-Con</span></span></a></li>
                <li><a class="help symbol question modal" title="Symbols key" href="/help/symbols-key.html"><span class="category-multi category" title="F/M, M/M"><span class="text">F/M, M/M</span></span></a></li>
                <li><a class="help symbol question modal" title="Symbols key" href="/help/symbols-key.html"><span class="complete-yes iswip" title="Complete Work"><span class="text">Complete Work</span></span></a></li>
               </ul>
               <p class="datetime">20 Jul 2016</p>
              </div><!--warnings again, cast, freeform tags-->
              <h6 class="landmark heading">Tags</h6>
              <ul class="tags commas">
               <li class="warnings"><strong><a class="tag" href="/tags/Graphic%20Depictions%20Of%20Violence/works">Graphic Depictions Of Violence</a></strong></li>
               <li class="warnings"><strong><a class="tag" href="/tags/No%20Archive%20Warnings%20Apply/works">No Archive Warnings Apply</a></strong></li>
               <li class="warnings"><strong><a class="tag" href="/tags/Rape*s*Non-Con/works">Rape/Non-Con</a></strong></li>
               <li class="relationships"><a class="tag" href="/tags/Draco%20Malfoy*s*Original%20Female%20Character(s)/works">Draco Malfoy/Original Female Character(s)</a></li>
               <li class="relationships"><a class="tag" href="/tags/Draco%20Malfoy*s*Harry%20Potter/works">Draco Malfoy/Harry Potter</a></li>
               <li class="relationships"><a class="tag" href="/tags/Seamus%20Finnigan*s*Original%20Female%20Character(s)/works">Seamus Finnigan/Original Female Character(s)</a></li>
               <li class="characters"><a class="tag" href="/tags/Harry%20Potter/works">Harry Potter</a></li>
               <li class="characters"><a class="tag" href="/tags/Draco%20Malfoy/works">Draco Malfoy</a></li>
               <li class="characters"><a class="tag" href="/tags/Seamus%20Finnigan/works">Seamus Finnigan</a></li>
               <li class="characters"><a class="tag" href="/tags/Dean%20Thomas/works">Dean Thomas</a></li>
               <li class="characters"><a class="tag" href="/tags/Hermione%20Granger/works">Hermione Granger</a></li>
               <li class="characters"><a class="tag" href="/tags/Ron%20Weasley/works">Ron Weasley</a></li>
               <li class="characters"><a class="tag" href="/tags/Blaise%20Zabini/works">Blaise Zabini</a></li>
               <li class="characters"><a class="tag" href="/tags/Pansy%20Parkinson/works">Pansy Parkinson</a></li>
               <li class="characters"><a class="tag" href="/tags/Severus%20Snape/works">Severus Snape</a></li>
               <li class="characters"><a class="tag" href="/tags/Minerva%20McGonagall/works">Minerva McGonagall</a></li>
               <li class="characters"><a class="tag" href="/tags/Albus%20Dumbledore/works">Albus Dumbledore</a></li>
               <li class="freeforms"><a class="tag" href="/tags/Rape*s*Non-con%20Elements/works">Rape/Non-con Elements</a></li>
               <li class="freeforms"><a class="tag" href="/tags/Implied*s*Referenced%20Rape*s*Non-con/works">Implied/Referenced Rape/Non-con</a></li>
               <li class="freeforms"><a class="tag" href="/tags/Sexual%20Assault/works">Sexual Assault</a></li>
               <li class="freeforms"><a class="tag" href="/tags/Eating%20Disorders/works">Eating Disorders</a></li>
               <li class="freeforms"><a class="tag" href="/tags/Depression/works">Depression</a></li>
               <li class="freeforms"><a class="tag" href="/tags/Self-Harm/works">Self-Harm</a></li>
              </ul><!--summary-->
              <h6 class="landmark heading">Summary</h6>
              <blockquote class="userstuff summary">
               <p>I've caught a HP bug and I've written a whole mess of shorts. Some have potential and some are just me writing my pent up emotions. I'm 100% not done with any of them. I just want them out where people can give me feedback instead of me re-reading them into oblivion.</p>
              </blockquote><!--stats-->
              <dl class="stats">
               <dt class="language">
                Language:
               </dt>
               <dd class="language" lang="en">
                English
               </dd>
               <dt class="words">
                Words:
               </dt>
               <dd class="words">
                10,690
               </dd>
               <dt class="chapters">
                Chapters:
               </dt>
               <dd class="chapters">
                <a href="/works/7541962/chapters/17149516">13</a>/13
               </dd>
               <dt class="comments">
                Comments:
               </dt>
               <dd class="comments">
                <a href="/works/7541962?show_comments=true&amp;view_full_work=true#comments">25</a>
               </dd>
               <dt class="kudos">
                Kudos:
               </dt>
               <dd class="kudos">
                <a href="/works/7541962?view_full_work=true#kudos">363</a>
               </dd>
               <dt class="bookmarks">
                Bookmarks:
               </dt>
               <dd class="bookmarks">
                <a href="/works/7541962/bookmarks">20</a>
               </dd>
               <dt class="hits">
                Hits:
               </dt>
               <dd class="hits">
                19,821
               </dd>
              </dl></li>
             <li id="work_656983" class="work blurb group work-656983 user-9" role="article"><!--title, author, fandom-->
              <div class="header module">
               <!-- updated_at=1668539084 -->
               <h4 class="heading"><a href="/works/656983">Harry Potter</a> by <!-- do not cache --> <a rel="author" href="/users/orphan_account/pseuds/orphan_account">orphan_account</a></h4>
               <h5 class="fandoms heading"><span class="landmark">Fandoms:</span> <a class="tag" href="/tags/Pitch%20Perfect%20(2012)/works">Pitch Perfect (2012)</a> &nbsp;</h5><!--required tags-->
               <ul class="required-tags">
                <li><a class="help symbol question modal" title="Symbols key" href="/help/symbols-key.html"><span class="rating-teen rating" title="Teen And Up Audiences"><span class="text">Teen And Up Audiences</span></span></a></li>
                <li><a class="help symbol question modal" title="Symbols key" href="/help/symbols-key.html"><span class="warning-choosenotto warnings" title="Creator Chose Not To Use Archive Warnings"><span class="text">Creator Chose Not To Use Archive Warnings</span></span></a></li>
                <li><a class="help symbol question modal" title="Symbols key" href="/help/symbols-key.html"><span class="category-femslash category" title="F/F"><span class="text">F/F</span></span></a></li>
                <li><a class="help symbol question modal" title="Symbols key" href="/help/symbols-key.html"><span class="complete-yes iswip" title="Complete Work"><span class="text">Complete Work</span></span></a></li>
               </ul>
               <p class="datetime">27 Jan 2013</p>
              </div><!--warnings again, cast, freeform tags-->
              <h6 class="landmark heading">Tags</h6>
              <ul class="tags commas">
               <li class="warnings"><strong><a class="tag" href="/tags/Choose%20Not%20To%20Use%20Archive%20Warnings/works">Creator Chose Not To Use Archive Warnings</a></strong></li>
               <li class="relationships"><a class="tag" href="/tags/Beca*s*Chloe/works">Beca/Chloe</a></li>
              </ul><!--summary-->
              <h6 class="landmark heading">Summary</h6>
              <blockquote class="userstuff summary">
               <p>Prompted One-shot.<br>
                Jesse and Chloe surprise Beca, and lock her up for a sleep over. Jesse criticizes Beca’s favorite book series Harry Potter. Chloe soothes Beca’s rage, and kicks Jesse out so she can further comfort her girlfriend.</p>
              </blockquote><!--stats-->
              <dl class="stats">
               <dt class="language">
                Language:
               </dt>
               <dd class="language" lang="en">
                English
               </dd>
               <dt class="words">
                Words:
               </dt>
               <dd class="words">
                253
               </dd>
               <dt class="chapters">
                Chapters:
               </dt>
               <dd class="chapters">
                1/1
               </dd>
               <dt class="comments">
                Comments:
               </dt>
               <dd class="comments">
                <a href="/works/656983?show_comments=true#comments">2</a>
               </dd>
               <dt class="kudos">
                Kudos:
               </dt>
               <dd class="kudos">
                <a href="/works/656983#kudos">53</a>
               </dd>
               <dt class="bookmarks">
                Bookmarks:
               </dt>
               <dd class="bookmarks">
                <a href="/works/656983/bookmarks">2</a>
               </dd>
               <dt class="hits">
                Hits:
               </dt>
               <dd class="hits">
                3,178
               </dd>
              </dl></li>
             <li id="work_74635491" class="work blurb group work-74635491 user-31020006" role="article"><!--title, author, fandom-->
              <div class="header module">
               <!-- updated_at=1763893653 -->
               <h4 class="heading"><a href="/works/74635491">«&nbsp;Harry Potter&nbsp;»</a> by <!-- do not cache --> <a rel="author" href="/users/anari123123123_456456/pseuds/anari123123123_456456">anari123123123_456456</a></h4>
               <h5 class="fandoms heading"><span class="landmark">Fandoms:</span> <a class="tag" href="/tags/Original%20Work/works">Original Work</a> &nbsp;</h5><!--required tags-->
               <ul class="required-tags">
                <li><a class="help symbol question modal" title="Symbols key" href="/help/symbols-key.html"><span class="rating-general-audience rating" title="General Audiences"><span class="text">General Audiences</span></span></a></li>
                <li><a class="help symbol question modal" title="Symbols key" href="/help/symbols-key.html"><span class="warning-choosenotto warnings" title="Creator Chose Not To Use Archive Warnings"><span class="text">Creator Chose Not To Use Archive Warnings</span></span></a></li>
                <li><a class="help symbol question modal" title="Symbols key" href="/help/symbols-key.html"><span class="category-het category" title="F/M"><span class="text">F/M</span></span></a></li>
                <li><a class="help symbol question modal" title="Symbols key" href="/help/symbols-key.html"><span class="complete-yes iswip" title="Complete Work"><span class="text">Complete Work</span></span></a></li>
               </ul>
               <p class="datetime">23 Nov 2025</p>
              </div><!--warnings again, cast, freeform tags-->
              <h6 class="landmark heading">Tags</h6>
              <ul class="tags commas">
               <li class="warnings"><strong><a class="tag" href="/tags/Choose%20Not%20To%20Use%20Archive%20Warnings/works">Creator Chose Not To Use Archive Warnings</a></strong></li>
               <li class="freeforms"><a class="tag" href="/tags/poem/works">poem</a></li>
               <li class="freeforms"><a class="tag" href="/tags/Poetry/works">Poetry</a></li>
               <li class="freeforms"><a class="tag" href="/tags/Love%20Poem/works">Love Poem</a></li>
               <li class="freeforms"><a class="tag" href="/tags/Love/works">Love</a></li>
              </ul><!--summary--> <!--stats-->
              <dl class="stats">
               <dt class="language">
                Language:
               </dt>
               <dd class="language" lang="en">
                English
               </dd>
               <dt class="words">
                Words:
               </dt>
               <dd class="words">
                84
               </dd>
               <dt class="chapters">
                Chapters:
               </dt>
               <dd class="chapters">
                1/1
               </dd>
               <dt class="kudos">
                Kudos:
               </dt>
               <dd class="kudos">
                <a href="/works/74635491#kudos">6</a>
               </dd>
               <dt class="hits">
                Hits:
               </dt>
               <dd class="hits">
                129
               </dd>
              </dl></li>
             <li id="work_11244825" class="work blurb group work-11244825 user-723191" role="article"><!--title, author, fandom-->
              <div class="header module">
               <!-- updated_at=1769080558 -->
               <h4 class="heading"><a href="/works/11244825">Harry Potter</a> by <!-- do not cache --> <a rel="author" href="/users/NyGi/pseuds/NyGi">NyGi</a></h4>
               <h5 class="fandoms heading"><span class="landmark">Fandoms:</span> <a class="tag" href="/tags/Harry%20Potter%20-%20J*d*%20K*d*%20Rowling/works">Harry Potter - J. K. Rowling</a>, <a class="tag" href="/tags/Iron%20Man%20(Movies)/works">Iron Man (Movies)</a>, <a class="tag" href="/tags/Marvel%20Cinematic%20Universe/works">Marvel Cinematic Universe</a>, <a class="tag" href="/tags/Agent%20Carter%20(TV)/works">Agent Carter (TV)</a> &nbsp;</h5><!--required tags-->
               <ul class="required-tags">
                <li><a class="help symbol question modal" title="Symbols key" href="/help/symbols-key.html"><span class="rating-teen rating" title="Teen And Up Audiences"><span class="text">Teen And Up Audiences</span></span></a></li>
                <li><a class="help symbol question modal" title="Symbols key" href="/help/symbols-key.html"><span class="warning-no warnings" title="No Archive Warnings Apply"><span class="text">No Archive Warnings Apply</span></span></a></li>
                <li><a class="help symbol question modal" title="Symbols key" href="/help/symbols-key.html"><span class="category-het category" title="F/M"><span class="text">F/M</span></span></a></li>
                <li><a class="help symbol question modal" title="Symbols key" href="/help/symbols-key.html"><span class="complete-yes iswip" title="Complete Work"><span class="text">Complete Work</span></span></a></li>
               </ul>
               <p class="datetime">19 Jun 2017</p>
              </div><!--warnings again, cast, freeform tags-->
              <h6 class="landmark heading">Tags</h6>
              <ul class="tags commas">
               <li class="warnings"><strong><a class="tag" href="/tags/No%20Archive%20Warnings%20Apply/works">No Archive Warnings Apply</a></strong></li>
               <li class="relationships"><a class="tag" href="/tags/Howard%20Stark*s*Maria%20Stark/works">Howard Stark/Maria Stark</a></li>
               <li class="characters"><a class="tag" href="/tags/Howard%20Stark/works">Howard Stark</a></li>
               <li class="characters"><a class="tag" href="/tags/Harry%20Potter/works">Harry Potter</a></li>
               <li class="characters"><a class="tag" href="/tags/Maria%20Stark/works">Maria Stark</a></li>
               <li class="freeforms"><a class="tag" href="/tags/Harry%20Potter%20was%20Raised%20by%20Other(s)/works">Harry Potter was Raised by Other(s)</a></li>
               <li class="freeforms"><a class="tag" href="/tags/POV%20Howard%20Stark/works">POV Howard Stark</a></li>
               <li class="freeforms"><a class="tag" href="/tags/Howard%20Stark's%20A+%20Parenting/works">Howard Stark's A+ Parenting</a></li>
               <li class="freeforms"><a class="tag" href="/tags/Mentions%20of%20Murder/works">Mentions of Murder</a></li>
              </ul><!--summary-->
              <h6 class="landmark heading">Summary</h6>
              <blockquote class="userstuff summary">
               <p>Sending off your kids is horrible, especially if you cannot follow them. Even for a man like Howard Stark.</p>
              </blockquote>
              <h6 class="landmark heading">Series</h6>
              <ul class="series">
               <li>Part <strong>32</strong> of <a href="/series/607786">Trick or Treat</a></li>
               <li>Part <strong>4</strong> of <a href="/series/751851">The timely Brothers</a></li>
              </ul><!--stats-->
              <dl class="stats">
               <dt class="language">
                Language:
               </dt>
               <dd class="language" lang="en">
                English
               </dd>
               <dt class="words">
                Words:
               </dt>
               <dd class="words">
                3,006
               </dd>
               <dt class="chapters">
                Chapters:
               </dt>
               <dd class="chapters">
                1/1
               </dd>
               <dt class="collections">
                Collections:
               </dt>
               <dd class="collections">
                <a href="/works/11244825/collections">1</a>
               </dd>
               <dt class="comments">
                Comments:
               </dt>
               <dd class="comments">
                <a href="/works/11244825?show_comments=true#comments">20</a>
               </dd>
               <dt class="kudos">
                Kudos:
               </dt>
               <dd class="kudos">
                <a href="/works/11244825#kudos">551</a>
               </dd>
               <dt class="bookmarks">
                Bookmarks:
               </dt>
               <dd class="bookmarks">
                <a href="/works/11244825/bookmarks">31</a>
               </dd>
               <dt class="hits">
                Hits:
               </dt>
               <dd class="hits">
                10,146
               </dd>
              </dl></li>
             <li id="work_63677896" class="work blurb group work-63677896 user-20069533" role="article"><!--title, author, fandom-->
              <div class="header module">
               <!-- updated_at=1741415304 -->
               <h4 class="heading"><a href="/works/63677896">Harry Potter</a> by <!-- do not cache --> <a rel="author" href="/users/AstrioBlack/pseuds/AstrioBlack">AstrioBlack</a></h4>
               <h5 class="fandoms heading"><span class="landmark">Fandoms:</span> <a class="tag" href="/tags/Harry%20Potter%20-%20Fandom/works">Harry Potter - Fandom</a> &nbsp;</h5><!--required tags-->
               <ul class="required-tags">
                <li><a class="help symbol question modal" title="Symbols key" href="/help/symbols-key.html"><span class="rating-notrated rating" title="Not Rated"><span class="text">Not Rated</span></span></a></li>
                <li><a class="help symbol question modal" title="Symbols key" href="/help/symbols-key.html"><span class="warning-yes warnings" title="Creator Chose Not To Use Archive Warnings, Graphic Depictions Of Violence"><span class="text">Creator Chose Not To Use Archive Warnings, Graphic Depictions Of Violence</span></span></a></li>
                <li><a class="help symbol question modal" title="Symbols key" href="/help/symbols-key.html"><span class="category-multi category" title="M/M, Multi"><span class="text">M/M, Multi</span></span></a></li>
                <li><a class="help symbol question modal" title="Symbols key" href="/help/symbols-key.html"><span class="complete-yes iswip" title="Complete Work"><span class="text">Complete Work</span></span></a></li>
               </ul>
               <p class="datetime">08 Mar 2025</p>
              </div><!--warnings again, cast, freeform tags-->
              <h6 class="landmark heading">Tags</h6>
              <ul class="tags commas">
               <li class="warnings"><strong><a class="tag" href="/tags/Choose%20Not%20To%20Use%20Archive%20Warnings/works">Creator Chose Not To Use Archive Warnings</a></strong></li>
               <li class="warnings"><strong><a class="tag" href="/tags/Graphic%20Depictions%20Of%20Violence/works">Graphic Depictions Of Violence</a></strong></li>
               <li class="relationships"><a class="tag" href="/tags/Harry%20Potter*s*Severus%20Snape/works">Harry Potter/Severus Snape</a></li>
               <li class="relationships"><a class="tag" href="/tags/Draco%20Malfoy*s*Fred%20Weasley*s*George%20Weasley/works">Draco Malfoy/Fred Weasley/George Weasley</a></li>
               <li class="characters"><a class="tag" href="/tags/Severus%20Snape/works">Severus Snape</a></li>
               <li class="characters"><a class="tag" href="/tags/Albus%20Dumbledore/works">Albus Dumbledore</a></li>
               <li class="characters"><a class="tag" href="/tags/Fred%20Weasley/works">Fred Weasley</a></li>
               <li class="characters"><a class="tag" href="/tags/George%20Weasley/works">George Weasley</a></li>
               <li class="characters"><a class="tag" href="/tags/Harry%20Potter/works">Harry Potter</a></li>
               <li class="characters"><a class="tag" href="/tags/Hermione%20Granger/works">Hermione Granger</a></li>
               <li class="freeforms"><a class="tag" href="/tags/Notgoodwithtags/works">Notgoodwithtags</a></li>
              </ul><!--summary-->
              <h6 class="landmark heading">Summary</h6>
              <blockquote class="userstuff summary">
               <p>??</p>
              </blockquote><!--stats-->
              <dl class="stats">
               <dt class="language">
                Language:
               </dt>
               <dd class="language" lang="en">
                English
               </dd>
               <dt class="words">
                Words:
               </dt>
               <dd class="words">
                2,391
               </dd>
               <dt class="chapters">
                Chapters:
               </dt>
               <dd class="chapters">
                <a href="/works/63677896/chapters/163234372">2</a>/2
               </dd>
               <dt class="comments">
                Comments:
               </dt>
               <dd class="comments">
                <a href="/works/63677896?show_comments=true&amp;view_full_work=true#comments">1</a>
               </dd>
               <dt class="kudos">
                Kudos:
               </dt>
               <dd class="kudos">
                <a href="/works/63677896?view_full_work=true#kudos">21</a>
               </dd>
               <dt class="bookmarks">
                Bookmarks:
               </dt>
               <dd class="bookmarks">
                <a href="/works/63677896/bookmarks">2</a>
               </dd>
               <dt class="hits">
                Hits:
               </dt>
               <dd class="hits">
                704
               </dd>
              </dl></li>
             <li id="work_24609490" class="work blurb group work-24609490 user-7608622" role="article"><!--title, author, fandom-->
              <div class="header module">
               <!-- updated_at=1668539079 -->
               <h4 class="heading"><a href="/works/24609490">Harry Potter</a> by <!-- do not cache --> <a rel="author" href="/users/rubymarella302/pseuds/rubymarella302">rubymarella302</a></h4>
               <h5 class="fandoms heading"><span class="landmark">Fandoms:</span> <a class="tag" href="/tags/Harry%20Potter%20-%20J*d*%20K*d*%20Rowling/works">Harry Potter - J. K. Rowling</a> &nbsp;</h5><!--required tags-->
               <ul class="required-tags">
                <li><a class="help symbol question modal" title="Symbols key" href="/help/symbols-key.html"><span class="rating-teen rating" title="Teen And Up Audiences"><span class="text">Teen And Up Audiences</span></span></a></li>
                <li><a class="help symbol question modal" title="Symbols key" href="/help/symbols-key.html"><span class="warning-no warnings" title="No Archive Warnings Apply"><span class="text">No Archive Warnings Apply</span></span></a></li>
                <li><a class="help symbol question modal" title="Symbols key" href="/help/symbols-key.html"><span class="category-multi category" title="Gen, M/M"><span class="text">Gen, M/M</span></span></a></li>
                <li><a class="help symbol question modal" title="Symbols key" href="/help/symbols-key.html"><span class="complete-yes iswip" title="Complete Work"><span class="text">Complete Work</span></span></a></li>
               </ul>
               <p class="datetime">08 Jun 2020</p>
              </div><!--warnings again, cast, freeform tags-->
              <h6 class="landmark heading">Tags</h6>
              <ul class="tags commas">
               <li class="warnings"><strong><a class="tag" href="/tags/No%20Archive%20Warnings%20Apply/works">No Archive Warnings Apply</a></strong></li>
               <li class="relationships"><a class="tag" href="/tags/Draco%20Malfoy*s*Harry%20Potter/works">Draco Malfoy/Harry Potter</a></li>
               <li class="characters"><a class="tag" href="/tags/Harry%20Potter/works">Harry Potter</a></li>
               <li class="characters"><a class="tag" href="/tags/Draco%20Malfoy/works">Draco Malfoy</a></li>
               <li class="characters"><a class="tag" href="/tags/Various%20Characters/works">Various Characters</a></li>
               <li class="characters"><a class="tag" href="/tags/Alex%20is%20back*d*%20Again*d*/works">Alex is back. Again.</a></li>
              </ul><!--summary-->
              <h6 class="landmark heading">Summary</h6>
              <blockquote class="userstuff summary">
               <p>Two incredibly different ideas but they were both Rather Gay</p>
              </blockquote>
              <h6 class="landmark heading">Series</h6>
              <ul class="series">
               <li>Part <strong>7</strong> of <a href="/series/1777936">Unfinished Works</a></li>
              </ul><!--stats-->
              <dl class="stats">
               <dt class="language">
                Language:
               </dt>
               <dd class="language" lang="en">
                English
               </dd>
               <dt class="words">
                Words:
               </dt>
               <dd class="words">
                3,732
               </dd>
               <dt class="chapters">
                Chapters:
               </dt>
               <dd class="chapters">
                <a href="/works/24609490/chapters/59450155">2</a>/2
               </dd>
               <dt class="comments">
                Comments:
               </dt>
               <dd class="comments">
                <a href="/works/24609490?show_comments=true&amp;view_full_work=true#comments">3</a>
               </dd>
               <dt class="kudos">
                Kudos:
               </dt>
               <dd class="kudos">
                <a href="/works/24609490?view_full_work=true#kudos">162</a>
               </dd>
               <dt class="bookmarks">
                Bookmarks:
               </dt>
               <dd class="bookmarks">
                <a href="/works/24609490/bookmarks">4</a>
               </dd>
               <dt class="hits">
                Hits:
               </dt>
               <dd class="hits">
                7,834
               </dd>
              </dl></li>
             <li id="work_51016804" class="work blurb group work-51016804 user-11320216" role="article"><!--title, author, fandom-->
              <div class="header module">
               <!-- updated_at=1759782174 -->
               <h4 class="heading"><a href="/works/51016804">Harry Potter</a> by <!-- do not cache --> <a rel="author" href="/users/Wolfchild18/pseuds/Wolfchild18">Wolfchild18</a></h4>
               <h5 class="fandoms heading"><span class="landmark">Fandoms:</span> <a class="tag" href="/tags/Harry%20Potter%20-%20J*d*%20K*d*%20Rowling/works">Harry Potter - J. K. Rowling</a> &nbsp;</h5><!--required tags-->
               <ul class="required-tags">
                <li><a class="help symbol question modal" title="Symbols key" href="/help/symbols-key.html"><span class="rating-general-audience rating" title="General Audiences"><span class="text">General Audiences</span></span></a></li>
                <li><a class="help symbol question modal" title="Symbols key" href="/help/symbols-key.html"><span class="warning-choosenotto warnings" title="Creator Chose Not To Use Archive Warnings"><span class="text">Creator Chose Not To Use Archive Warnings</span></span></a></li>
                <li><a class="help symbol question modal" title="Symbols key" href="/help/symbols-key.html"><span class="category-multi category" title="F/M, M/M"><span class="text">F/M, M/M</span></span></a></li>
                <li><a class="help symbol question modal" title="Symbols key" href="/help/symbols-key.html"><span class="complete-yes iswip" title="Complete Work"><span class="text">Complete Work</span></span></a></li>
               </ul>
               <p class="datetime">05 Jun 2025</p>
              </div><!--warnings again, cast, freeform tags-->
              <h6 class="landmark heading">Tags</h6>
              <ul class="tags commas">
               <li class="warnings"><strong><a class="tag" href="/tags/Choose%20Not%20To%20Use%20Archive%20Warnings/works">Creator Chose Not To Use Archive Warnings</a></strong></li>
               <li class="relationships"><a class="tag" href="/tags/Draco%20Malfoy%20*a*%20Original%20Female%20Character(s)/works">Draco Malfoy &amp; Original Female Character(s)</a></li>
               <li class="relationships"><a class="tag" href="/tags/Neville%20Longbottom*s*Luna%20Lovegood/works">Neville Longbottom/Luna Lovegood</a></li>
               <li class="relationships"><a class="tag" href="/tags/Harry%20Potter%20*a*%20Severus%20Snape/works">Harry Potter &amp; Severus Snape</a></li>
               <li class="characters"><a class="tag" href="/tags/Harry%20Potter/works">Harry Potter</a></li>
               <li class="characters"><a class="tag" href="/tags/Serena%20Potter/works">Serena Potter</a></li>
               <li class="characters"><a class="tag" href="/tags/Ron%20Weasley/works">Ron Weasley</a></li>
               <li class="characters"><a class="tag" href="/tags/Hermione%20Granger/works">Hermione Granger</a></li>
               <li class="characters"><a class="tag" href="/tags/Luna%20Lovegood/works">Luna Lovegood</a></li>
               <li class="characters"><a class="tag" href="/tags/Neville%20Longbottom/works">Neville Longbottom</a></li>
               <li class="characters"><a class="tag" href="/tags/Draco%20Malfoy/works">Draco Malfoy</a></li>
               <li class="characters"><a class="tag" href="/tags/Blaise%20Zabini/works">Blaise Zabini</a></li>
               <li class="characters"><a class="tag" href="/tags/Theodore%20Nott/works">Theodore Nott</a></li>
               <li class="characters"><a class="tag" href="/tags/Pansy%20Parkinson/works">Pansy Parkinson</a></li>
               <li class="characters"><a class="tag" href="/tags/Gregory%20Goyle/works">Gregory Goyle</a></li>
               <li class="characters"><a class="tag" href="/tags/Vincent%20Crabbe/works">Vincent Crabbe</a></li>
               <li class="characters"><a class="tag" href="/tags/Severus%20Snape/works">Severus Snape</a></li>
              </ul><!--summary-->
              <h6 class="landmark heading">Summary</h6>
              <blockquote class="userstuff summary">
               <p>What would happen if you feel into your fav. book? Well here's your chance to find out in Harry Potter(no I do not own it, I only claim my characters) and their world before Hogwarts! Let's find out what happens when one of the main characters has memories of her past life and how it'll affect them in this fun tale. Let's find out in this Isekai story of Harry Potter hope you like it!!!!!</p>
              </blockquote><!--stats-->
              <dl class="stats">
               <dt class="language">
                Language:
               </dt>
               <dd class="language" lang="en">
                English
               </dd>
               <dt class="words">
                Words:
               </dt>
               <dd class="words">
                38,671
               </dd>
               <dt class="chapters">
                Chapters:
               </dt>
               <dd class="chapters">
                <a href="/works/51016804/chapters/170716954">11</a>/11
               </dd>
               <dt class="comments">
                Comments:
               </dt>
               <dd class="comments">
                <a href="/works/51016804?show_comments=true&amp;view_full_work=true#comments">41</a>
               </dd>
               <dt class="kudos">
                Kudos:
               </dt>
               <dd class="kudos">
                <a href="/works/51016804?view_full_work=true#kudos">66</a>
               </dd>
               <dt class="bookmarks">
                Bookmarks:
               </dt>
               <dd class="bookmarks">
                <a href="/works/51016804/bookmarks">5</a>
               </dd>
               <dt class="hits">
                Hits:
               </dt>
               <dd class="hits">
                4,348
               </dd>
              </dl></li>
             <li id="work_8868136" class="work blurb group work-8868136 user-2308366" role="article"><!--title, author, fandom-->
              <div class="header module">
               <!-- updated_at=1490178532 -->
               <h4 class="heading"><a href="/works/8868136">Harry Potter</a> by <!-- do not cache --> mino1243 [archived by <a rel="author" href="/users/Turkfanfiction_Archivist/pseuds/Turkfanfiction_Archivist">Turkfanfiction_Archivist</a>]</h4>
               <h5 class="fandoms heading"><span class="landmark">Fandoms:</span> <a class="tag" href="/tags/Harry%20Potter%20-%20J*d*%20K*d*%20Rowling/works">Harry Potter - J. K. Rowling</a> &nbsp;</h5><!--required tags-->
               <ul class="required-tags">
                <li><a class="help symbol question modal" title="Symbols key" href="/help/symbols-key.html"><span class="rating-general-audience rating" title="General Audiences"><span class="text">General Audiences</span></span></a></li>
                <li><a class="help symbol question modal" title="Symbols key" href="/help/symbols-key.html"><span class="warning-choosenotto warnings" title="Creator Chose Not To Use Archive Warnings"><span class="text">Creator Chose Not To Use Archive Warnings</span></span></a></li>
                <li><a class="help symbol question modal" title="Symbols key" href="/help/symbols-key.html"><span class="category-gen category" title="Gen"><span class="text">Gen</span></span></a></li>
                <li><a class="help symbol question modal" title="Symbols key" href="/help/symbols-key.html"><span class="complete-yes iswip" title="Complete Work"><span class="text">Complete Work</span></span></a></li>
               </ul>
               <p class="datetime">16 Dec 2016</p>
              </div><!--warnings again, cast, freeform tags-->
              <h6 class="landmark heading">Tags</h6>
              <ul class="tags commas">
               <li class="warnings"><strong><a class="tag" href="/tags/Choose%20Not%20To%20Use%20Archive%20Warnings/works">Creator Chose Not To Use Archive Warnings</a></strong></li>
               <li class="characters"><a class="tag" href="/tags/Albus%20Dumbledore/works">Albus Dumbledore</a></li>
               <li class="characters"><a class="tag" href="/tags/Draco%20Malfoy/works">Draco Malfoy</a></li>
               <li class="characters"><a class="tag" href="/tags/Harry%20Potter/works">Harry Potter</a></li>
               <li class="characters"><a class="tag" href="/tags/Hermione%20Granger/works">Hermione Granger</a></li>
               <li class="characters"><a class="tag" href="/tags/Minerva%20McGonagall/works">Minerva McGonagall</a></li>
               <li class="characters"><a class="tag" href="/tags/Severus%20Snape/works">Severus Snape</a></li>
               <li class="freeforms"><a class="tag" href="/tags/Action*s*Adventure/works">Action/Adventure</a></li>
               <li class="relationships"><a class="tag" href="/tags/Hermione%20Granger*s*Ron%20Weasley/works">Hermione Granger/Ron Weasley</a></li>
               <li class="freeforms"><a class="tag" href="/tags/Fantasy/works">Fantasy</a></li>
               <li class="freeforms"><a class="tag" href="/tags/Comedy/works">Comedy</a></li>
              </ul><!--summary-->
              <h6 class="landmark heading">Summary</h6>
              <blockquote class="userstuff summary">
               <p>Herşey yeniden başlıyor... Harry potter ve arkadaşları 4. Sınıfta ve yeni maceralar onları bekliyor .<br>
                Hizemli kişiler kimlikler <br>
                Yeni profesör örneyin işte sıkı durun sizi harika bir hikaye bekliyor!</p>
              </blockquote><!--stats-->
              <dl class="stats">
               <dt class="language">
                Language:
               </dt>
               <dd class="language" lang="tr">
                Türkçe
               </dd>
               <dt class="words">
                Words:
               </dt>
               <dd class="words">
                799
               </dd>
               <dt class="chapters">
                Chapters:
               </dt>
               <dd class="chapters">
                1/1
               </dd>
               <dt class="collections">
                Collections:
               </dt>
               <dd class="collections">
                <a href="/works/8868136/collections">1</a>
               </dd>
               <dt class="comments">
                Comments:
               </dt>
               <dd class="comments">
                <a href="/works/8868136?show_comments=true#comments">1</a>
               </dd>
               <dt class="kudos">
                Kudos:
               </dt>
               <dd class="kudos">
                <a href="/works/8868136#kudos">2</a>
               </dd>
               <dt class="hits">
                Hits:
               </dt>
               <dd class="hits">
                800
               </dd>
              </dl></li>
             <li id="work_8597572" class="work blurb group work-8597572 user-2574586" role="article"><!--title, author, fandom-->
              <div class="header module">
               <!-- updated_at=1668535695 -->
               <h4 class="heading"><a href="/works/8597572">Harry Potter</a> by <!-- do not cache --> <a rel="author" href="/users/CARTOONSAREAWESOME/pseuds/CARTOONSAREAWESOME">CARTOONSAREAWESOME</a></h4>
               <h5 class="fandoms heading"><span class="landmark">Fandoms:</span> <a class="tag" href="/tags/Harry%20Potter%20-%20J*d*%20K*d*%20Rowling/works">Harry Potter - J. K. Rowling</a> &nbsp;</h5><!--required tags-->
               <ul class="required-tags">
                <li><a class="help symbol question modal" title="Symbols key" href="/help/symbols-key.html"><span class="rating-general-audience rating" title="General Audiences"><span class="text">General Audiences</span></span></a></li>
                <li><a class="help symbol question modal" title="Symbols key" href="/help/symbols-key.html"><span class="warning-yes warnings" title="Major Character Death"><span class="text">Major Character Death</span></span></a></li>
                <li><a class="help symbol question modal" title="Symbols key" href="/help/symbols-key.html"><span class="category-het category" title="F/M"><span class="text">F/M</span></span></a></li>
                <li><a class="help symbol question modal" title="Symbols key" href="/help/symbols-key.html"><span class="complete-yes iswip" title="Complete Work"><span class="text">Complete Work</span></span></a></li>
               </ul>
               <p class="datetime">19 Nov 2016</p>
              </div><!--warnings again, cast, freeform tags-->
              <h6 class="landmark heading">Tags</h6>
              <ul class="tags commas">
               <li class="warnings"><strong><a class="tag" href="/tags/Major%20Character%20Death/works">Major Character Death</a></strong></li>
               <li class="relationships"><a class="tag" href="/tags/Hermione%20Granger*s*Ron%20Weasley/works">Hermione Granger/Ron Weasley</a></li>
               <li class="characters"><a class="tag" href="/tags/Hermione%20Granger/works">Hermione Granger</a></li>
               <li class="characters"><a class="tag" href="/tags/Ron%20Weasley/works">Ron Weasley</a></li>
               <li class="characters"><a class="tag" href="/tags/Draco%20Malfoy/works">Draco Malfoy</a></li>
               <li class="characters"><a class="tag" href="/tags/Tom%20Riddle%20%7C%20Voldemort/works">Tom Riddle | Voldemort</a></li>
               <li class="freeforms"><a class="tag" href="/tags/Fanfiction/works">Fanfiction</a></li>
              </ul><!--summary-->
              <h6 class="landmark heading">Summary</h6>
              <blockquote class="userstuff summary">
               <p>Raised by Voldemort Harry followed in the footsteps of his former parents and became a death eater. Harry now aids Voldemort in his plans to take over.</p>
              </blockquote><!--stats-->
              <dl class="stats">
               <dt class="language">
                Language:
               </dt>
               <dd class="language" lang="en">
                English
               </dd>
               <dt class="words">
                Words:
               </dt>
               <dd class="words">
                1,524
               </dd>
               <dt class="chapters">
                Chapters:
               </dt>
               <dd class="chapters">
                1/1
               </dd>
               <dt class="comments">
                Comments:
               </dt>
               <dd class="comments">
                <a href="/works/8597572?show_comments=true#comments">24</a>
               </dd>
               <dt class="kudos">
                Kudos:
               </dt>
               <dd class="kudos">
                <a href="/works/8597572#kudos">138</a>
               </dd>
               <dt class="bookmarks">
                Bookmarks:
               </dt>
               <dd class="bookmarks">
                <a href="/works/8597572/bookmarks">12</a>
               </dd>
               <dt class="hits">
                Hits:
               </dt>
               <dd class="hits">
                5,690
               </dd>
              </dl></li>
             <li id="work_73687691" class="work blurb group work-73687691 user-26622124" role="article"><!--title, author, fandom-->
              <div class="header module">
               <!-- updated_at=1762280901 -->
               <h4 class="heading"><a href="/works/73687691">Harry Potter</a> by <!-- do not cache --> <a rel="author" href="/users/Vesna_Na_Zemle/pseuds/Vesna_Na_Zemle">Vesna_Na_Zemle</a></h4>
               <h5 class="fandoms heading"><span class="landmark">Fandoms:</span> <a class="tag" href="/tags/Harry%20Potter%20-%20J*d*%20K*d*%20Rowling/works">Harry Potter - J. K. Rowling</a> &nbsp;</h5><!--required tags-->
               <ul class="required-tags">
                <li><a class="help symbol question modal" title="Symbols key" href="/help/symbols-key.html"><span class="rating-general-audience rating" title="General Audiences"><span class="text">General Audiences</span></span></a></li>
                <li><a class="help symbol question modal" title="Symbols key" href="/help/symbols-key.html"><span class="warning-choosenotto warnings" title="Creator Chose Not To Use Archive Warnings"><span class="text">Creator Chose Not To Use Archive Warnings</span></span></a></li>
                <li><a class="help symbol question modal" title="Symbols key" href="/help/symbols-key.html"><span class="category-gen category" title="Gen"><span class="text">Gen</span></span></a></li>
                <li><a class="help symbol question modal" title="Symbols key" href="/help/symbols-key.html"><span class="complete-yes iswip" title="Complete Work"><span class="text">Complete Work</span></span></a></li>
               </ul>
               <p class="datetime">04 Nov 2025</p>
              </div><!--warnings again, cast, freeform tags-->
              <h6 class="landmark heading">Tags</h6>
              <ul class="tags commas">
               <li class="warnings"><strong><a class="tag" href="/tags/Choose%20Not%20To%20Use%20Archive%20Warnings/works">Creator Chose Not To Use Archive Warnings</a></strong></li>
               <li class="characters"><a class="tag" href="/tags/Harry%20Potter/works">Harry Potter</a></li>
               <li class="freeforms"><a class="tag" href="/tags/Art/works">Art</a></li>
               <li class="freeforms"><a class="tag" href="/tags/Digital%20Art/works">Digital Art</a></li>
               <li class="freeforms"><a class="tag" href="/tags/Post-Hogwarts/works">Post-Hogwarts</a></li>
               <li class="freeforms"><a class="tag" href="/tags/Portraits/works">Portraits</a></li>
              </ul><!--summary-->
              <h6 class="landmark heading">Summary</h6>
              <blockquote class="userstuff summary">
               <p>Twenty-year-old Harry, self-assured, slightly smug, but still good-natured. Why hadn't he worn red glasses frames before? They suit him, don't they?</p>
              </blockquote><!--stats-->
              <dl class="stats">
               <dt class="language">
                Language:
               </dt>
               <dd class="language" lang="en">
                English
               </dd>
               <dt class="words">
                Words:
               </dt>
               <dd class="words">
                0
               </dd>
               <dt class="chapters">
                Chapters:
               </dt>
               <dd class="chapters">
                1/1
               </dd>
               <dt class="comments">
                Comments:
               </dt>
               <dd class="comments">
                <a href="/works/73687691?show_comments=true#comments">7</a>
               </dd>
               <dt class="kudos">
                Kudos:
               </dt>
               <dd class="kudos">
                <a href="/works/73687691#kudos">16</a>
               </dd>
               <dt class="hits">
                Hits:
               </dt>
               <dd class="hits">
                421
               </dd>
              </dl></li>
             <li id="work_29564997" class="work blurb group work-29564997 user-10153557" role="article"><!--title, author, fandom-->
              <div class="header module">
               <!-- updated_at=1736510643 -->
               <h4 class="heading"><a href="/works/29564997">Harry Potter</a> by <!-- do not cache --> <a rel="author" href="/users/VernonForPresident/pseuds/VernonForPresident">VernonForPresident</a></h4>
               <h5 class="fandoms heading"><span class="landmark">Fandoms:</span> <a class="tag" href="/tags/Harry%20Potter%20-%20J*d*%20K*d*%20Rowling/works">Harry Potter - J. K. Rowling</a>, <a class="tag" href="/tags/Harry%20Potter%20and%20the%20Cursed%20Child%20-%20Thorne%20*a*%20Rowling/works">Harry Potter and the Cursed Child - Thorne &amp; Rowling</a>, <a class="tag" href="/tags/Harry%20Potter%20RPF/works">Harry Potter RPF</a>, <a class="tag" href="/tags/Harry%20Potter:%20Hogwarts%20Mystery%20(Video%20Game)/works">Harry Potter: Hogwarts Mystery (Video Game)</a> &nbsp;</h5><!--required tags-->
               <ul class="required-tags">
                <li><a class="help symbol question modal" title="Symbols key" href="/help/symbols-key.html"><span class="rating-general-audience rating" title="General Audiences"><span class="text">General Audiences</span></span></a></li>
                <li><a class="help symbol question modal" title="Symbols key" href="/help/symbols-key.html"><span class="warning-choosenotto warnings" title="Creator Chose Not To Use Archive Warnings"><span class="text">Creator Chose Not To Use Archive Warnings</span></span></a></li>
                <li><a class="help symbol question modal" title="Symbols key" href="/help/symbols-key.html"><span class="category-none category" title="No category"><span class="text">No category</span></span></a></li>
                <li><a class="help symbol question modal" title="Symbols key" href="/help/symbols-key.html"><span class="complete-yes iswip" title="Complete Work"><span class="text">Complete Work</span></span></a></li>
               </ul>
               <p class="datetime">19 Feb 2021</p>
              </div><!--warnings again, cast, freeform tags-->
              <h6 class="landmark heading">Tags</h6>
              <ul class="tags commas">
               <li class="warnings"><strong><a class="tag" href="/tags/Choose%20Not%20To%20Use%20Archive%20Warnings/works">Creator Chose Not To Use Archive Warnings</a></strong></li>
               <li class="characters"><a class="tag" href="/tags/Harry%20Potter/works">Harry Potter</a></li>
               <li class="characters"><a class="tag" href="/tags/Ron%20Weasley/works">Ron Weasley</a></li>
               <li class="characters"><a class="tag" href="/tags/Hermione%20Granger/works">Hermione Granger</a></li>
               <li class="characters"><a class="tag" href="/tags/Voldemort%20(Harry%20Potter)/works">Voldemort (Harry Potter)</a></li>
               <li class="characters"><a class="tag" href="/tags/Tom%20Riddle%20%7C%20Voldemort/works">Tom Riddle | Voldemort</a></li>
               <li class="characters"><a class="tag" href="/tags/Voldemort%20(Olympic%20Opening%20Ceremony)/works">Voldemort (Olympic Opening Ceremony)</a></li>
               <li class="characters"><a class="tag" href="/tags/Dursley%20Family%20(Harry%20Potter)/works">Dursley Family (Harry Potter)</a></li>
               <li class="freeforms"><a class="tag" href="/tags/Zoo/works">Zoo</a></li>
               <li class="freeforms"><a class="tag" href="/tags/Pandas/works">Pandas</a></li>
               <li class="freeforms"><a class="tag" href="/tags/High%20School/works">High School</a></li>
               <li class="freeforms"><a class="tag" href="/tags/School/works">School</a></li>
               <li class="freeforms"><a class="tag" href="/tags/Alternate%20Universe%20-%20High%20School/works">Alternate Universe - High School</a></li>
              </ul><!--summary--> <!--stats-->
              <dl class="stats">
               <dt class="language">
                Language:
               </dt>
               <dd class="language" lang="en">
                English
               </dd>
               <dt class="words">
                Words:
               </dt>
               <dd class="words">
                86
               </dd>
               <dt class="chapters">
                Chapters:
               </dt>
               <dd class="chapters">
                1/1
               </dd>
               <dt class="comments">
                Comments:
               </dt>
               <dd class="comments">
                <a href="/works/29564997?show_comments=true#comments">16</a>
               </dd>
               <dt class="kudos">
                Kudos:
               </dt>
               <dd class="kudos">
                <a href="/works/29564997#kudos">57</a>
               </dd>
               <dt class="bookmarks">
                Bookmarks:
               </dt>
               <dd class="bookmarks">
                <a href="/works/29564997/bookmarks">1</a>
               </dd>
               <dt class="hits">
                Hits:
               </dt>
               <dd class="hits">
                2,962
               </dd>
              </dl></li>
             <li id="work_23156851" class="work blurb group work-23156851 user-9" role="article"><!--title, author, fandom-->
              <div class="header module">
               <!-- updated_at=1668539087 -->
               <h4 class="heading"><a href="/works/23156851">"HARRY POTTER !"</a> by <!-- do not cache --> <a rel="author" href="/users/orphan_account/pseuds/hobiknj">hobiknj (orphan_account)</a></h4>
               <h5 class="fandoms heading"><span class="landmark">Fandoms:</span> <a class="tag" href="/tags/Harry%20Potter%20-%20J*d*%20K*d*%20Rowling/works">Harry Potter - J. K. Rowling</a> &nbsp;</h5><!--required tags-->
               <ul class="required-tags">
                <li><a class="help symbol question modal" title="Symbols key" href="/help/symbols-key.html"><span class="rating-general-audience rating" title="General Audiences"><span class="text">General Audiences</span></span></a></li>
                <li><a class="help symbol question modal" title="Symbols key" href="/help/symbols-key.html"><span class="warning-choosenotto warnings" title="Creator Chose Not To Use Archive Warnings"><span class="text">Creator Chose Not To Use Archive Warnings</span></span></a></li>
                <li><a class="help symbol question modal" title="Symbols key" href="/help/symbols-key.html"><span class="category-het category" title="F/M"><span class="text">F/M</span></span></a></li>
                <li><a class="help symbol question modal" title="Symbols key" href="/help/symbols-key.html"><span class="complete-yes iswip" title="Complete Work"><span class="text">Complete Work</span></span></a></li>
               </ul>
               <p class="datetime">15 Mar 2020</p>
              </div><!--warnings again, cast, freeform tags-->
              <h6 class="landmark heading">Tags</h6>
              <ul class="tags commas">
               <li class="warnings"><strong><a class="tag" href="/tags/Choose%20Not%20To%20Use%20Archive%20Warnings/works">Creator Chose Not To Use Archive Warnings</a></strong></li>
               <li class="relationships"><a class="tag" href="/tags/Sirius%20Black*s*Original%20Female%20Character(s)/works">Sirius Black/Original Female Character(s)</a></li>
               <li class="relationships"><a class="tag" href="/tags/Sirius%20Black*s*Remus%20Lupin/works">Sirius Black/Remus Lupin</a></li>
               <li class="relationships"><a class="tag" href="/tags/Sirius%20Black*s*Remus%20Lupin*s*Harry%20Potter/works">Sirius Black/Remus Lupin/Harry Potter</a></li>
               <li class="characters"><a class="tag" href="/tags/Harry%20Potter/works">Harry Potter</a></li>
               <li class="characters"><a class="tag" href="/tags/Hermione%20Granger/works">Hermione Granger</a></li>
               <li class="characters"><a class="tag" href="/tags/Ron%20Weasley/works">Ron Weasley</a></li>
               <li class="characters"><a class="tag" href="/tags/Sirius%20Black/works">Sirius Black</a></li>
               <li class="characters"><a class="tag" href="/tags/Remus%20Lupin/works">Remus Lupin</a></li>
               <li class="characters"><a class="tag" href="/tags/Original%20Female%20Character(s)/works">Original Female Character(s)</a></li>
               <li class="freeforms"><a class="tag" href="/tags/Sirius%20Black%20Raises%20Harry%20Potter/works">Sirius Black Raises Harry Potter</a></li>
               <li class="freeforms"><a class="tag" href="/tags/Remus%20Lupin%20Raises%20Harry%20Potter/works">Remus Lupin Raises Harry Potter</a></li>
               <li class="freeforms"><a class="tag" href="/tags/Original%20Character(s)/works">Original Character(s)</a></li>
               <li class="freeforms"><a class="tag" href="/tags/Harry%20Potter%20*a*%20Ron%20Weasley%20Friendship/works">Harry Potter &amp; Ron Weasley Friendship</a></li>
               <li class="freeforms"><a class="tag" href="/tags/Hermione%20Granger%20*a*%20Harry%20Potter%20Friendship/works">Hermione Granger &amp; Harry Potter Friendship</a></li>
               <li class="freeforms"><a class="tag" href="/tags/Sirius%20Black%20*a*%20Remus%20Lupin%20Friendship/works">Sirius Black &amp; Remus Lupin Friendship</a></li>
              </ul><!--summary-->
              <h6 class="landmark heading">Summary</h6>
              <blockquote class="userstuff summary">
               <p>Après leur virée en voiture pour arriver à Poudlard, Harry reçoit aussi une beuglante de sa tante t/p et de ses oncles moony et padfoot.</p>
              </blockquote><!--stats-->
              <dl class="stats">
               <dt class="language">
                Language:
               </dt>
               <dd class="language" lang="fr">
                Français
               </dd>
               <dt class="words">
                Words:
               </dt>
               <dd class="words">
                554
               </dd>
               <dt class="chapters">
                Chapters:
               </dt>
               <dd class="chapters">
                1/1
               </dd>
               <dt class="comments">
                Comments:
               </dt>
               <dd class="comments">
                <a href="/works/23156851?show_comments=true#comments">5</a>
               </dd>
               <dt class="kudos">
                Kudos:
               </dt>
               <dd class="kudos">
                <a href="/works/23156851#kudos">57</a>
               </dd>
               <dt class="bookmarks">
                Bookmarks:
               </dt>
               <dd class="bookmarks">
                <a href="/works/23156851/bookmarks">1</a>
               </dd>
               <dt class="hits">
                Hits:
               </dt>
               <dd class="hits">
                3,213
               </dd>
              </dl></li>
             <li id="work_1980816" class="work blurb group work-1980816 user-36313" role="article"><!--title, author, fandom-->
              <div class="header module">
               <!-- updated_at=1405721882 -->
               <h4 class="heading"><a href="/works/1980816">Harry Potter</a> by <!-- do not cache --> <a rel="author" href="/users/NeoCortex/pseuds/NeoCortex">NeoCortex</a></h4>
               <h5 class="fandoms heading"><span class="landmark">Fandoms:</span> <a class="tag" href="/tags/Teen%20Wolf%20(TV)/works">Teen Wolf (TV)</a>, <a class="tag" href="/tags/The%20Avengers%20(Marvel%20Movies)/works">The Avengers (Marvel Movies)</a> &nbsp;</h5><!--required tags-->
               <ul class="required-tags">
                <li><a class="help symbol question modal" title="Symbols key" href="/help/symbols-key.html"><span class="rating-notrated rating" title="Not Rated"><span class="text">Not Rated</span></span></a></li>
                <li><a class="help symbol question modal" title="Symbols key" href="/help/symbols-key.html"><span class="warning-choosenotto warnings" title="Creator Chose Not To Use Archive Warnings"><span class="text">Creator Chose Not To Use Archive Warnings</span></span></a></li>
                <li><a class="help symbol question modal" title="Symbols key" href="/help/symbols-key.html"><span class="category-none category" title="No category"><span class="text">No category</span></span></a></li>
                <li><a class="help symbol question modal" title="Symbols key" href="/help/symbols-key.html"><span class="complete-yes iswip" title="Complete Work"><span class="text">Complete Work</span></span></a></li>
               </ul>
               <p class="datetime">18 Jul 2014</p>
              </div><!--warnings again, cast, freeform tags-->
              <h6 class="landmark heading">Tags</h6>
              <ul class="tags commas">
               <li class="warnings"><strong><a class="tag" href="/tags/Choose%20Not%20To%20Use%20Archive%20Warnings/works">Creator Chose Not To Use Archive Warnings</a></strong></li>
               <li class="characters"><a class="tag" href="/tags/Stiles%20Stilinski/works">Stiles Stilinski</a></li>
               <li class="characters"><a class="tag" href="/tags/Salem%20Barton/works">Salem Barton</a></li>
               <li class="freeforms"><a class="tag" href="/tags/Crossover/works">Crossover</a></li>
               <li class="freeforms"><a class="tag" href="/tags/Salem%20is%20one%20of%20Marvel%20OCs/works">Salem is one of Marvel OCs</a></li>
              </ul><!--summary-->
              <h6 class="landmark heading">Summary</h6>
              <blockquote class="userstuff summary">
               <p>Stiles and Salem discuss Harry Potter</p>
              </blockquote>
              <h6 class="landmark heading">Series</h6>
              <ul class="series">
               <li>Part <strong>10</strong> of <a href="/series/128073">Writer's Block Word Challenge</a></li>
              </ul><!--stats-->
              <dl class="stats">
               <dt class="language">
                Language:
               </dt>
               <dd class="language" lang="en">
                English
               </dd>
               <dt class="words">
                Words:
               </dt>
               <dd class="words">
                53
               </dd>
               <dt class="chapters">
                Chapters:
               </dt>
               <dd class="chapters">
                1/1
               </dd>
               <dt class="comments">
                Comments:
               </dt>
               <dd class="comments">
                <a href="/works/1980816?show_comments=true#comments">2</a>
               </dd>
               <dt class="kudos">
                Kudos:
               </dt>
               <dd class="kudos">
                <a href="/works/1980816#kudos">17</a>
               </dd>
               <dt class="bookmarks">
                Bookmarks:
               </dt>
               <dd class="bookmarks">
                <a href="/works/1980816/bookmarks">1</a>
               </dd>
               <dt class="hits">
                Hits:
               </dt>
               <dd class="hits">
                1,126
               </dd>
              </dl></li>
            </ol>
            <h4 class="landmark heading">Pages Navigation</h4>
            <ol role="navigation" aria-label="Pagination" class="pagination actions" title="pagination">
             <li class="previous" title="previous"><span class="disabled">← Previous</span></li>
             <li><span class="current">1</span></li>
             <li><a rel="next" href="/works/search?commit=Search&amp;page=2&amp;work_search%5Bbookmarks_count%5D=&amp;work_search%5Bcharacter_names%5D=&amp;work_search%5Bcomments_count%5D=&amp;work_search%5Bcomplete%5D=&amp;work_search%5Bcreators%5D=&amp;work_search%5Bcrossover%5D=&amp;work_search%5Bfandom_names%5D=&amp;work_search%5Bfreeform_names%5D=&amp;work_search%5Bhits%5D=&amp;work_search%5Bkudos_count%5D=&amp;work_search%5Blanguage_id%5D=&amp;work_search%5Bquery%5D=Harry+Potter&amp;work_search%5Brating_ids%5D=&amp;work_search%5Brelationship_names%5D=&amp;work_search%5Brevised_at%5D=&amp;work_search%5Bsingle_chapter%5D=0&amp;work_search%5Bsort_column%5D=_score&amp;work_search%5Bsort_direction%5D=desc&amp;work_search%5Btitle%5D=&amp;work_search%5Bword_count%5D=">2</a></li>
             <li><a href="/works/search?commit=Search&amp;page=3&amp;work_search%5Bbookmarks_count%5D=&amp;work_search%5Bcharacter_names%5D=&amp;work_search%5Bcomments_count%5D=&amp;work_search%5Bcomplete%5D=&amp;work_search%5Bcreators%5D=&amp;work_search%5Bcrossover%5D=&amp;work_search%5Bfandom_names%5D=&amp;work_search%5Bfreeform_names%5D=&amp;work_search%5Bhits%5D=&amp;work_search%5Bkudos_count%5D=&amp;work_search%5Blanguage_id%5D=&amp;work_search%5Bquery%5D=Harry+Potter&amp;work_search%5Brating_ids%5D=&amp;work_search%5Brelationship_names%5D=&amp;work_search%5Brevised_at%5D=&amp;work_search%5Bsingle_chapter%5D=0&amp;work_search%5Bsort_column%5D=_score&amp;work_search%5Bsort_direction%5D=desc&amp;work_search%5Btitle%5D=&amp;work_search%5Bword_count%5D=">3</a></li>
             <li><a href="/works/search?commit=Search&amp;page=4&amp;work_search%5Bbookmarks_count%5D=&amp;work_search%5Bcharacter_names%5D=&amp;work_search%5Bcomments_count%5D=&amp;work_search%5Bcomplete%5D=&amp;work_search%5Bcreators%5D=&amp;work_search%5Bcrossover%5D=&amp;work_search%5Bfandom_names%5D=&amp;work_search%5Bfreeform_names%5D=&amp;work_search%5Bhits%5D=&amp;work_search%5Bkudos_count%5D=&amp;work_search%5Blanguage_id%5D=&amp;work_search%5Bquery%5D=Harry+Potter&amp;work_search%5Brating_ids%5D=&amp;work_search%5Brelationship_names%5D=&amp;work_search%5Brevised_at%5D=&amp;work_search%5Bsingle_chapter%5D=0&amp;work_search%5Bsort_column%5D=_score&amp;work_search%5Bsort_direction%5D=desc&amp;work_search%5Btitle%5D=&amp;work_search%5Bword_count%5D=">4</a></li>
             <li><a href="/works/search?commit=Search&amp;page=5&amp;work_search%5Bbookmarks_count%5D=&amp;work_search%5Bcharacter_names%5D=&amp;work_search%5Bcomments_count%5D=&amp;work_search%5Bcomplete%5D=&amp;work_search%5Bcreators%5D=&amp;work_search%5Bcrossover%5D=&amp;work_search%5Bfandom_names%5D=&amp;work_search%5Bfreeform_names%5D=&amp;work_search%5Bhits%5D=&amp;work_search%5Bkudos_count%5D=&amp;work_search%5Blanguage_id%5D=&amp;work_search%5Bquery%5D=Harry+Potter&amp;work_search%5Brating_ids%5D=&amp;work_search%5Brelationship_names%5D=&amp;work_search%5Brevised_at%5D=&amp;work_search%5Bsingle_chapter%5D=0&amp;work_search%5Bsort_column%5D=_score&amp;work_search%5Bsort_direction%5D=desc&amp;work_search%5Btitle%5D=&amp;work_search%5Bword_count%5D=">5</a></li>
             <li><a href="/works/search?commit=Search&amp;page=6&amp;work_search%5Bbookmarks_count%5D=&amp;work_search%5Bcharacter_names%5D=&amp;work_search%5Bcomments_count%5D=&amp;work_search%5Bcomplete%5D=&amp;work_search%5Bcreators%5D=&amp;work_search%5Bcrossover%5D=&amp;work_search%5Bfandom_names%5D=&amp;work_search%5Bfreeform_names%5D=&amp;work_search%5Bhits%5D=&amp;work_search%5Bkudos_count%5D=&amp;work_search%5Blanguage_id%5D=&amp;work_search%5Bquery%5D=Harry+Potter&amp;work_search%5Brating_ids%5D=&amp;work_search%5Brelationship_names%5D=&amp;work_search%5Brevised_at%5D=&amp;work_search%5Bsingle_chapter%5D=0&amp;work_search%5Bsort_column%5D=_score&amp;work_search%5Bsort_direction%5D=desc&amp;work_search%5Btitle%5D=&amp;work_search%5Bword_count%5D=">6</a></li>
             <li><a href="/works/search?commit=Search&amp;page=7&amp;work_search%5Bbookmarks_count%5D=&amp;work_search%5Bcharacter_names%5D=&amp;work_search%5Bcomments_count%5D=&amp;work_search%5Bcomplete%5D=&amp;work_search%5Bcreators%5D=&amp;work_search%5Bcrossover%5D=&amp;work_search%5Bfandom_names%5D=&amp;work_search%5Bfreeform_names%5D=&amp;work_search%5Bhits%5D=&amp;work_search%5Bkudos_count%5D=&amp;work_search%5Blanguage_id%5D=&amp;work_search%5Bquery%5D=Harry+Potter&amp;work_search%5Brating_ids%5D=&amp;work_search%5Brelationship_names%5D=&amp;work_search%5Brevised_at%5D=&amp;work_search%5Bsingle_chapter%5D=0&amp;work_search%5Bsort_column%5D=_score&amp;work_search%5Bsort_direction%5D=desc&amp;work_search%5Btitle%5D=&amp;work_search%5Bword_count%5D=">7</a></li>
             <li><a href="/works/search?commit=Search&amp;page=8&amp;work_search%5Bbookmarks_count%5D=&amp;work_search%5Bcharacter_names%5D=&amp;work_search%5Bcomments_count%5D=&amp;work_search%5Bcomplete%5D=&amp;work_search%5Bcreators%5D=&amp;work_search%5Bcrossover%5D=&amp;work_search%5Bfandom_names%5D=&amp;work_search%5Bfreeform_names%5D=&amp;work_search%5Bhits%5D=&amp;work_search%5Bkudos_count%5D=&amp;work_search%5Blanguage_id%5D=&amp;work_search%5Bquery%5D=Harry+Potter&amp;work_search%5Brating_ids%5D=&amp;work_search%5Brelationship_names%5D=&amp;work_search%5Brevised_at%5D=&amp;work_search%5Bsingle_chapter%5D=0&amp;work_search%5Bsort_column%5D=_score&amp;work_search%5Bsort_direction%5D=desc&amp;work_search%5Btitle%5D=&amp;work_search%5Bword_count%5D=">8</a></li>
             <li><a href="/works/search?commit=Search&amp;page=9&amp;work_search%5Bbookmarks_count%5D=&amp;work_search%5Bcharacter_names%5D=&amp;work_search%5Bcomments_count%5D=&amp;work_search%5Bcomplete%5D=&amp;work_search%5Bcreators%5D=&amp;work_search%5Bcrossover%5D=&amp;work_search%5Bfandom_names%5D=&amp;work_search%5Bfreeform_names%5D=&amp;work_search%5Bhits%5D=&amp;work_search%5Bkudos_count%5D=&amp;work_search%5Blanguage_id%5D=&amp;work_search%5Bquery%5D=Harry+Potter&amp;work_search%5Brating_ids%5D=&amp;work_search%5Brelationship_names%5D=&amp;work_search%5Brevised_at%5D=&amp;work_search%5Bsingle_chapter%5D=0&amp;work_search%5Bsort_column%5D=_score&amp;work_search%5Bsort_direction%5D=desc&amp;work_search%5Btitle%5D=&amp;work_search%5Bword_count%5D=">9</a></li>
             <li class="gap">…</li>
             <li><a href="/works/search?commit=Search&amp;page=4999&amp;work_search%5Bbookmarks_count%5D=&amp;work_search%5Bcharacter_names%5D=&amp;work_search%5Bcomments_count%5D=&amp;work_search%5Bcomplete%5D=&amp;work_search%5Bcreators%5D=&amp;work_search%5Bcrossover%5D=&amp;work_search%5Bfandom_names%5D=&amp;work_search%5Bfreeform_names%5D=&amp;work_search%5Bhits%5D=&amp;work_search%5Bkudos_count%5D=&amp;work_search%5Blanguage_id%5D=&amp;work_search%5Bquery%5D=Harry+Potter&amp;work_search%5Brating_ids%5D=&amp;work_search%5Brelationship_names%5D=&amp;work_search%5Brevised_at%5D=&amp;work_search%5Bsingle_chapter%5D=0&amp;work_search%5Bsort_column%5D=_score&amp;work_search%5Bsort_direction%5D=desc&amp;work_search%5Btitle%5D=&amp;work_search%5Bword_count%5D=">4999</a></li>
             <li><a href="/works/search?commit=Search&amp;page=5000&amp;work_search%5Bbookmarks_count%5D=&amp;work_search%5Bcharacter_names%5D=&amp;work_search%5Bcomments_count%5D=&amp;work_search%5Bcomplete%5D=&amp;work_search%5Bcreators%5D=&amp;work_search%5Bcrossover%5D=&amp;work_search%5Bfandom_names%5D=&amp;work_search%5Bfreeform_names%5D=&amp;work_search%5Bhits%5D=&amp;work_search%5Bkudos_count%5D=&amp;work_search%5Blanguage_id%5D=&amp;work_search%5Bquery%5D=Harry+Potter&amp;work_search%5Brating_ids%5D=&amp;work_search%5Brelationship_names%5D=&amp;work_search%5Brevised_at%5D=&amp;work_search%5Bsingle_chapter%5D=0&amp;work_search%5Bsort_column%5D=_score&amp;work_search%5Bsort_direction%5D=desc&amp;work_search%5Btitle%5D=&amp;work_search%5Bword_count%5D=">5000</a></li>
             <li class="next" title="next"><a rel="next" href="/works/search?commit=Search&amp;page=2&amp;work_search%5Bbookmarks_count%5D=&amp;work_search%5Bcharacter_names%5D=&amp;work_search%5Bcomments_count%5D=&amp;work_search%5Bcomplete%5D=&amp;work_search%5Bcreators%5D=&amp;work_search%5Bcrossover%5D=&amp;work_search%5Bfandom_names%5D=&amp;work_search%5Bfreeform_names%5D=&amp;work_search%5Bhits%5D=&amp;work_search%5Bkudos_count%5D=&amp;work_search%5Blanguage_id%5D=&amp;work_search%5Bquery%5D=Harry+Potter&amp;work_search%5Brating_ids%5D=&amp;work_search%5Brelationship_names%5D=&amp;work_search%5Brevised_at%5D=&amp;work_search%5Bsingle_chapter%5D=0&amp;work_search%5Bsort_column%5D=_score&amp;work_search%5Bsort_direction%5D=desc&amp;work_search%5Btitle%5D=&amp;work_search%5Bword_count%5D=">Next →</a></li>
            </ol><!--/content-->
            <div class="clear">
             <!--presentational-->
            </div>
           </div><!-- END main -->
          </div><!-- BEGIN footer -->
          <div id="footer" role="contentinfo" class="region">
           <h3 class="landmark heading">Footer</h3>
           <ul class="navigation actions">
            <li class="module group">
             <h4 class="heading">About the Archive</h4>
             <ul class="menu">
              <li><a href="/site_map">Site Map</a></li>
              <li><a href="/diversity">Diversity Statement</a></li>
              <li><a href="/tos">Terms of Service</a></li>
              <li><a href="/content">Content Policy</a></li>
              <li><a href="/privacy">Privacy Policy</a></li>
              <li><a href="/dmca">DMCA Policy</a></li>
              <li><a href="https://www.otwstatus.org">Site Status</a></li>
             </ul></li>
            <li class="module group">
             <h4 class="heading">Contact Us</h4>
             <ul class="menu">
              <li><a href="/abuse_reports/new">Policy Questions &amp; Abuse Reports</a></li>
              <li><a href="/support">Technical Support &amp; Feedback</a></li>
             </ul></li>
            <li class="module group">
             <h4 class="heading">Development</h4>
             <ul class="menu">
              <li><a href="https://github.com/otwcode/otwarchive/commits/v0.9.460.1">otwarchive v0.9.460.1</a></li>
              <li><a href="/known_issues">Known Issues</a></li>
              <li><a title="View License" href="https://www.gnu.org/licenses/old-licenses/gpl-2.0.html">GPL-2.0-or-later</a> by the <a title="Organization for Transformative Works" href="https://transformativeworks.org/">OTW</a></li>
             </ul></li>
           </ul>
          </div><!-- END footer -->
         </div><!-- check to see if this controller/action allow tinymce before we load the gigantor js; see application_helper -->
         <script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js" type="text/javascript"></script>
         <script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.10.0/jquery-ui.min.js" type="text/javascript"></script><!-- if user has googleapis blocked for some reason we need a fallback -->
         <script type="text/javascript">
          if (typeof jQuery == 'undefined') {
            document.write(unescape("%3Cscript src='/javascripts/jquery.min.js' type='text/javascript'%3E%3C/script%3E"));
            document.write(unescape("%3Cscript src='/javascripts/jquery-ui.min.js' type='text/javascript'%3E%3C/script%3E"));
          }
        </script>
         <script type="text/javascript">$j = jQuery.noConflict();</script>
         <script src="/javascripts/jquery.scrollTo.min.js"></script>
         <script src="/javascripts/jquery.livequery.min.js"></script>
         <script src="/javascripts/rails.js"></script>
         <script src="/javascripts/application.js"></script>
         <script src="/javascripts/bootstrap/bootstrap-dropdown.min.js"></script>
         <script src="/javascripts/jquery-shuffle.js"></script>
         <script src="/javascripts/jquery.tokeninput.min.js"></script>
         <script src="/javascripts/jquery.trap.min.js"></script>
         <script src="/javascripts/ao3modal.min.js"></script>
         <script src="/javascripts/js.cookie.min.js"></script>
         <script src="/javascripts/filters.min.js"></script>
         <script>
        //<![CDATA[

            // We can't rely on !window.localStorage to test localStorage support in
            // browsers like Safari 9, which technically support it, but which have a
            // storage length of 0 in private mode.
            // Credit: https://github.com/getgrav/grav-plugin-admin/commit/cfe2188f10c4ca604e03c96f3e21537fda1cdf9a
            function isSupported() {
                var item = "localStoragePolyfill";
                try {
                    localStorage.setItem(item, item);
                    localStorage.removeItem(item);
                    return true;
                } catch (e) {
                    return false;
                }
            }

            function acceptTOS() {
              if (isSupported()) {
                localStorage.setItem("accepted_tos", "20241119");
              } else {
                Cookies.set("accepted_tos", "20241119", { expires: 365 });
              }
            }

            $j(document).ready(function() {
                if (localStorage.getItem("accepted_tos") !== "20241119" && Cookies.get("accepted_tos") !== "20241119") {
                  $j("body").prepend("<div id=\"tos_prompt\" class=\"hidden\">\n  <h2 class=\"heading\">\n    <span>Archive of Our Own<\/span>\n  <\/h2>\n  <div class=\"agreement\">\n    <p>\n      On the Archive of Our Own (AO3), users can create works, bookmarks, comments, tags, and other <a href=\"/tos_faq#define_content\">Content<\/a>. Any information you publish on AO3 may be accessible by the public, AO3 users, and/or AO3 personnel. Be mindful when sharing personal information, including but not limited to your name, email, age, location, personal relationships, gender or sexual identity, racial or ethnic background, religious or political views, and/or account usernames for other sites.\n    <\/p>\n    <p>\n      To learn more, check out our <a href=\"/tos\">Terms of Service<\/a>, including the <a href=\"/content\">Content Policy<\/a> and <a href=\"/privacy\">Privacy Policy<\/a>.\n    <\/p>\n\n    <p class=\"confirmation\">\n      <input type=\"checkbox\" id=\"tos_agree\" />\n      <label for=\"tos_agree\">I have read &amp; understood the 2024 Terms of Service, including the Content Policy and Privacy Policy.<\/label>\n    <\/p>\n\n    <p class=\"confirmation\">\n      <input type=\"checkbox\" id=\"data_processing_agree\" />\n      <label for=\"data_processing_agree\">By checking this box, you consent to the processing of your personal data in the United States and other jurisdictions in connection with our provision of AO3 and its related services to you. You acknowledge that the data privacy laws of such jurisdictions may differ from those provided in your jurisdiction. For more information about how your personal data will be processed, please refer to our Privacy Policy.<\/label>\n    <\/p>\n\n      <p class=\"submit\">\n        <button name=\"button\" type=\"button\" disabled=\"disabled\" id=\"accept_tos\">I agree/consent to these Terms<\/button>\n      <\/p>\n\n  <\/div>\n<\/div>\n\n<script>\n//<![CDATA[\n\n  \$j(document).ready(function() {\n    var container = \j(\"#tos_prompt\");\n    var outer = \$j(\"#outer\");\n    var button = \$j(\"#accept_tos\");\n    var tosCheckbox = document.getElementById(\"tos_agree\");\n    var dataProcessingCheckbox = document.getElementById(\"data_processing_agree\");\n\n    var checkboxClicked = function() {\n      button.attr(\"disabled\", !tosCheckbox.checked || !dataProcessingCheckbox.checked);\n      if (this.checked) {\n        button.on(\"click\", function() {\n          acceptTOS();\n          outer.removeClass(\"hidden\").removeAttr(\"aria-hidden\");\n          \$j.when(container.fadeOut(500)).done(function() {\n            container.remove();\n          });\n        });\n      };\n    };\n\n    setTimeout(showTOSPrompt, 1500);\n\n    function showTOSPrompt() {\n      \$j.when(container.fadeIn(500)).done(function() {\n        outer.addClass(\"hidden\").attr(\"aria-hidden\", \"true\");\n      });\n\n      \$j(\"#tos_agree\").on(\"click\", checkboxClicked).change();\n      \$j(\"#data_processing_agree\").on(\"click\", checkboxClicked).change();\n    };\n  });\n\n//]]]]><![CDATA[>\n<\/script>");
                }
            });

        //]]>
        </script>
         <script>
        //<![CDATA[

            $j(document).ready(function() {
              var permitted_hosts = ["104.153.64.122","208.85.241.152","208.85.241.157","ao3.org","archiveofourown.com","archiveofourown.gay","archiveofourown.net","archiveofourown.org","download.archiveofourown.org","insecure.archiveofourown.org","secure.archiveofourown.org","www.archiveofourown.com","www.archiveofourown.net","www.archiveofourown.org","www.ao3.org","archive.transformativeworks.org"];
              var current_host = window.location.hostname;

              if (!permitted_hosts.includes(current_host) && Cookies.get("proxy_notice") !== "0" && window.location.protocol !== "file:") {
                $j("#skiplinks").after("<div id=\"proxy-notice\">\n  <div class=\"userstuff\">\n    <p class=\"important\">Important message:<\/p>\n    <ol>\n      <li>You are using a proxy site that is not part of the Archive of Our Own.<\/li>\n      <li>The entity that set up the proxy site can see what you submit, including your IP address. If you log in through the proxy site, it can see your password.<\/li>\n    <\/ol>\n    <p class=\"important\">Важная информация:<\/p>\n    <ol>\n      <li>Вы используете прокси-сайт, который не является частью AO3 (Нашего Архива).<\/li>\n      <li>Субъект, настроивший прокси-сайт, может видеть, что вы отправляете, включая ваш IP-адрес. Если вы авторизуетесь через прокси-сайт, он может видеть ваш пароль.<\/li>\n    <\/ol>\n    <p class=\"important\">Важливе повідомлення:<\/p>\n    <ol>\n      <li>Ви використовуєте проксі-сайт, який не є частиною Archive of Our Own (Нашого Власного Архіву).<\/li>\n      <li>Творці цього проксі-сайту можуть бачити Ваші дані та дії, а також Вашу IP-адресу. Якщо Ви входите до свого облікового запису, використовуючи проксі-сайт, його творці можуть бачити Ваш пароль.<\/li>\n    <\/ol>\n    <p class=\"important\">重要提示：<\/p>\n    <ol>\n      <li>您使用的是第三方开发的反向代理网站，此网站并非Archive of Our Own - AO3（AO3作品库）原站。<\/li>\n      <li>代理网站的开发者能够获取您上传至该站点的全部内容，包括您的ip地址。如您通过代理登录AO3，对方将获得您的密码。<\/li>\n    <\/ol>\n    <p class=\"submit\"><button class=\"action\" type=\"button\" id=\"proxy-notice-dismiss\">Dismiss Notice<\/button><\/p>\n  <\/div>\n<\/div>\n\n<script>\n//<![CDATA[\n\n  \$j(document).ready(function() {\n    \$j(\"#proxy-notice-dismiss\").on(\"click\", function() {\n      Cookies.set(\"proxy_notice\", \"0\");\n      \$j(\"#proxy-notice\").slideUp();\n    });\n  });\n\n//]]]]><![CDATA[>\n<\/script>");
              }
            });

        //]]>
        </script>
        </body>
    """.trimIndent()

    val workEntry = """
        <li id="work_8868136" class="work blurb group work-8868136 user-2308366" role="article"><!--title, author, fandom-->
      <div class="header module">
       <!-- updated_at=1490178532 -->
       <h4 class="heading"><a href="/works/8868136">Harry Potter</a> by <!-- do not cache --> mino1243 [archived by <a rel="author" href="/users/Turkfanfiction_Archivist/pseuds/Turkfanfiction_Archivist">Turkfanfiction_Archivist</a>]</h4>
       <h5 class="fandoms heading"><span class="landmark">Fandoms:</span> <a class="tag" href="/tags/Harry%20Potter%20-%20J*d*%20K*d*%20Rowling/works">Harry Potter - J. K. Rowling</a> &nbsp;</h5><!--required tags-->
       <ul class="required-tags">
        <li><a class="help symbol question modal" title="Symbols key" href="/help/symbols-key.html"><span class="rating-general-audience rating" title="General Audiences"><span class="text">General Audiences</span></span></a></li>
        <li><a class="help symbol question modal" title="Symbols key" href="/help/symbols-key.html"><span class="warning-choosenotto warnings" title="Creator Chose Not To Use Archive Warnings"><span class="text">Creator Chose Not To Use Archive Warnings</span></span></a></li>
        <li><a class="help symbol question modal" title="Symbols key" href="/help/symbols-key.html"><span class="category-gen category" title="Gen"><span class="text">Gen</span></span></a></li>
        <li><a class="help symbol question modal" title="Symbols key" href="/help/symbols-key.html"><span class="complete-yes iswip" title="Complete Work"><span class="text">Complete Work</span></span></a></li>
       </ul>
       <p class="datetime">16 Dec 2016</p>
      </div><!--warnings again, cast, freeform tags-->
      <h6 class="landmark heading">Tags</h6>
      <ul class="tags commas">
       <li class="warnings"><strong><a class="tag" href="/tags/Choose%20Not%20To%20Use%20Archive%20Warnings/works">Creator Chose Not To Use Archive Warnings</a></strong></li>
       <li class="characters"><a class="tag" href="/tags/Albus%20Dumbledore/works">Albus Dumbledore</a></li>
       <li class="characters"><a class="tag" href="/tags/Draco%20Malfoy/works">Draco Malfoy</a></li>
       <li class="characters"><a class="tag" href="/tags/Harry%20Potter/works">Harry Potter</a></li>
       <li class="characters"><a class="tag" href="/tags/Hermione%20Granger/works">Hermione Granger</a></li>
       <li class="characters"><a class="tag" href="/tags/Minerva%20McGonagall/works">Minerva McGonagall</a></li>
       <li class="characters"><a class="tag" href="/tags/Severus%20Snape/works">Severus Snape</a></li>
       <li class="freeforms"><a class="tag" href="/tags/Action*s*Adventure/works">Action/Adventure</a></li>
       <li class="relationships"><a class="tag" href="/tags/Hermione%20Granger*s*Ron%20Weasley/works">Hermione Granger/Ron Weasley</a></li>
       <li class="freeforms"><a class="tag" href="/tags/Fantasy/works">Fantasy</a></li>
       <li class="freeforms"><a class="tag" href="/tags/Comedy/works">Comedy</a></li>
      </ul><!--summary-->
      <h6 class="landmark heading">Summary</h6>
      <blockquote class="userstuff summary">
       <p>Herşey yeniden başlıyor... Harry potter ve arkadaşları 4. Sınıfta ve yeni maceralar onları bekliyor .<br>
        Hizemli kişiler kimlikler <br>
        Yeni profesör örneyin işte sıkı durun sizi harika bir hikaye bekliyor!</p>
      </blockquote><!--stats-->
      <dl class="stats">
       <dt class="language">
        Language:
       </dt>
       <dd class="language" lang="tr">
        Türkçe
       </dd>
       <dt class="words">
        Words:
       </dt>
       <dd class="words">
        799
       </dd>
       <dt class="chapters">
        Chapters:
       </dt>
       <dd class="chapters">
        1/1
       </dd>
       <dt class="collections">
        Collections:
       </dt>
       <dd class="collections">
        <a href="/works/8868136/collections">1</a>
       </dd>
       <dt class="comments">
        Comments:
       </dt>
       <dd class="comments">
        <a href="/works/8868136?show_comments=true#comments">1</a>
       </dd>
       <dt class="kudos">
        Kudos:
       </dt>
       <dd class="kudos">
        <a href="/works/8868136#kudos">2</a>
       </dd>
       <dt class="hits">
        Hits:
       </dt>
       <dd class="hits">
        800
       </dd>
      </dl></li>
    """.trimIndent()
}