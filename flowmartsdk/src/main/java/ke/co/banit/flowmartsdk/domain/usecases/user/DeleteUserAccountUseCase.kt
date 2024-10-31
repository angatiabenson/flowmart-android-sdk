package ke.co.banit.flowmartsdk.domain.usecases.user

import ke.co.banit.flowmartsdk.data.models.response.user.DeleteUserResponse
import ke.co.banit.flowmartsdk.domain.repositories.UserRepository
import ke.co.banit.flowmartsdk.util.Result
import ke.co.banit.flowmartsdk.util.handleResult
import ke.co.banit.flowmartsdk.util.runCatchingResult

/**
 * @Author: Angatia Benson
 * @Date: 10/29/2024
 * Copyright (c) 2024 BanIT
 */

/**
 * Use case for deleting the user account.
 * @property repository An instance of UserRepository to handle account deletion.
 */
internal class DeleteUserAccountUseCase(private val repository: UserRepository) {
    /**
     * Executes the use case to delete the user's account.
     * @return Result<Unit, Exception> - A result wrapping a success unit or an exception on failure.
     */
    suspend operator fun invoke(): Result<DeleteUserResponse, Exception> {
        return runCatchingResult {
            repository.deleteUserAccount()
        }.handleResult()
    }
}