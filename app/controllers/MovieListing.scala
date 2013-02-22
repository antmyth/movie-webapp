package controllers

import play.api.mvc.{Action, Controller}
import models.Movies

object MovieListing extends Controller{

  def index = Action { implicit request =>
    Ok(views.html.movies(Movies.all))
  }

}
