package ke.co.banit.flowmartsdk.data.remote.api

import ke.co.banit.flowmartsdk.data.models.response.ApiResponse
import ke.co.banit.flowmartsdk.data.models.response.BaseResponse
import ke.co.banit.flowmartsdk.data.models.response.category.CategoriesListResponse
import ke.co.banit.flowmartsdk.data.models.response.category.UpdateCategoryResponse
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

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
 * Service interface defining API calls related to category management within the FlowMart API.
 *
 * This interface provides endpoints for fetching, creating, updating, and deleting categories.
 * Each method corresponds to a specific HTTP operation on the categories resource.
 */
internal interface CategoryApiService {

    /**
     * Retrieves a list of all categories available in the FlowMart inventory.
     *
     * @return A [Response] wrapping an [ApiResponse] containing [CategoriesListResponse] with details of each category.
     */
    @GET("categories")
    suspend fun getAllCategories(): Response<ApiResponse<CategoriesListResponse>>

    /**
     * Creates a new category in the FlowMart inventory.
     *
     * @param body The request payload containing the details of the category to create.
     * @return A [Response] wrapping an [ApiResponse] containing [BaseResponse], which provides a message indicating
     *         the success or failure of the category creation.
     */
    @POST("categories")
    suspend fun createCategory(@Body body: RequestBody): Response<ApiResponse<BaseResponse>>

    /**
     * Updates an existing category in the FlowMart inventory.
     *
     * @param id The unique identifier of the category to update.
     * @param body The request payload containing updated details for the specified category.
     * @return A [Response] wrapping an [ApiResponse] containing [UpdateCategoryResponse], which includes
     *         the updated category details and a success message.
     */
    @PUT("categories/{id}")
    suspend fun updateCategory(
        @Path("id") id: Int,
        @Body body: RequestBody
    ): Response<ApiResponse<UpdateCategoryResponse>>

    /**
     * Deletes a category from the FlowMart inventory.
     *
     * @param id The unique identifier of the category to delete.
     * @return A [Response] wrapping an [ApiResponse] containing [BaseResponse], which provides a message
     *         indicating the success or failure of the deletion operation.
     */
    @DELETE("categories/{id}")
    suspend fun deleteCategory(@Path("id") id: Int): Response<ApiResponse<BaseResponse>>
}
