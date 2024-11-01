package ke.co.banit.flowmartsdk.data.models.response.category

import ke.co.banit.flowmartsdk.data.models.dto.Category

data class CategoriesListResponse(
    val categories: List<Category>,
)