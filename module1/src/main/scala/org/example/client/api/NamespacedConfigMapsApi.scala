package org.example.client.api

import cats.effect.Async
import com.goyeau.kubernetes.client.KubeConfig
import io.circe.{Decoder, Encoder}
import io.k8s.api.core.v1.{ConfigMap, ConfigMapList}
import org.example.client.operation.Gettable
import org.http4s.Uri
import org.http4s.client.Client

private[client] class NamespacedConfigMapsApi[F[_]](
                                                     val httpClient: Client[F],
                                                     val config: KubeConfig,
                                                     val namespace: String
                                                   )(implicit
                                                     val F: Async[F],
                                                     val resourceEncoder: Encoder[ConfigMap],
                                                     val resourceDecoder: Decoder[ConfigMap],
                                                     val listDecoder: Decoder[ConfigMapList]
                                                   ) extends Gettable[F, ConfigMap]
 {
   override protected def resourceUri: Uri = ???

   override def get(name: String): F[ConfigMap] = ???
 }
