package models


import mongoContext.context

import com.mongodb.casbah.Imports._
import com.novus.salat._

case class Movie(id: String, location: String){
  val name = id
}

object Movies {
  val movies = MongoConnection()("movie_db")("Movie")

  def all = {
    println(movies.size)
    movies.map(grater[Movie].asObject(_)).toList
  }
}
