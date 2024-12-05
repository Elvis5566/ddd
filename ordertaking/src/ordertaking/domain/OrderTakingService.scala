package ordertaking.domain

import zio.prelude.NonEmptyList
import zio.prelude.fx.ZPure
import zio.stm.ZSTM
import zio.{IO, Task, UIO, ZIO, ZLayer}

import java.time.LocalDateTime
import scala.collection.mutable.ListBuffer

private trait OrderTakingService {
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

  import OpaqueTypes.*
//
//
//
//  type KilogramQuantity // double
//
//
//
//  type OrderLineId
//  case class PersonalName(firstName: String50, lastName: String50)
//  case class CustomerInfo(name: PersonalName, emailAddress: EmailAddress)
//
//  type ShippingAddress = String
//  type BillingAddress
//  opaque type Price = Int
//  type BillingAmount
  type ProductCode = WidgetCode | GizmoCode
//  type OrderQuantity = UnitQuantity | KilogramQuantity
//
//  case class UnvalidatedOrder(
//      orderId: String,
//      firstName: String,
//      lastName: String,
//      shippingAddress: UnvalidatedAddress
//  )
//
//  case class ValidatedOrder(
//      orderId: OrderId,
//      customerInfo: CustomerInfo,
//      shippingAddress: ValidateAddress,
//      billingAddress: BillingAddress,
//      orderLines: List[ValidateOrderLine]
//  )
//
//  case class PlacedOrder(
//      orderId: OrderId,
//      customerInfo: CustomerInfo,
//      shippingAddress: ValidateAddress,
//      billingAddress: BillingAddress,
//      orderLines: List[ValidateOrderLine],
//      amountToBill: BillingAmount
//  )
//
//  type Order = UnvalidatedOrder | ValidatedOrder | PlacedOrder
//
//  def validateAddress: UnvalidatedAddress => Option[ValidateAddress] = ???
//
//  type EmailAddress = String
//  opaque type UnverifiedEmail = EmailAddress
//  opaque type VerifiedEmail = EmailAddress
//  type CustomerEmail = UnverifiedEmail | VerifiedEmail
//
//  def sendPasswordResetEmail: VerifiedEmail => Unit
//
//  case class OrderLine(
//      id: OrderLineId,
//      orderId: OrderId,
//      productCode: ProductCode,
//      orderQuantity: OrderQuantity,
//      var price: Price
//  )
//
//  type ValidateOrderLine
//
//  type AcknowledgmentSent
//
//  case class PlaceOrderEvents(
//      acknowledgmentSent: AcknowledgmentSent,
//      orderPlaced: OrderPlaced,
//      billableOrderPlaced: BillableOrderPlaced
//  )
//
//  case class ValidationError(fileName: String, errorDescription: String)
//  type PlaceOrderError = ValidationError
//
//  type EnvelopContents
//  type QuoteForm
//  type OrderForm
//  type CategorizedMail = QuoteForm | OrderForm
//  type CategorizeInboundMail = EnvelopContents => CategorizedMail
//
//  type ProductCatalog
//  type PricedOrder
//  type CalculatePrices = (OrderForm, ProductCatalog) => PricedOrder
////  def calculatePrices(orderForm: OrderForm): PricedOrder
//
//  def changeOrderLinePrice: (Order, OrderLineId, Price) => Order
//
//  def getProductPrice(productCode: ProductCode): Price
//
//  type GetProductPrice = ProductCode => Price
//
//  opaque type HtmlString = String
//  case class OrderAcknowledgment(emailAddress: EmailAddress, letter: HtmlString)
//
//  type CreateOrderAcknowledmentLetter = PricedOrder => HtmlString
//  def createOrderAcknowledmentLetter(pricedOrder: PricedOrder): HtmlString
//
//  type Sent
//  type NotSent
//  type SendResult = Sent | NotSent
//  type SendOrderAcknowledgment = OrderAcknowledgment => UIO[SendResult]
//
//  def sendOrderAcknowledgment(
//      orderAcknowledgment: OrderAcknowledgment
//  ): SendResult
//
//  case class OrderAcknowledgmentSent(
//      orderId: OrderId,
//      emailAddress: EmailAddress
//  )
//
//  type OrderPlaced = PricedOrder
//  case class BillableOrderPlaced(
//      orderId: OrderId,
//      billingAddress: BillingAddress,
//      amountToBill: BillingAmount
//  )

//  type PlaceOrderEvent = OrderPlaced | BillableOrderPlaced |
//    OrderAcknowledgmentSent
//
//  type PricingError = String

  // workflow

//  def validateOrder
//      : UnvalidatedOrder => ZIO[ProductService, List[
//        AddressValidationError
//      ], ValidatedOrder]

//  def validateOrder(
//      unvalidatedOrder: UnvalidatedOrder
//  ): Either[ValidationError, ValidatedOrder]

//  type PriceOrder =
//    GetProductPrice => ValidatedOrder => Either[PricingError, PricedOrder]
//
//  def placeOrder(
//      getProductPrice: GetProductPrice,
//      validatedOrder: ValidatedOrder
//  ): Either[PricingError, PricedOrder]

//  type AcknowledgeOrder =
//    CreateOrderAcknowledmentLetter => SendOrderAcknowledgment => PricedOrder => Option[
//      OrderAcknowledgmentSent
//    ]

//  def acknowledgeOrder(
//      createOrderAcknowledmentLetter: CreateOrderAcknowledmentLetter,
//      sendOrderAcknowledgment: SendOrderAcknowledgment,
//      priceOrder: PriceOrder
//  ): Option[OrderAcknowledgmentSent]
//
//  type CreateEvents = PricedOrder => List[PlaceOrderEvent]
//
//  def createEvents(pricedOrder: PricedOrder): List[PlaceOrderEvent]
//
//  type PlaceOrderWorkflow =
//    UnvalidatedOrder => IO[List[PlaceOrderEvent], PlaceOrderError]

//  def workflow(unvalidatedOrder: UnvalidatedOrder) =
//    for
//      s <- validateOrder(unvalidatedOrder)
//
//    yield
}

protected object OrderTakingService extends OrderTakingService {
  import OrderTakingService.*
  import OrderTakingService.OpaqueTypes.*
  def foo() =
    product.domain.Api.checkProductCodeExists(WidgetCode("abcd1234").toString)

}
