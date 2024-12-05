package product.domain

import share.db.DbMigrator
import zio.http.*

object Api:
  val routes =
    Routes(
      Method.GET / "product" -> handler { (req: Request) =>
        val name = req.queryParamToOrElse("name", "World")
        Response.text(s"Product $name!")
      }
    )

  def checkProductCodeExists(code: String): Boolean =
    ProductService.checkProductCodeExists(code)
