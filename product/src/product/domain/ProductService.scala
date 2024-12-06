package product.domain

private[domain] trait ProductService:
  def checkProductCodeExists(code: String): Boolean

private[domain] object ProductService extends ProductService:
  def checkProductCodeExists(code: String): Boolean = true
