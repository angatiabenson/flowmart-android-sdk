package ke.co.banit.flowmartsdk.data.models.response.product

import ke.co.banit.flowmartsdk.data.models.dto.Product

data class ProductsListResponse(
    val products: List<Product>,
)