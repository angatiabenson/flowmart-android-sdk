package ke.co.banit.flowmartsdk.domain.usecases.user

import ke.co.banit.flowmartsdk.domain.repositories.UserRepository
import ke.co.banit.flowmartsdk.domain.util.Result
import ke.co.banit.flowmartsdk.domain.util.isEmailValid


/**
 * @Author: Angatia Benson
 * @Date: 10/29/2024
 * Copyright (c) 2024 BanIT
 */

/**
 * Use case for logging in a user.
 * @property repository An instance of UserRepository to handle user login.
 */
class LoginUserUseCase(private val repository: UserRepository) {
    /**
     * Executes the use case to log in a user.
     * @param email The email address of the user.
     * @param password The password of the user.
     * @return Result<String, Exception> - A result wrapping the API token or an exception on failure.
     */
    suspend operator fun invoke(email: String, password: String): Result<String, Exception> {
        if (!isEmailValid(email)) return Result.Error(Exception("Invalid email format"))
        if (password.isBlank()) return Result.Error(Exception("Password cannot be blank"))

        return repository.loginUser(email, password)
    }
}