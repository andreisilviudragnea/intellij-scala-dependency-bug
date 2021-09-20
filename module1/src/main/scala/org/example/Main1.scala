package org.example

import cats.Show
import cats.syntax.show._

object Main1 extends App {
  class MyException {
    def getMessage: String = this.show
  }

  implicit val exceptionShow: Show[MyException] = Show.show { _ => "show"}

  println(new MyException().getMessage)
}
