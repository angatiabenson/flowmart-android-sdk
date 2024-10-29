package ke.co.banit.flowmartsdk.domain.repositories

import ke.co.banit.flowmartsdk.domain.models.User
import ke.co.banit.flowmartsdk.domain.util.Result

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
    ): Result<User, Exception>

    suspend fun loginUser(
        email: String,
        password: String
    ): Result<String, Exception> // Returns API token

    suspend fun getUserProfile(): Result<User, Exception>
    suspend fun updateUserProfile(
        name: String?,
        email: String?,
        phone: String?,
        password: String?
    ): Result<User, Exception>

    suspend fun deleteUserAccount(): Result<Unit, Exception>
}