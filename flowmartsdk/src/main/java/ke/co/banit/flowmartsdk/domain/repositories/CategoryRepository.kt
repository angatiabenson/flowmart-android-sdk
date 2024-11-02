package ke.co.banit.flowmartsdk.domain.repositories

import ke.co.banit.flowmartsdk.data.models.response.BaseResponse
import ke.co.banit.flowmartsdk.data.models.response.category.CategoriesListResponse
import ke.co.banit.flowmartsdk.data.models.response.category.UpdateCategoryResponse
import ke.co.banit.flowmartsdk.util.Result

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
 * Interface defining repository operations for managing categories within the FlowMart API.
 *
 * The `CategoryRepository` provides abstract functions to handle fetching, creating, updating,
 * and deleting categories, returning results wrapped in [Result] to indicate success or failure.
 */
internal interface CategoryRepository {

    /**
     * Retrieves a list of all categories from the FlowMart API.
     *
     * @return A [Result] containing either [CategoriesListResponse] if successful, or an [Exception] on failure.
     */
    suspend fun getAllCategories(): Result<CategoriesListResponse, Exception>

    /**
     * Creates a new category in the FlowMart API.
     *
     * @param name The name of the category to be created.
     * @return A [Result] containing either [BaseResponse] if successful, or an [Exception] on failure.
     */
    suspend fun createCategory(name: String): Result<BaseResponse, Exception>

    /**
     * Updates an existing category in the FlowMart API.
     *
     * @param id The unique identifier of the category to update.
     * @param name The new name for the category.
     * @return A [Result] containing either [UpdateCategoryResponse] if successful, or an [Exception] on failure.
     */
    suspend fun updateCategory(id: Int, name: String): Result<UpdateCategoryResponse, Exception>

    /**
     * Deletes a category from the FlowMart API.
     *
     * @param id The unique identifier of the category to delete.
     * @return A [Result] containing either [BaseResponse] if successful, or an [Exception] on failure.
     */
    suspend fun deleteCategory(id: Int): Result<BaseResponse, Exception>
}
