package org.example.client.operation

import cats.effect.Async
import com.goyeau.kubernetes.client.KubeConfig
import io.circe.Decoder
import org.http4s.Method.GET
import org.http4s.{Request, Uri}
import org.http4s.client.Client

private[client] trait Gettable[F[_], Resource] {
  protected def httpClient: Client[F]
  implicit protected val F: Async[F]
  protected def config: KubeConfig
  protected def resourceUri: Uri
  implicit protected def resourceDecoder: Decoder[Resource]

  def get(name: String): F[Resource]
}
