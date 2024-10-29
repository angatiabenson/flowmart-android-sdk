package ke.co.banit.flowmartsdk.domain.usecases.user

import ke.co.banit.flowmartsdk.domain.models.User
import ke.co.banit.flowmartsdk.domain.repositories.UserRepository
import ke.co.banit.flowmartsdk.domain.util.Result
import ke.co.banit.flowmartsdk.domain.util.isEmailValid
import ke.co.banit.flowmartsdk.domain.util.isPasswordStrong
import ke.co.banit.flowmartsdk.domain.util.isPhoneValid


/**
 * @Author: Angatia Benson
 * @Date: 10/29/2024
 * Copyright (c) 2024 BanIT
 */

/**
 * Use case for registering a new user.
 * @property repository An instance of UserRepository to handle user registration.
 */
class RegisterUserUseCase(private val repository: UserRepository) {
    /**
     * Executes the use case to register a new user.
     * @param name The name of the user.
     * @param email The email address of the user.
     * @param phone The phone number of the user.
     * @param password The password chosen by the user.
     * @return Result<User, Exception> - A result wrapping the created user or an exception on failure.
     */
    suspend operator fun invoke(
        name: String,
        email: String,
        phone: String,
        password: String
    ): Result<User, Exception> {
        if (name.isBlank()) return Result.Error(Exception("Name cannot be blank"))
        if (!isEmailValid(email)) return Result.Error(Exception("Invalid email format"))
        if (!isPhoneValid(phone)) return Result.Error(Exception("Invalid phone number format"))
        if (!isPasswordStrong(password)) return Result.Error(Exception("Password must be at least 8 characters, contain a number, an uppercase letter, and a special character"))

        return repository.registerUser(name, email, phone, password)
    }
}