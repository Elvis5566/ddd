package ordertaking.dao

import ordertaking.domain.OrderTakingService.Order
import zio.Task

trait OrderDAO:
  def insert(person: Order): Task[Long]
