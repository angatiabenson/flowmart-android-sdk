package ke.co.banit.flowmartsdk.data.models.response.user

import com.squareup.moshi.Json
import ke.co.banit.flowmartsdk.data.models.dto.User

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
 * Represents the response received after a user login attempt in the FlowMart API.
 *
 * @property apiKey The API key provided upon successful login, used for authenticated requests.
 * @property message A message from the server indicating the success or status of the login operation.
 * @property user The [User] object containing details of the logged-in user.
 */
data class LoginUserResponse(
    @Json(name = "api_key") val apiKey: String,
    val message: String,
    val user: User
)
