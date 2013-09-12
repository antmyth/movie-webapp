package models

import mongoContext.context

import com.mongodb.casbah.Imports._
import com.novus.salat._

case class Movie(id: String, location: String){
  val name = id
}
case class MovieDetails(id: String, location: String, year: Int){
  val name = id
}

object Movies {
  val movies = MongoConnection()("movie_db")("Movie")

  def detail(title: String):Movie = {
    movies.findOne(MongoDBObject("_id"->title)).map(grater[Movie].asObject(_)).get
  }

  def update(updated: MovieDetails){
    movies.save(grater[MovieDetails].asDBObject(updated))
  }

  def all = {
    println(movies.size)
    movies.map(grater[Movie].asObject(_)).toList
  }
}
