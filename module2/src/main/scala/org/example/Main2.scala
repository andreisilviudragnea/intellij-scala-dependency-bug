package org.example

import cats.effect.IO

object Main2 extends App {
  println(IO.pure(5).unsafeRunSync())
}
