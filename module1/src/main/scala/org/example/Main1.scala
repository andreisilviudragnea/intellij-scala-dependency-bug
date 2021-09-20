package org.example

import cats.Show
import cats.syntax.show._

import scala.util.control.NoStackTrace

object Main1 extends App {
  class MyException extends Exception with NoStackTrace {
    override def getMessage: String = this.show
  }

  implicit val exceptionShow: Show[MyException] = Show.show { _ => "show"}

  println(new MyException().getMessage)
}
