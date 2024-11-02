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
 * Represents the operational environment for the FlowMart API.
 *
 * The `Environment` enum is used to specify the current environment in which the application is running.
 * This can be helpful for setting up configurations, such as enabling logging in development or using
 * production-specific settings.
 */
enum class Environment {
    /** Development environment, typically used for testing and debugging. */
    DEVELOPMENT,

    /** Production environment, used for live deployment. */
    PRODUCTION
}
