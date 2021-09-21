package org.example.client.api

import cats.effect.Async
import com.goyeau.kubernetes.client.KubeConfig
import com.goyeau.kubernetes.client.api.NamespacedConfigMapsApi
import com.goyeau.kubernetes.client.operation.{Listable, Watchable}
import io.circe.{Decoder, Encoder}
import io.k8s.api.core.v1.{ConfigMap, ConfigMapList}
import org.http4s.Uri
import org.http4s.client.Client

private[client] class ConfigMapsApi[F[_]](val httpClient: Client[F], val config: KubeConfig)(implicit
                                                                                             val F: Async[F],
                                                                                             val listDecoder: Decoder[ConfigMapList],
                                                                                             val resourceDecoder: Decoder[ConfigMap],
                                                                                             encoder: Encoder[ConfigMap]
) {
  def namespace(namespace: String): NamespacedConfigMapsApi[F] =
    new NamespacedConfigMapsApi(httpClient, config, namespace)
}
