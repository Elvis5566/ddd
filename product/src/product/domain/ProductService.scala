package product.domain

private[domain] trait ProductService:
  def checkProductCodeExists(code: String): Boolean = true

private[domain] object ProductService extends ProductService
