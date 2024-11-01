package ke.co.banit.flowmartsdk.data.remote.api

import ke.co.banit.flowmartsdk.data.models.response.ApiResponse
import ke.co.banit.flowmartsdk.data.models.response.product.CreateProductResponse
import ke.co.banit.flowmartsdk.data.models.response.product.DeleteProductResponse
import ke.co.banit.flowmartsdk.data.models.response.product.ProductsByCategoryResponse
import ke.co.banit.flowmartsdk.data.models.response.product.ProductsListResponse
import ke.co.banit.flowmartsdk.data.models.response.product.UpdateProductResponse
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
internal interface ProductApiService {
    @GET("products")
    suspend fun getAllProducts(): Response<ApiResponse<ProductsListResponse>>

    @POST("products")
    suspend fun createProduct(@Body body: RequestBody): Response<ApiResponse<CreateProductResponse>>

    @GET("categories/{category_id}/products")
    suspend fun getProductsByCategory(@Path("category_id") categoryId: Int): Response<ApiResponse<ProductsByCategoryResponse>>

    @PUT("products/{id}")
    suspend fun updateProduct(
        @Path("id") id: Int,
        @Body product: RequestBody
    ): Response<ApiResponse<UpdateProductResponse>>

    @DELETE("products/{id}")
    suspend fun deleteProduct(@Path("id") id: Int): Response<ApiResponse<DeleteProductResponse>>
}