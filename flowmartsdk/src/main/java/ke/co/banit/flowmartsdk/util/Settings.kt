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
 * Configuration settings for network operations within the FlowMart API.
 *
 * The `Settings` object contains constants for connection timeouts and the base URL, used across the API
 * to standardize network-related configurations.
 */
internal object Settings {

    /**
     * The duration in milliseconds for connection timeout.
     */
    const val CONNECT_TIMEOUT: Long = 60 * 1000

    /**
     * The duration in milliseconds for read timeout.
     */
    const val READ_TIMEOUT: Long = 60 * 1000

    /**
     * The duration in milliseconds for write timeout.
     */
    const val WRITE_TIMEOUT: Long = 60 * 1000

    /**
     * The base URL for the FlowMart API.
     */
    const val BASE_URL = "https://flowmart.banit.co.ke/"
}
