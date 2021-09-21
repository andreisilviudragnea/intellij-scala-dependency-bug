package org.example

import akka.http.scaladsl.server.directives.HostDirectives

object ClosureBreakpointBug extends App {
  def method(f: String => String): String = {
    f("string")
  }

  val v =
    method(_.concat("ceva"))

  HostDirectives.extractHost
}
