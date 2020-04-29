package io.github.typeness.spray.json.refined

import eu.timepit.refined._
import eu.timepit.refined.api.Refined
import eu.timepit.refined.auto._
import eu.timepit.refined.collection.NonEmpty
import eu.timepit.refined.numeric.Positive
import org.scalatest.funsuite.AnyFunSuite
import spray.json.DefaultJsonProtocol._
import spray.json._


class SprayJsonRefinedSpec extends AnyFunSuite {

  test("Write to JSON positive int") {
    val positive: Int Refined Positive = 5
    assert(positive.toJson == JsNumber(positive))
  }
  test("Write to JSON case class with refined field") {
    case class Id(value: String Refined NonEmpty)
    implicit val idFormat: RootJsonFormat[Id] = jsonFormat1(Id.apply)
    val id = "1234"
    assert(refineV[NonEmpty](id).map(Id).toJson == JsObject("value" -> JsString(id)))
  }
  test("Read from JSON positive int") {
    val json = JsNumber(1)
    assert(json.convertTo[Int Refined Positive] == (1: Int Refined Positive))
  }
  test("Fail to read JSON negative int") {
    val json = JsNumber(-1)
    intercept[DeserializationException] {
      json.convertTo[Int Refined Positive]
    }
  }
  test("Read from JSON object with refined field") {
    case class Id(value: String Refined NonEmpty)
    implicit val idFormat: RootJsonFormat[Id] = jsonFormat1(Id.apply)
    assert(JsObject("value" -> JsString("1234")).convertTo[Id] == Id("1234"))
  }
}
