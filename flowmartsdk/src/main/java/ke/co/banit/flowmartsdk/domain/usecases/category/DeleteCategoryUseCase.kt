package ke.co.banit.flowmartsdk.domain.usecases.category

import ke.co.banit.flowmartsdk.data.models.response.BaseResponse
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
 * Use case for deleting a category in the FlowMart API.
 *
 * This use case encapsulates the logic for invoking the [CategoryRepository] to delete a category,
 * allowing the application to handle category deletion in a consistent and reusable manner.
 *
 * @property categoryRepository An instance of [CategoryRepository] used to perform the category deletion operation.
 */
internal class DeleteCategoryUseCase(private val categoryRepository: CategoryRepository) {

    /**
     * Deletes a category by delegating the operation to the [CategoryRepository].
     *
     * @param id The unique identifier of the category to be deleted.
     * @return A [Result] containing either [BaseResponse] if successful, or an [Exception] on failure.
     */
    suspend operator fun invoke(id: Int): Result<BaseResponse, Exception> {
        return runCatchingResult {
            categoryRepository.deleteCategory(id)
        }.handleResult()
    }
}
