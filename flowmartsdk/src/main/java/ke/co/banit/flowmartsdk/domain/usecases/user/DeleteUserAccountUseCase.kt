package ke.co.banit.flowmartsdk.domain.usecases.user

import ke.co.banit.flowmartsdk.data.models.response.BaseResponse
import ke.co.banit.flowmartsdk.domain.repositories.UserRepository
import ke.co.banit.flowmartsdk.util.Result
import ke.co.banit.flowmartsdk.util.handleResult
import ke.co.banit.flowmartsdk.util.runCatchingResult

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
 * Use case for deleting the user account in the FlowMart API.
 *
 * This use case encapsulates the logic for invoking the [UserRepository] to delete the user's account,
 * providing a consistent and reusable approach for account deletion.
 *
 * @property repository An instance of [UserRepository] used to handle account deletion.
 */
internal class DeleteUserAccountUseCase(private val repository: UserRepository) {

    /**
     * Executes the use case to delete the user's account.
     *
     * @return A [Result] containing either [BaseResponse] if successful, or an [Exception] on failure.
     */
    suspend operator fun invoke(): Result<BaseResponse, Exception> {
        return runCatchingResult {
            repository.deleteUserAccount()
        }.handleResult()
    }
}
