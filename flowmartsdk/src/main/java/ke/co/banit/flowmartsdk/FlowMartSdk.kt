package ke.co.banit.flowmartsdk

import ke.co.banit.flowmartsdk.data.models.response.BaseResponse
import ke.co.banit.flowmartsdk.data.models.response.category.CategoriesListResponse
import ke.co.banit.flowmartsdk.data.models.response.category.UpdateCategoryResponse
import ke.co.banit.flowmartsdk.data.models.response.product.ProductsListResponse
import ke.co.banit.flowmartsdk.data.models.response.product.UpdateProductResponse
import ke.co.banit.flowmartsdk.data.models.response.user.LoginUserResponse
import ke.co.banit.flowmartsdk.data.models.response.user.UserResponse
import ke.co.banit.flowmartsdk.data.remote.ApiClient
import ke.co.banit.flowmartsdk.data.remote.okhttp.AuthInterceptor
import ke.co.banit.flowmartsdk.data.repositories.CategoryRepositoryImpl
import ke.co.banit.flowmartsdk.data.repositories.ProductRepositoryImpl
import ke.co.banit.flowmartsdk.data.repositories.UserRepositoryImpl
import ke.co.banit.flowmartsdk.domain.usecases.category.CreateCategoryUseCase
import ke.co.banit.flowmartsdk.domain.usecases.category.DeleteCategoryUseCase
import ke.co.banit.flowmartsdk.domain.usecases.category.GetCategoriesUseCase
import ke.co.banit.flowmartsdk.domain.usecases.category.UpdateCategoryUseCase
import ke.co.banit.flowmartsdk.domain.usecases.product.CreateProductUseCase
import ke.co.banit.flowmartsdk.domain.usecases.product.DeleteProductUseCase
import ke.co.banit.flowmartsdk.domain.usecases.product.GetAllProductsUseCase
import ke.co.banit.flowmartsdk.domain.usecases.product.GetProductsByCategoryUseCase
import ke.co.banit.flowmartsdk.domain.usecases.product.UpdateProductUseCase
import ke.co.banit.flowmartsdk.domain.usecases.user.DeleteUserAccountUseCase
import ke.co.banit.flowmartsdk.domain.usecases.user.GetUserProfileUseCase
import ke.co.banit.flowmartsdk.domain.usecases.user.LoginUserUseCase
import ke.co.banit.flowmartsdk.domain.usecases.user.RegisterUserUseCase
import ke.co.banit.flowmartsdk.domain.usecases.user.UpdateUserProfileUseCase
import ke.co.banit.flowmartsdk.util.Environment
import ke.co.banit.flowmartsdk.util.FlowMartConfiguration
import ke.co.banit.flowmartsdk.util.Result
import ke.co.banit.flowmartsdk.util.Settings

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
 * SDK for interacting with the FlowMart API, providing comprehensive functionality for managing categories, products, and user accounts.
 *
 * The `FlowMartSdk` class encapsulates the necessary use cases to interact with the FlowMart API, allowing users to perform operations
 * such as retrieving, creating, updating, and deleting categories and products, as well as managing user registration, login, profile updates,
 * and account deletion. The SDK is designed to be instantiated via the `Builder` class, ensuring that all required configurations are provided.
 *
 *
 * ### Example Usage:
 * ```
 * val sdk = FlowMartSdk.Builder()
 *     .configure(
 *              configuration=FlowMartConfiguration(apiKey = "your_api_key"),
 *              environment=Environment.DEVELOPMENT)
 *     .build()
 * ```
 *
 */
