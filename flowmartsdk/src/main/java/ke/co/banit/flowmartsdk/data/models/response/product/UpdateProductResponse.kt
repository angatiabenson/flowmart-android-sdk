package ke.co.banit.flowmartsdk.data.models.response.product

import ke.co.banit.flowmartsdk.data.models.dto.Product

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
 * Represents the response received after updating a [Product] in the FlowMart API.
 *
 * @property message A message from the server indicating the success or status of the product update operation.
 * @property product The updated [Product] object, reflecting the latest changes made.
 */
data class UpdateProductResponse(
    val message: String,
    val product: Product
)
