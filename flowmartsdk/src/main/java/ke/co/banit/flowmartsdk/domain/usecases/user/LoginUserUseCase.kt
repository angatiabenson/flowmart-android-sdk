package ke.co.banit.flowmartsdk.domain.usecases.user

import ke.co.banit.flowmartsdk.data.models.response.user.LoginUserResponse
import ke.co.banit.flowmartsdk.domain.repositories.UserRepository
import ke.co.banit.flowmartsdk.domain.util.isEmailValid
import ke.co.banit.flowmartsdk.util.Result
import ke.co.banit.flowmartsdk.util.handleResult
import ke.co.banit.flowmartsdk.util.runCatchingResult


/**
 * @Author: Angatia Benson
 * @Date: 10/29/2024
 * Copyright (c) 2024 BanIT
 */

/**
 * Use case for logging in a user.
 * @property repository An instance of UserRepository to handle user login.
 */
internal class LoginUserUseCase(private val repository: UserRepository) {
    /**
     * Executes the use case to log in a user.
     * @param email The email address of the user.
     * @param password The password of the user.
     * @return Result<String, Exception> - A result wrapping the API token or an exception on failure.
     */
    suspend operator fun invoke(
        email: String,
        password: String
    ): Result<LoginUserResponse, Exception> {
        if (!isEmailValid(email)) return Result.Error(Exception("Invalid email format"))
        if (password.isBlank()) return Result.Error(Exception("Password cannot be blank"))

        return runCatchingResult {
            repository.loginUser(email, password)
        }.handleResult()
    }
}