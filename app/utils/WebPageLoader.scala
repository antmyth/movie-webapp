package utils

import java.net.URL
import util.matching.Regex


case class SearchResultItem(url: String, title: String, studio: String, year: String)

object WebPageLoader {
  def fromWeb(title: String): String = {
    "1984"
  }

  val iafdUrl = "http://www.iafd.com/"
  val searchByTitleUrl = iafdUrl+"results.asp?searchtype=title&searchstring="

  def parseTitle(title: String) = title.replaceAll("\\s","+")
}

class WebPageLoader {

//    val url = new URL("http://www.iafd.com")
  val url2 = new URL("http://www.iafd.com/results.asp?searchtype=title&searchstring=anal+angels")
  val urlCon = url2.openConnection()
  urlCon.setConnectTimeout(20000)
  urlCon.setReadTimeout(10000)
  println(urlCon.getContentLength)
  println(urlCon.getContentType)
  println(urlCon.getContent)

  def read(url: String): String = io.Source.fromURL(url, "iso-8859-1").mkString


  def infoFor(title: String): String = {
    val response: String = read("http://www.iafd.com/results.asp?searchtype=title&searchstring=god+save+the+kink")
    println(response)
    val strings: Array[String] = response.split("/n")
    val regex = "<ol class=\"tresult\">(.*)</ol>".r
    //    println("SIZE---"+strings.last)
    response match {
      case u if u.contains("title results") => u
    }
    val regex(inside) = response
    inside
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
    println("Stripped==>" + stripped)
    stripped
  }
}
