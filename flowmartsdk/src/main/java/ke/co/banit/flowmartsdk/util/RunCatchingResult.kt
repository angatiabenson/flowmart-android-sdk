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
 * Executes the given [block] and returns a [Result].
 *
 * @param T The type of the successful result.
 * @param E The type of the error exception.
 * @param block The block of code to execute.
 * @return A [Result.Success] if the block executes successfully, or [Result.Error] if an exception is thrown.
 */
internal inline fun <T> runCatchingResult(block: () -> Result<T, Exception>): Result<T, Exception> {
    return try {
        block()
    } catch (e: Exception) {
        Result.Error(e)
    }
}
