package models

import com.mongodb.casbah.Imports._
import com.novus.salat._
import play.api.Play
import play.api.Play.current

package object mongoContext {
  implicit val context = {
    val context = new Context {
      val name = "global"
      override val typeHintStrategy = StringTypeHintStrategy(when = TypeHintFrequency.WhenNecessary, typeHint = "_t")
    }
    context.registerGlobalKeyOverride(remapThis = "id", toThisInstead = "_id")
    context.registerClassLoader(Play.classloader)
    context
  }
}

import mongoContext.context

object Registrations {
  val registrations = MongoConnection()("sampleapp2")("registrations")

  def all = registrations.map(grater[RegistrationXXX].asObject(_)).toList


  def create(registration: RegistrationXXX) {
    registrations += grater[RegistrationXXX].asDBObject(registration)
  }
}
