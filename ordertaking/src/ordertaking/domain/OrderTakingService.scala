package ordertaking.domain

import zio.prelude.NonEmptyList
import zio.prelude.fx.ZPure
import zio.stm.ZSTM
import zio.{IO, Task, UIO, ZIO, ZLayer}

import java.time.LocalDateTime
import scala.collection.mutable.ListBuffer

private[ordertaking] trait OrderTakingService {
  // type 跟workflow 都寫在這trait, 實作在companion object
  object OpaqueTypes:
    opaque type OrderId = String

    object OrderId:
      def apply(s: String): Option[OrderId] =
        Some(s).filter(_.length <= 50 && s.nonEmpty)

    opaque type AddressValidationError = String

    opaque type UnitQuantity = Int

    object UnitQuantity:
      def apply(i: Int): Either[String, UnitQuantity] =
        if (i < 1 || i > 1000) Left("quantity is between 1 ~ 1000")
        else Right(i)

    opaque type String50 = String
    object String50:
      def apply(s: String): Option[String50] =
        Some(s).filter(_ => s.nonEmpty && s.length <= 50)

    opaque type WidgetCode = String
    object WidgetCode:
      def apply(s: String): WidgetCode = s
    extension (code: WidgetCode) def toString: String = code

    //      override def toString: String = code

    type GizmoCode = String

  end OpaqueTypes

//  import OpaqueTypes.*

  case class Order(id: Int, name: String)

  def action1(i: Int): Option[Int]
  def action2(i: Int): Option[Int]
  def action3(i: Int): Option[Int]

  def workflow(i: Int): Option[Boolean] =
    for
      i1 <- action1(i)
      i2 <- action2(i1)
      i3 <- action3(i2)
    yield product.domain.Api.checkProductCodeExists(i3.toString) // 用到其他module 直接call function call, 有需要移出去再改http request 
}

private[ordertaking] object OrderTakingService extends OrderTakingService {
  import OrderTakingService.*
  import OrderTakingService.OpaqueTypes.*

  def action1(i: Int) = Some(i)
  def action2(i: Int) = Some(i)
  def action3(i: Int) = Some(i)

}
