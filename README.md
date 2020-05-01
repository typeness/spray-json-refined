# spray-json-refined

A library that integrates [spray-json](https://github.com/spray/spray-json) and [refined](https://github.com/fthomas/refined)

Given instance of `JsonFormat[T]` this library derives instance of `JsonFormat[T Refined P]`

## Installation
Supported Scala versions: `2.13`, `2.12`

Add the following to your `build.sbt`
`libraryDependencies += "io.github.typeness" %% "spray-json-refined" % "<version>"`


## Usage

### Read from JSON
``` scala
import eu.timepit.refined.api.Refined
import eu.timepit.refined.auto._
import eu.timepit.refined._
import eu.timepit.refined.collection.NonEmpty
import spray.json.DefaultJsonProtocol._
import spray.json._
import io.github.typeness.spray.json.refined._

case class Id(value: String Refined NonEmpty)
implicit val idFormat: RootJsonFormat[Id] = jsonFormat1(Id.apply)
assert(JsObject("value" -> JsString("1234")).convertTo[Id] == Id("1234"))
```

### Write to JSON
``` scala
import eu.timepit.refined.api.Refined
import eu.timepit.refined.auto._
import eu.timepit.refined._
import eu.timepit.refined.collection.NonEmpty
import spray.json.DefaultJsonProtocol._
import spray.json._
import io.github.typeness.spray.json.refined._

case class Id(value: String Refined NonEmpty)
implicit val idFormat: RootJsonFormat[Id] = jsonFormat1(Id.apply)
val id = "1234"
assert(refineV[NonEmpty](id).map(Id).toJson == JsObject("value" -> JsString(id)))
```
