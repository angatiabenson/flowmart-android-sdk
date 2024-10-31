package ke.co.banit.flowmartsdk.domain.usecases.user

import ke.co.banit.flowmartsdk.data.models.response.user.UpdateUserProfileResponse
import ke.co.banit.flowmartsdk.domain.repositories.UserRepository
import ke.co.banit.flowmartsdk.domain.util.isEmailValid
import ke.co.banit.flowmartsdk.domain.util.isPasswordStrong
import ke.co.banit.flowmartsdk.domain.util.isPhoneValid
import ke.co.banit.flowmartsdk.util.Result
import ke.co.banit.flowmartsdk.util.handleResult
import ke.co.banit.flowmartsdk.util.runCatchingResult


/**
 * @Author: Angatia Benson
 * @Date: 10/29/2024
 * Copyright (c) 2024 BanIT
 */

/**
 * Use case for updating the user's profile.
 * @property repository An instance of UserRepository to handle profile updates.
 */
internal class UpdateUserProfileUseCase(private val repository: UserRepository) {
    /**
     * Executes the use case to update the user's profile.
     * @param name The updated name of the user (nullable).
     * @param email The updated email of the user (nullable).
     * @param phone The updated phone number of the user (nullable).
     * @param password The updated password of the user (nullable).
     * @return Result<User, Exception> - A result wrapping the updated user profile or an exception on failure.
     */
    suspend operator fun invoke(
        name: String?,
        email: String?,
        phone: String?,
        password: String?
    ): Result<UpdateUserProfileResponse, Exception> {
        if (name != null && name.isBlank()) return Result.Error(Exception("Name cannot be blank"))
        if (email != null && !isEmailValid(email)) return Result.Error(Exception("Invalid email format"))
        if (phone != null && !isPhoneValid(phone)) return Result.Error(Exception("Invalid phone number format"))
        if (password != null && !isPasswordStrong(password)) return Result.Error(Exception("Password must be at least 8 characters, contain a number, an uppercase letter, and a special character"))

        return runCatchingResult {
            repository.updateUserProfile(name, email, phone, password)
        }.handleResult()
    }
}