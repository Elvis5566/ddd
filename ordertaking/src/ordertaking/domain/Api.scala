package ordertaking.domain

import zio.http.*

object Api:
  val routes =
    Routes(
      Method.GET / "ordertaking" -> handler {
        Response.text("take order")
      }
    )

  def workflow(i: Int) = OrderTakingService.workflow(i)
