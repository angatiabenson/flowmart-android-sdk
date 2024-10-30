package ke.co.banit.flowmartsdk.data.remote.api

import ke.co.banit.flowmartsdk.data.models.response.category.CategoriesListResponse
import ke.co.banit.flowmartsdk.data.models.response.category.CreateCategoryResponse
import ke.co.banit.flowmartsdk.data.models.response.category.DeleteCategoryResponse
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
 * @Author: Angatia Benson
 * @Date: 10/30/2024
 * Copyright (c) 2024 BanIT
 */

interface CategoryApiService {
    @GET("categories")
    suspend fun getAllCategories(): Response<CategoriesListResponse>

    @POST("categories")
    suspend fun createCategory(@Body body: RequestBody): Response<CreateCategoryResponse>

    @PUT("categories/{id}")
    suspend fun updateCategory(
        @Path("id") id: Int,
        @Body body: RequestBody
    ): Response<UpdateCategoryResponse>

    @DELETE("categories/{id}")
    suspend fun deleteCategory(@Path("id") id: Int): Response<DeleteCategoryResponse>
}