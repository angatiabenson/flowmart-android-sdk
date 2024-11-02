package ke.co.banit.flowmartsdk.domain.usecases.category

import ke.co.banit.flowmartsdk.data.models.response.category.UpdateCategoryResponse
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
 * Use case for updating a category in the FlowMart API.
 *
 * This use case encapsulates the logic for invoking the [CategoryRepository] to update a category's details,
 * allowing the application to handle category updates in a consistent and reusable manner.
 *
 * @property categoryRepository An instance of [CategoryRepository] used to perform the category update operation.
 */
internal class UpdateCategoryUseCase(private val categoryRepository: CategoryRepository) {

    /**
     * Updates a category by delegating the operation to the [CategoryRepository].
     *
     * @param id The unique identifier of the category to update.
     * @param name The new name for the category.
     * @return A [Result] containing either [UpdateCategoryResponse] if successful, or an [Exception] on failure.
     */
    suspend operator fun invoke(id: Int, name: String): Result<UpdateCategoryResponse, Exception> {
        return runCatchingResult {
            categoryRepository.updateCategory(id, name)
        }.handleResult()
    }
}
