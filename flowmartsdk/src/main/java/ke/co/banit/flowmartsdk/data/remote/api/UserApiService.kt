package ke.co.banit.flowmartsdk.data.remote.api

import ke.co.banit.flowmartsdk.data.models.response.ApiResponse
import ke.co.banit.flowmartsdk.data.models.response.BaseResponse
import ke.co.banit.flowmartsdk.data.models.response.user.LoginUserResponse
import ke.co.banit.flowmartsdk.data.models.response.user.UserResponse
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT

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
 * Service interface defining API calls related to user account management within the FlowMart API.
 *
 * This interface provides endpoints for user registration, login, profile management, and account deletion.
 */
internal interface UserApiService {

    /**
     * Registers a new user in the FlowMart system.
     *
     * @param body The request payload containing user registration details.
     * @return A [Response] wrapping an [ApiResponse] containing [UserResponse], which includes the newly registered user details.
     */
    @POST("register")
    suspend fun registerUser(@Body body: RequestBody): Response<ApiResponse<UserResponse>>

    /**
     * Logs in an existing user in the FlowMart system.
     *
     * @param body The request payload containing user login credentials.
     * @return A [Response] wrapping an [ApiResponse] containing [LoginUserResponse], which includes the login status and user details.
     */
    @POST("login")
    suspend fun loginUser(@Body body: RequestBody): Response<ApiResponse<LoginUserResponse>>

    /**
     * Retrieves the profile details of the currently authenticated user.
     *
     * @return A [Response] wrapping an [ApiResponse] containing [UserResponse], which provides details of the user's profile.
     */
    @GET("user")
    suspend fun getUserProfile(): Response<ApiResponse<UserResponse>>

    /**
     * Updates the profile of the currently authenticated user.
     *
     * @param body The request payload containing updated profile information.
     * @return A [Response] wrapping an [ApiResponse] containing [UserResponse], which reflects the updated user profile.
     */
    @PUT("user")
    suspend fun updateUserProfile(@Body body: RequestBody): Response<ApiResponse<UserResponse>>

    /**
     * Deletes the account of the currently authenticated user.
     *
     * @return A [Response] wrapping an [ApiResponse] containing [BaseResponse], which provides a message indicating the success or status of the account deletion.
     */
    @DELETE("user")
    suspend fun deleteUserAccount(): Response<ApiResponse<BaseResponse>>
}
