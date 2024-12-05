package ordertaking.domain

import product.domain.Api.getClass
import share.db.DbMigrator
import zio.ZIO
import zio.http.*

object Api:
  val routes =
    Routes(
      Method.GET / "ordertaking" -> handler {
        Response.text("take order")
      }
    )