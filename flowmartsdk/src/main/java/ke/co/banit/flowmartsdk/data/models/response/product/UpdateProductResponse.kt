package ke.co.banit.flowmartsdk.data.models.response.product

data class UpdateProductResponse(
    val message: String,
    val product: Product,
    val status: String
) {
    data class Product(
        val category: Category,
        val id: Int,
        val name: String,
        val quantity: String
    ) {
        data class Category(
            val id: Int,
            val name: String
        )
    }
}