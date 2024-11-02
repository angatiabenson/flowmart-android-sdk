package ke.co.banit.flowmartsdk.data.models.dto

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
 * Represents a product within the FlowMart inventory management platform.
 *
 * @property category The category to which the product belongs, aiding in product organization.
 * @property id Unique identifier for the product, used for product-related API operations.
 * @property name The name of the product, providing a clear identifier within the inventory.
 * @property quantity The available quantity of the product, stored as a string to support units or formats (e.g., "10 pcs").
 */
data class Product(
    val category: Category,
    val id: Int,
    val name: String,
    val quantity: String
)


