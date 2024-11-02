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
 * Use case for creating a new category in the FlowMart API.
 *
 * This use case encapsulates the logic for invoking the [CategoryRepository] to create a category,
 * allowing the application to handle category creation in a consistent and reusable manner.
 *
 * @property categoryRepository An instance of [CategoryRepository] used to perform the category creation operation.
 */
internal class CreateCategoryUseCase(private val categoryRepository: CategoryRepository) {

    /**
     * Creates a new category by delegating the operation to the [CategoryRepository].
     *
     * @param name The name of the category to be created.
     * @return A [Result] containing either [BaseResponse] if successful, or an [Exception] on failure.
     */
    suspend operator fun invoke(name: String): Result<BaseResponse, Exception> {
        if (name.isBlank()) return Result.Error(Exception("Name cannot be blank"))
        return runCatchingResult {
            categoryRepository.createCategory(name)
        }.handleResult()
    }
}
