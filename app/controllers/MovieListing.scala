package controllers

import play.api.mvc.{Action, Controller}
import play.api.data.Forms._
import play.api.data._
import models.{Movie, Movies}
import utils.{IafdLoader, LoadInfoFromWeb}

object MovieListing extends Controller{

  def movieDetailForm = Form(
    mapping(
      "name" -> nonEmptyText,
      "location" -> nonEmptyText
    )(Movie.apply)(Movie.unapply)
  )

  def index = Action { implicit request =>
    Ok(views.html.movies(Movies.all))
  }

  def detail(title: String) = Action { implicit request =>
    Ok(views.html.moviedetail(movieDetailForm.fill( Movies.detail(title))))
  }

  def searchByTitle(title: String) = Action {
    Ok(views.html.movieupdate(LoadInfoFromWeb.fromWeb(title)(new IafdLoader())))
  }

}
