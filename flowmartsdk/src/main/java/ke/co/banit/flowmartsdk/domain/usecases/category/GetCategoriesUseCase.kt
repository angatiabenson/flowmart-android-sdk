package ke.co.banit.flowmartsdk.domain.usecases.category

import ke.co.banit.flowmartsdk.data.models.response.category.CategoriesListResponse
import ke.co.banit.flowmartsdk.domain.repositories.CategoryRepository
import ke.co.banit.flowmartsdk.util.Result
import ke.co.banit.flowmartsdk.util.handleResult
import ke.co.banit.flowmartsdk.util.runCatchingResult

/**
 *  FlowMart SDK
 *
 *  Copyright (c) 2024 Angatia Benson
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at:
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

/**
 * Use case for retrieving all categories from the FlowMart API.
 *
 * This use case encapsulates the logic for invoking the [CategoryRepository] to fetch all categories,
 * allowing the application to handle category retrieval in a consistent and reusable manner.
 *
 * @property categoryRepository An instance of [CategoryRepository] used to perform the category retrieval operation.
 */
internal class GetCategoriesUseCase(private val categoryRepository: CategoryRepository) {

    /**
     * Retrieves all categories by delegating the operation to the [CategoryRepository].
     *
     * @return A [Result] containing either [CategoriesListResponse] if successful, or an [Exception] on failure.
     */
    suspend operator fun invoke(): Result<CategoriesListResponse, Exception> {
        return runCatchingResult {
            categoryRepository.getAllCategories()
        }.handleResult()
    }
}
