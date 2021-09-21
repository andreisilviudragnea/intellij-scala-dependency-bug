package org.example

import cats.effect.IO
import com.goyeau.kubernetes.client.KubernetesClient
import io.k8s.api.core.v1.ConfigMap

object KubernetesClientBug extends App {
  def method(client: KubernetesClient[IO]): IO[Unit] = {
    for {
      cm <- client.configMaps.namespace("").get("")
      _ = useConfigMap(cm)
    } yield ()
  }

  def useConfigMap(cm: ConfigMap): Unit = ()
}
