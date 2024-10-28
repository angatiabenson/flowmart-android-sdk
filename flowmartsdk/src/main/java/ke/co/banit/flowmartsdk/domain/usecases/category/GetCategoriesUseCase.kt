package ke.co.banit.flowmartsdk.domain.usecases.category

import ke.co.banit.flowmartsdk.domain.models.Category
import ke.co.banit.flowmartsdk.domain.repositories.CategoryRepository
import ke.co.banit.flowmartsdk.domain.util.Result

/**
 * @Author: Angatia Benson
 * @Date: 10/28/2024
 * Copyright (c) 2024 BanIT
 */

class GetCategoriesUseCase(private val categoryRepository: CategoryRepository) {
    suspend operator fun invoke(): Result<List<Category>> {
        return categoryRepository.getAllCategories()
    }
}