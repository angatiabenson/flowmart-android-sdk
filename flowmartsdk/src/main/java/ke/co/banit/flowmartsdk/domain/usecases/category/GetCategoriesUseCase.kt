package ke.co.banit.flowmartsdk.domain.usecases.category

import ke.co.banit.flowmartsdk.data.models.response.category.CategoriesListResponse
import ke.co.banit.flowmartsdk.domain.repositories.CategoryRepository
import ke.co.banit.flowmartsdk.util.Result
import ke.co.banit.flowmartsdk.util.handleResult
import ke.co.banit.flowmartsdk.util.runCatchingResult

/**
 * @Author: Angatia Benson
 * @Date: 10/28/2024
 * Copyright (c) 2024 BanIT
 */

internal class GetCategoriesUseCase(private val categoryRepository: CategoryRepository) {
    suspend operator fun invoke(): Result<CategoriesListResponse, Exception> {
        return runCatchingResult {
            categoryRepository.getAllCategories()
        }.handleResult()
    }
}