package org.example.client

import cats.effect.Async
import com.goyeau.kubernetes.client.KubeConfig
import org.example.client.api.ConfigMapsApi
import org.http4s.client.Client

class KubernetesClient[F[_]: Async](httpClient: Client[F], config: KubeConfig) {
  lazy val configMaps                = new ConfigMapsApi(httpClient, config)
}
