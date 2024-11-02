package ke.co.banit.flowmartsdk.exceptions

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
 * Represents an API-related exception, encapsulating an error code and message.
 *
 * The `ApiException` class is used to indicate errors that occur during API calls, allowing
 * for consistent error handling based on HTTP response codes.
 *
 * @property code The HTTP status code associated with the exception.
 * @param message The error message providing additional details about the exception.
 */
class ApiException(val code: Int, message: String?) : Exception(message)
