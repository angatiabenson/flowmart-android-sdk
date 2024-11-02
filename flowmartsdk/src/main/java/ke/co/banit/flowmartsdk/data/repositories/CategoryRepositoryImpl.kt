package ke.co.banit.flowmartsdk.data.repositories

import ke.co.banit.flowmartsdk.data.models.response.BaseResponse
import ke.co.banit.flowmartsdk.data.models.response.category.CategoriesListResponse
import ke.co.banit.flowmartsdk.data.models.response.category.UpdateCategoryResponse
import ke.co.banit.flowmartsdk.data.remote.api.CategoryApiService
import ke.co.banit.flowmartsdk.domain.repositories.CategoryRepository
import ke.co.banit.flowmartsdk.util.Result
import ke.co.banit.flowmartsdk.util.handleApiError
import okhttp3.MultipartBody


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
 * Implementation of [CategoryRepository] for managing category operations within the FlowMart API.
 *
 * This class uses [CategoryApiService] to perform network operations for categories, including fetching,
 * creating, updating, and deleting categories. It handles API responses, error handling, and provides results
 * wrapped in [Result] to indicate success or failure.
 *
 * @property categoryApiService An instance of [CategoryApiService] used to make category-related API calls.
 */
internal class CategoryRepositoryImpl(private val categoryApiService: CategoryApiService) :
    CategoryRepository {

    /**
     * Retrieves a list of all categories from the FlowMart API.
     *
     * @return A [Result] containing either [CategoriesListResponse] if successful, or an [Exception] on failure.
     */
    override suspend fun getAllCategories(): Result<CategoriesListResponse, Exception> {
        return try {
            val response = categoryApiService.getAllCategories()
            if (response.isSuccessful) {
                val result = response.body()?.data!!
                Result.Success(result)
            } else {
                handleApiError(response)
            }
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    /**
     * Creates a new category in the FlowMart API.
     *
     * @param name The name of the category to be created.
     * @return A [Result] containing either [BaseResponse] if successful, or an [Exception] on failure.
     */
    override suspend fun createCategory(name: String): Result<BaseResponse, Exception> {
        return try {
            val builder = MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("name", name)
            val requestBody = builder.build()

            val response = categoryApiService.createCategory(body = requestBody)
            if (response.isSuccessful) {
                val result = response.body()?.data!!
                Result.Success(result)
            } else {
                handleApiError(response)
            }
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    /**
     * Updates an existing category in the FlowMart API.
     *
     * @param id The unique identifier of the category to update.
     * @param name The new name for the category.
     * @return A [Result] containing either [UpdateCategoryResponse] if successful, or an [Exception] on failure.
     */
    override suspend fun updateCategory(
        id: Int, name: String
    ): Result<UpdateCategoryResponse, Exception> {
        return try {
            val builder = MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("name", name)
            val requestBody = builder.build()

            val response = categoryApiService.updateCategory(id = id, body = requestBody)
            if (response.isSuccessful) {
                val result = response.body()?.data!!
                Result.Success(result)
            } else {
                handleApiError(response)
            }
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    /**
     * Deletes a category from the FlowMart API.
     *
     * @param id The unique identifier of the category to delete.
     * @return A [Result] containing either [BaseResponse] if successful, or an [Exception] on failure.
     */
    override suspend fun deleteCategory(id: Int): Result<BaseResponse, Exception> {
        return try {
            val response = categoryApiService.deleteCategory(id)
            if (response.isSuccessful) {
                val result = response.body()?.data!!
                Result.Success(result)
            } else {
                handleApiError(response)
            }
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}
