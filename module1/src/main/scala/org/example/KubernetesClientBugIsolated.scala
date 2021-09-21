package org.example

import cats.effect.{Async, IO}
import com.goyeau.kubernetes.client.{KubeConfig, KubernetesClient}
import io.circe.Decoder
import io.k8s.api.core.v1.ConfigMap
import org.http4s.Uri
import org.http4s.client.Client

object KubernetesClientBugIsolated extends App {
  def method(client: KubernetesClient[IO]): IO[Unit] = {
    for {
      cm <- new NamespacedConfigMapsApi[IO]().get("")
      _ = useConfigMap(cm)
    } yield ()
  }

  def useConfigMap(cm: ConfigMap): Unit = ()

  class NamespacedConfigMapsApi[F[_]]()(implicit
                                                       val F: Async[F],
                                                     ) extends Gettable[F, ConfigMap] {
    override protected def resourceUri: Uri = ???

    override def get(name: String): F[ConfigMap] = ???

    override protected def httpClient: Client[F] = ???

    override protected def config: KubeConfig = ???

    override implicit protected def resourceDecoder: Decoder[ConfigMap] = ???
  }

  trait Gettable[F[_], Resource] {
    protected def httpClient: Client[F]
    implicit protected val F: Async[F]
    protected def config: KubeConfig
    protected def resourceUri: Uri
    implicit protected def resourceDecoder: Decoder[Resource]

    def get(name: String): F[Resource]
  }
}
