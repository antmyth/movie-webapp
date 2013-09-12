package utils

import util.matching.Regex


case class SearchResultItem(url: String, title: String, studio: String, year: String)

object LoadInfoFromWeb {
  def fromWeb(title: String)(f: LoadInfoFromWeb): List[SearchResultItem] = {
    f.infoFor(title)
  }

}

trait LoadInfoFromWeb{
  def infoFor(title: String):List[SearchResultItem]
}

class IafdLoader extends LoadInfoFromWeb{
  val iafdUrl = "http://www.iafd.com/"
  val searchByTitleUrl = iafdUrl+"results.asp?searchtype=title&searchstring="

  def parseTitle(title: String) = title.replaceAll("\\s","+")

  def read(url: String): String = io.Source.fromURL(url, "iso-8859-1").mkString


  def infoFor(title: String) = {
    val response: String = read(searchByTitleUrl + parseTitle(title))
//    println(response)
    searchResultsIn(response)
  }

  def searchResultsIn(html: String): List[SearchResultItem] = {
    val stripped: String = stripOverheadHtml(html)
    val regex2: Regex = "<a href=\"(title.rme[^>]*)\">([^<]*)</a></b><br>&nbsp;&nbsp;&nbsp;<b>Release Info:</b> ([^,]*), ([0-9]*)".r
    regex2.findAllMatchIn(stripped).map(regexMatch => regexMatch.subgroups.toList match {
      case List(url:String, title:String, studio:String, year:String) => SearchResultItem(url,title,studio,year)
    }).toList
  }

  def stripOverheadHtml(html: String): String = {
    val regex1: Regex = "<ol class=\"tresult\">(.*)</ol>".r
    val stripped = regex1.findFirstIn(html).get
//    println("Stripped==>" + stripped)
    stripped
  }
}

class DummyIafdLoader extends LoadInfoFromWeb{
  def infoFor(title: String) = ???
}
