package ordertaking.dao

import io.getquill.*
import io.getquill.jdbczio.Quill
import ordertaking.domain.OrderTakingService.Order
import zio.{Task, ZIO, ZLayer}

import java.sql.SQLException

class OrderDaoLive(quill: Quill.Postgres[SnakeCase]) extends OrderDAO:
  import quill.*

  val ctx = new SqlMirrorContext(MirrorSqlDialect, Literal)
//  import ctx._

  private inline def query = quote(querySchema[Order](entity = "orders"))

  def insert(order: Order): Task[Long] = run(query.insertValue(lift(order)))

object OrderDaoLive:
  val layer = ZLayer.derive[OrderDaoLive]
