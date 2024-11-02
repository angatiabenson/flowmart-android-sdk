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
 * Represents a network-related exception.
 *
 * The `NetworkException` class is used to indicate issues that occur due to network failures,
 * such as connectivity problems or timeouts, allowing for consistent handling of network errors.
 *
 * @param message The error message providing additional details about the network issue.
 */
class NetworkException(message: String) : Exception(message)
