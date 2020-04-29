package io.github.typeness.spray.json

import eu.timepit.refined.api._
import spray.json._

package object refined {

  implicit def formatRefined[T, P, F[_, _]](implicit jsonFormat: JsonFormat[T],
                                            refType: RefType[F],
                                            validate: Validate[T, P]): RootJsonFormat[F[T, P]] = new RootJsonFormat[F[T, P]] {
    override def write(obj: F[T, P]): JsValue = jsonFormat.write(refType.unwrap(obj))

    override def read(json: JsValue): F[T, P] = refType.refine[P](jsonFormat.read(json)) match {
      case Left(value) => deserializationError(value)
      case Right(value) => value
    }
  }
}