class FlowMartSdk private constructor(
    private val getCategoriesUseCase: Lazy<GetCategoriesUseCase>,
    private val createCategoryUseCase: Lazy<CreateCategoryUseCase>,
    private val updateCategoryUseCase: Lazy<UpdateCategoryUseCase>,
    private val deleteCategoryUseCase: Lazy<DeleteCategoryUseCase>,
    private val getAllProductsUseCase: Lazy<GetAllProductsUseCase>,
    private val getProductsByCategoryUseCase: Lazy<GetProductsByCategoryUseCase>,
    private val createProductUseCase: Lazy<CreateProductUseCase>,
    private val updateProductUseCase: Lazy<UpdateProductUseCase>,
    private val deleteProductUseCase: Lazy<DeleteProductUseCase>,
    private val registerUserUseCase: Lazy<RegisterUserUseCase>,
    private val loginUserUseCase: Lazy<LoginUserUseCase>,
    private val getUserProfileUseCase: Lazy<GetUserProfileUseCase>,
    private val updateUserProfileUseCase: Lazy<UpdateUserProfileUseCase>,
    private val deleteUserAccountUseCase: Lazy<DeleteUserAccountUseCase>
) {

    // Category Methods

    /**
     * Retrieves all categories.
     *
     * @return `Result<CategoriesListResponse, Exception>` A result wrapping the categories list or an exception on failure.
     */
    suspend fun getCategories(): Result<CategoriesListResponse, Exception> =
        getCategoriesUseCase.value.invoke()

    /**
     * Creates a new category with the specified name.
     * @param categoryName The name of the new category to create.
     * @return `Result<BaseResponse, Exception>` A result wrapping the response or an exception on failure.
     */
    suspend fun createCategory(categoryName: String): Result<BaseResponse, Exception> =
        createCategoryUseCase.value.invoke(categoryName)

    /**
     * Updates a category with the specified ID and name.
     * @param categoryId The unique identifier of the category to update.
     * @param categoryName The updated name for the category.
     * @return `Result<UpdateCategoryResponse, Exception>` A result wrapping the updated category response or an exception on failure.
     */
    suspend fun updateCategory(
        categoryId: Int,
        categoryName: String
    ): Result<UpdateCategoryResponse, Exception> =
        updateCategoryUseCase.value.invoke(categoryId, categoryName)

    /**
     * Deletes a category by its ID.
     *
     * @param categoryId The unique identifier of the category to delete.
     * @return `Result<BaseResponse, Exception>` A result wrapping the response or an exception on failure.
     */
    suspend fun deleteCategory(categoryId: Int): Result<BaseResponse, Exception> =
        deleteCategoryUseCase.value.invoke(categoryId)

    // Product Methods

    /**
     * Retrieves all products.
     *
     * @return `Result<ProductsListResponse, Exception>` A result wrapping the products list or an exception on failure.
     */
    suspend fun getProducts(): Result<ProductsListResponse, Exception> =
        getAllProductsUseCase.value.invoke()

    /**
     * Retrieves products by the specified category ID.
     *
     * @param categoryId The unique identifier of the category whose products are to be retrieved.
     * @return `Result<ProductsListResponse, Exception>` A result wrapping the products list or an exception on failure.
     */
    suspend fun getProductsByCategory(categoryId: Int): Result<ProductsListResponse, Exception> =
        getProductsByCategoryUseCase.value.invoke(categoryId)

    /**
     * Creates a new product with specified category ID, name, and quantity.
     *
     * @param categoryId The ID of the category where the product will be created.
     * @param productName The name of the product to be created.
     * @param quantity The quantity of the product.
     * @return `Result<BaseResponse, Exception>` A result wrapping the response or an exception on failure.
     */
    suspend fun createProduct(
        categoryId: Int,
        productName: String,
        quantity: String
    ): Result<BaseResponse, Exception> =
        createProductUseCase.value.invoke(categoryId, productName, quantity)

    /**
     * Updates an existing product with specified product ID, category ID, name, and quantity.
     *
     * @param productId The unique identifier of the product to update.
     * @param categoryId The category ID to which the product belongs.
     * @param productName The updated name for the product.
     * @param quantity The updated quantity for the product.
     * @return `Result<UpdateProductResponse, Exception>` A result wrapping the updated product response or an exception on failure.
     */
    suspend fun updateProduct(
        productId: Int,
        categoryId: Int,
        productName: String,
        quantity: String
    ): Result<UpdateProductResponse, Exception> =
        updateProductUseCase.value.invoke(productId, categoryId, productName, quantity)

    /**
     * Deletes a product by its ID.
     *
     * @param productId The unique identifier of the product to delete.
     * @return `Result<BaseResponse, Exception>` A result wrapping the response or an exception on failure.
     */
    suspend fun deleteProduct(productId: Int): Result<BaseResponse, Exception> =
        deleteProductUseCase.value.invoke(productId)

    // User Methods

    /**
     * Registers a new user with the specified details.
     *
     * @param name The full name of the user.
     * @param email The email address of the user.
     * @param phone The phone number of the user.
     * @param password The password chosen by the user.
     * @return `Result<UserResponse, Exception>` A result wrapping the user response or an exception on failure.
     */
    suspend fun registerUser(
        name: String,
        email: String,
        phone: String,
        password: String
    ): Result<UserResponse, Exception> =
        registerUserUseCase.value.invoke(name, email, phone, password)

    /**
     * Logs in a user with the specified email and password.
     *
     * @param email The email address of the user.
     * @param password The password for the user's account.
     * @return `Result<LoginUserResponse, Exception>` A result wrapping the login response or an exception on failure.
     */
    suspend fun loginUser(email: String, password: String): Result<LoginUserResponse, Exception> =
        loginUserUseCase.value.invoke(email, password)

    /**
     * Retrieves the current user profile.
     *
     * @return `Result<UserResponse, Exception>` A result wrapping the user profile or an exception on failure.
     */
    suspend fun getUserProfile(): Result<UserResponse, Exception> =
        getUserProfileUseCase.value.invoke()

    /**
     * Updates the current user profile with specified details.
     *
     * @param name The updated name of the user.
     * @param email The updated email address of the user.
     * @param phone The updated phone number of the user.
     * @param password The updated password for the user’s account.
     * @return `Result<UserResponse, Exception>` A result wrapping the updated profile or an exception on failure.
     */
    suspend fun updateUserProfile(
        name: String?,
        email: String?,
        phone: String?,
        password: String?
    ): Result<UserResponse, Exception> =
        updateUserProfileUseCase.value.invoke(name, email, phone, password)

    /**
     * Deletes the current user account.
     *
     * @return `Result<BaseResponse, Exception>` A result wrapping the response or an exception on failure.
     */
    suspend fun deleteUserAccount(): Result<BaseResponse, Exception> =
        deleteUserAccountUseCase.value.invoke()

    /**
     * Builder class for constructing an instance of [FlowMartSdk].
     *
     * The `Builder` class facilitates the setup and configuration of the SDK instance. It allows
     * setting up essential configurations such as `FlowMartConfiguration` and `Environment`, and builds
     * the SDK with appropriate repositories and use cases.
     *
     * ### Example Usage:
     * ```
     * val sdk = FlowMartSdk.Builder()
     *     .configure(
     *              configuration=FlowMartConfiguration(apiKey = "your_api_key"),
     *              environment=Environment.DEVELOPMENT)
     *     .build()
     * ```
     */
    class Builder {
        private var configuration: FlowMartConfiguration? = null
        private lateinit var interceptor: AuthInterceptor
        private var environment: Environment = Environment.PRODUCTION

        /**
         * Sets up the `AuthInterceptor` with the provided API key from the configuration.
         *
         * This method is called internally by the `configure` function to ensure that the interceptor is properly set up before
         * making API calls.
         */
        private fun setupInterceptor() {
            interceptor = AuthInterceptor(configuration?.apiKey ?: "")
        }

        /**
         * Creates a repository instance for a specific API service.
         *
         * This method is used to create repositories for categories, products, and users, each of which requires a specific API service.
         *
         * @param serviceCreator A function that creates the API service instance, given the `baseUrl`, `AuthInterceptor`, and `Environment`.
         * @param repositoryFactory A function that creates the repository, given the API service instance.
         * @return An instance of the repository created with the specified service.
         */
        private fun <T, R : Any> createRepository(
            serviceCreator: (String, AuthInterceptor, Environment) -> T,
            repositoryFactory: (T) -> R
        ): R {
            val baseUrl = configuration?.baseUrl ?: Settings.BASE_URL
            val service = serviceCreator(baseUrl, interceptor, environment)
            return repositoryFactory(service)
        }

        /**
         * Configures the SDK with the specified `FlowMartConfiguration` and `Environment`.
         *
         * This function is responsible for setting up essential SDK configurations, including setting the environment and
         * API key for authentication. It also initializes the `AuthInterceptor` for handling authenticated requests.
         * The `configure` method is chainable, allowing for fluent setup of the SDK.
         *
         * Example usage:
         * ```
         * val sdk = FlowMartSdk.Builder()
         *     .configure(
         *              configuration=FlowMartConfiguration(apiKey = "your_api_key"),
         *              environment=Environment.DEVELOPMENT)
         *     .build()
         * ```
         *
         * @param configuration An instance of `FlowMartConfiguration`, containing the API key and base URL for the SDK.
         * @param environment The `Environment` in which the SDK will operate (default is `Environment.PRODUCTION`).
         * @return The current `Builder` instance for chaining.
         */
        fun configure(
            configuration: FlowMartConfiguration,
            environment: Environment = Environment.PRODUCTION
        ): Builder = apply {
            this.environment = environment
            this.configuration = configuration
            setupInterceptor()
        }


        /**
         * Builds and returns an instance of [FlowMartSdk] with the specified configuration.
         *
         * @return A configured instance of [FlowMartSdk].
         */
        fun build(): FlowMartSdk {
            // Repository and use case setup logic
            val categoryRepository =
                createRepository(ApiClient::getCategoryAPIService, ::CategoryRepositoryImpl)
            val productRepository =
                createRepository(ApiClient::getProductAPIService, ::ProductRepositoryImpl)
            val userRepository =
                createRepository(ApiClient::getUserAPIService, ::UserRepositoryImpl)

            return FlowMartSdk(
                getCategoriesUseCase = lazy { GetCategoriesUseCase(categoryRepository) },
                createCategoryUseCase = lazy { CreateCategoryUseCase(categoryRepository) },
                updateCategoryUseCase = lazy { UpdateCategoryUseCase(categoryRepository) },
                deleteCategoryUseCase = lazy { DeleteCategoryUseCase(categoryRepository) },
                getAllProductsUseCase = lazy { GetAllProductsUseCase(productRepository) },
                getProductsByCategoryUseCase = lazy { GetProductsByCategoryUseCase(productRepository) },
                createProductUseCase = lazy { CreateProductUseCase(productRepository) },
                updateProductUseCase = lazy { UpdateProductUseCase(productRepository) },
                deleteProductUseCase = lazy { DeleteProductUseCase(productRepository) },
                registerUserUseCase = lazy { RegisterUserUseCase(userRepository) },
                loginUserUseCase = lazy { LoginUserUseCase(userRepository) },
                getUserProfileUseCase = lazy { GetUserProfileUseCase(userRepository) },
                updateUserProfileUseCase = lazy { UpdateUserProfileUseCase(userRepository) },
                deleteUserAccountUseCase = lazy { DeleteUserAccountUseCase(userRepository) }
            )
        }
    }
}
