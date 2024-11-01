package ke.co.banit.flowmartsdk.data.models.response.product

import ke.co.banit.flowmartsdk.data.models.dto.Product

data class UpdateProductResponse(
    val message: String,
    val product: Product
)