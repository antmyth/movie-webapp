package controllers

import play.api.mvc._
import play.api.data.Forms._
import play.api.data._
import models.{RegistrationXXX, Registrations}

object Register extends Controller{

  def registrationForm = Form(
    mapping(
      "name" -> nonEmptyText,
      "password" ->nonEmptyText,
      "confirm" -> nonEmptyText,
      "realName" -> text
    )(RegistrationXXX.apply)(RegistrationXXX.unapply)
      verifying("Passwords must match", fields => fields match {
              case RegistrationXXX(_, password, confirmation, _) => password.equals(confirmation)
            })
  )

  def index = Action { implicit request =>
    Ok(views.html.register(registrationForm))
  }

  def register = Action { implicit request =>
      registrationForm.bindFromRequest.fold(
        form => BadRequest(views.html.register(form)).flashing("message" -> "shit"),
        registration => {
          Registrations.create(registration)
          Redirect(routes.Application.index()).flashing("message" -> "User Registered")
        }
      )
  }
}
