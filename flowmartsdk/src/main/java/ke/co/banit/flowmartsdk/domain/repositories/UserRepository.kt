package ke.co.banit.flowmartsdk.domain.repositories

import ke.co.banit.flowmartsdk.data.models.response.user.DeleteUserResponse
import ke.co.banit.flowmartsdk.data.models.response.user.LoginUserResponse
import ke.co.banit.flowmartsdk.data.models.response.user.RegisterUserResponse
import ke.co.banit.flowmartsdk.data.models.response.user.UpdateUserProfileResponse
import ke.co.banit.flowmartsdk.data.models.response.user.UserProfileResponse
import ke.co.banit.flowmartsdk.util.Result

/**
 * @Author: Angatia Benson
 * @Date: 10/28/2024
 * Copyright (c) 2024 BanIT
 */
interface UserRepository {
    suspend fun registerUser(
        name: String,
        email: String,
        phone: String,
        password: String
    ): Result<RegisterUserResponse, Exception>

    suspend fun loginUser(
        email: String,
        password: String
    ): Result<LoginUserResponse, Exception> // Returns API token

    suspend fun getUserProfile(): Result<UserProfileResponse, Exception>
    suspend fun updateUserProfile(
        name: String?,
        email: String?,
        phone: String?,
        password: String?
    ): Result<UpdateUserProfileResponse, Exception>

    suspend fun deleteUserAccount(): Result<DeleteUserResponse, Exception>
}