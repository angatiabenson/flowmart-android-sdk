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
 * Configuration settings for the FlowMart API.
 *
 * The `FlowMartConfiguration` class holds the necessary settings to initialize and configure
 * API interactions, including the API key for authentication and the base URL for API requests.
 *
 * @property apiKey The API key required for authenticating requests. Can be null if authentication is not required.
 * @property baseUrl The base URL of the FlowMart API. Defaults to [Settings.BASE_URL] if not specified.
 */
data class FlowMartConfiguration(
    val apiKey: String?,
    val baseUrl: String? = Settings.BASE_URL
)
