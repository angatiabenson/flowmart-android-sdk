package ke.co.banit.flowmartsdk.data.remote.api

import ke.co.banit.flowmartsdk.data.models.response.user.DeleteUserResponse
import ke.co.banit.flowmartsdk.data.models.response.user.LoginUserResponse
import ke.co.banit.flowmartsdk.data.models.response.user.RegisterUserResponse
import ke.co.banit.flowmartsdk.data.models.response.user.UpdateUserProfileResponse
import ke.co.banit.flowmartsdk.data.models.response.user.UserProfileResponse
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT

/**
 * @Author: Angatia Benson
 * @Date: 10/30/2024
 * Copyright (c) 2024 BanIT
 */

interface UserApiService {
    @POST("register")
    suspend fun registerUser(@Body body: RequestBody): Response<RegisterUserResponse>

    @POST("login")
    suspend fun loginUser(@Body body: RequestBody): Response<LoginUserResponse>

    @GET("user")
    suspend fun getUserProfile(): Response<UserProfileResponse>

    @PUT("user")
    suspend fun updateUserProfile(@Body body: RequestBody): Response<UpdateUserProfileResponse>

    @DELETE("user")
    suspend fun deleteUserAccount(): Response<DeleteUserResponse>
}