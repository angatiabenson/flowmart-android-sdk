package ke.co.banit.flowmartsdk.domain.repositories

import ke.co.banit.flowmartsdk.data.models.response.category.CategoriesListResponse
import ke.co.banit.flowmartsdk.data.models.response.category.CreateCategoryResponse
import ke.co.banit.flowmartsdk.data.models.response.category.DeleteCategoryResponse
import ke.co.banit.flowmartsdk.data.models.response.category.UpdateCategoryResponse
import ke.co.banit.flowmartsdk.util.Result

/**
 * @Author: Angatia Benson
 * @Date: 10/28/2024
 * Copyright (c) 2024 BanIT
 */
internal interface CategoryRepository {
    suspend fun getAllCategories(): Result<CategoriesListResponse, Exception>
    suspend fun createCategory(name: String): Result<CreateCategoryResponse, Exception>
    suspend fun updateCategory(id: Int, name: String): Result<UpdateCategoryResponse, Exception>
    suspend fun deleteCategory(id: Int): Result<DeleteCategoryResponse, Exception>
}