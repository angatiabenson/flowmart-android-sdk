package ke.co.banit.flowmartsdk.util

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
 * A generic sealed interface that represents a success or an error outcome.
 *
 * @param D The type of the successful result data.
 * @param E The type of the error exception.
 */
sealed interface Result<out D, out E : Exception> {

    /**
     * Represents a successful outcome containing data.
     *
     * @param D The type of the successful result data.
     * @property data The successful result data.
     */
    data class Success<out D>(val data: D) : Result<D, Nothing>

    /**
     * Represents an error outcome containing an exception.
     *
     * @param E The type of the error exception.
     * @property error The exception that caused the error.
     */
    data class Error<out E : Exception>(val error: E) : Result<Nothing, E>
}

/**
 * A typealias for a Result with no data, used for operations that don't return a value.
 *
 * @param E The type of the error exception.
 */
internal typealias EmptyResult<E> = Result<Unit, E>