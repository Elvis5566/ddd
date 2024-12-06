import db.{Db, DbConfig, DbMigrator}
import zio.*
import zio.http.*

object HttpServer extends ZIOAppDefault {
  val routes =
    Routes(
      Method.GET / Root -> handler(Response.text("Greetings at your service")),
      Method.GET / "greet" -> handler { (req: Request) =>
        val name = req.queryParamToOrElse("name", "World")
        Response.text(s"Hello $name!")
      }
    ) ++ ordertaking.domain.Api.routes
      ++ product.domain.Api.routes

  val services = List("ordertaking", "product")

  def run =
    (for
      migrator <- ZIO.service[DbMigrator]
      _ <- ZIO.collectAll(services.map(migrator.migrate))
      _ <- Server.serve(routes)
    yield ())
      .provide(
        Server.default,
        DbMigrator.live,
        Db.dataSourceLive,
        DbConfig.live
      )
}
