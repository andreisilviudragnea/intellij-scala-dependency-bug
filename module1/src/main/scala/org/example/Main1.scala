package org.example

import cats.effect.IO
import cats.effect.unsafe.IORuntime

object Main1 extends App {
  println(IO.pure(5).unsafeRunSync()(IORuntime.global))
}
