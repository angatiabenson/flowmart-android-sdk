package ke.co.banit.flowmartsdk.data.models.response.category

import ke.co.banit.flowmartsdk.data.models.dto.Category

data class UpdateCategoryResponse(
    val category: Category,
    val message: String,
)