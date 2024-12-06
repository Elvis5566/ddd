package db

import zio.config.magnolia.deriveConfig
import zio.config.typesafe.FromConfigSourceTypesafe
import zio.{Config, ConfigProvider, ZLayer}

case class DbConfig(url: String, username: String, password: String, driver: String)

object DbConfig {
  val live: ZLayer[Any, Config.Error, DbConfig] =
    ZLayer.fromZIO(ConfigProvider.fromResourcePath().load(deriveConfig[DbConfig].nested("db")))
}
