package org.example

import cats.effect.IO
import io.k8s.api.core.v1.ConfigMap
import org.example.client.KubernetesClient

object KubernetesClientBugIsolated extends App {
  def method(client: KubernetesClient[IO]): IO[Unit] = {
    for {
      cm <- client.configMaps.namespace("").get("")
      _ = useConfigMap(cm)
    } yield ()
  }

  def useConfigMap(cm: ConfigMap): Unit = ()

}
