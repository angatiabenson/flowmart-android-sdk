package ke.co.banit.flowmartsdk.domain.repositories

import ke.co.banit.flowmartsdk.domain.models.Category
import ke.co.banit.flowmartsdk.domain.util.Result

/**
 * @Author: Angatia Benson
 * @Date: 10/28/2024
 * Copyright (c) 2024 BanIT
 */
interface CategoryRepository {
    suspend fun getAllCategories(): Result<List<Category>>
    suspend fun createCategory(name: String): Result<Category>
    suspend fun updateCategory(id: Int, name: String): Result<Category>
    suspend fun deleteCategory(id: Int): Result<Unit>
}