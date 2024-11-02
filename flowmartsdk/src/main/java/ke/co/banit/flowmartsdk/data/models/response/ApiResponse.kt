package ke.co.banit.flowmartsdk.data.models.response

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
 * Represents a generic API response structure within the FlowMart API SDK.
 *
 * @param T The type of data being returned in the response.
 * @property status The status of the response, typically indicating success or failure.
 * @property message An optional message from the server, providing additional information about the response.
 * @property code An optional integer code representing the HTTP status or specific API error code.
 * @property data The response payload of type [T], containing the main data if the request was successful.
 */
internal data class ApiResponse<T>(
    val status: String? = null,
    val message: String? = null,
    val code: Int? = null,
    val data: T? = null
)
