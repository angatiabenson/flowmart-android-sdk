package ke.co.banit.flowmartsdk

import ke.co.banit.flowmartsdk.data.models.response.category.CategoriesListResponse
import ke.co.banit.flowmartsdk.data.models.response.category.CreateCategoryResponse
import ke.co.banit.flowmartsdk.data.models.response.category.DeleteCategoryResponse
import ke.co.banit.flowmartsdk.data.models.response.category.UpdateCategoryResponse
import ke.co.banit.flowmartsdk.data.models.response.product.CreateProductResponse
import ke.co.banit.flowmartsdk.data.models.response.product.DeleteProductResponse
import ke.co.banit.flowmartsdk.data.models.response.product.ProductsByCategoryResponse
import ke.co.banit.flowmartsdk.data.models.response.product.ProductsListResponse
import ke.co.banit.flowmartsdk.data.models.response.product.UpdateProductResponse
import ke.co.banit.flowmartsdk.data.models.response.user.DeleteUserResponse
import ke.co.banit.flowmartsdk.data.models.response.user.LoginUserResponse
import ke.co.banit.flowmartsdk.data.models.response.user.RegisterUserResponse
import ke.co.banit.flowmartsdk.data.models.response.user.UpdateUserProfileResponse
import ke.co.banit.flowmartsdk.data.models.response.user.UserProfileResponse
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
import ke.co.banit.flowmartsdk.util.Result
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @Author: Angatia Benson
 * @Date: 10/29/2024
 * Copyright (c) 2024 BanIT
 */

/**
 * The core SDK class providing access to FlowMart API functionalities.
 *
 * @property getCategoriesUseCase The use case for retrieving categories.
 * @property createCategoryUseCase The use case for creating a new category.
 * @property updateCategoryUseCase The use case for updating an existing category.
 * @property deleteCategoryUseCase The use case for deleting a category.
 * @property getAllProductsUseCase The use case for retrieving products.
 * @property getProductsByCategoryUseCase The use case for retrieving products by category.
 * @property createProductUseCase The use case for creating a new product.
 * @property updateProductUseCase The use case for updating a product.
 * @property deleteProductUseCase The use case for deleting a product.
 * @property registerUserUseCase The use case for registering a new user.
 * @property loginUserUseCase The use case for logging in a user.
 * @property getUserProfileUseCase The use case for retrieving the user profile.
 * @property updateUserProfileUseCase The use case for updating the user profile.
 * @property deleteUserAccountUseCase The use case for deleting a user account.
 */
@Singleton
class FlowMartSdk @Inject constructor(
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val createCategoryUseCase: CreateCategoryUseCase,
    private val updateCategoryUseCase: UpdateCategoryUseCase,
    private val deleteCategoryUseCase: DeleteCategoryUseCase,
    private val getAllProductsUseCase: GetAllProductsUseCase,
    private val getProductsByCategoryUseCase: GetProductsByCategoryUseCase,
    private val createProductUseCase: CreateProductUseCase,
    private val updateProductUseCase: UpdateProductUseCase,
    private val deleteProductUseCase: DeleteProductUseCase,
    private val registerUserUseCase: RegisterUserUseCase,
    private val loginUserUseCase: LoginUserUseCase,
    private val getUserProfileUseCase: GetUserProfileUseCase,
    private val updateUserProfileUseCase: UpdateUserProfileUseCase,
    private val deleteUserAccountUseCase: DeleteUserAccountUseCase
) {

    /**
     * The authentication token (API key) used for authenticated API requests.
     * This should be set during SDK initialization.
     */
    @Volatile
    private var apiKey: String? = null

    /**
     * Initializes the SDK with the provided API key.
     *
     * @param apiKey The API key for authenticated requests.
     */
    fun initialize(apiKey: String) {
        this.apiKey = apiKey
    }

    /**
     * Sets the API key manually.
     *
     * Useful if the API key is obtained from an external source or needs to be updated.
     *
     * @param apiKey The API key to set.
     */
    fun setApiKey(apiKey: String) {
        this.apiKey = apiKey
    }

    /**
     * Clears the API key, e.g., on logout.
     */
    fun clearApiKey() {
        this.apiKey = null
    }

    /**
     * Retrieves the current API key.
     *
     * @return The API key if set, null otherwise.
     */
    fun getApiKey(): String? = apiKey

    // Category Methods
    suspend fun getCategories(): Result<CategoriesListResponse, Exception> {
        return getCategoriesUseCase()
    }

    suspend fun createCategory(name: String): Result<CreateCategoryResponse, Exception> {
        return createCategoryUseCase(name)
    }

    suspend fun updateCategory(id: Int, name: String): Result<UpdateCategoryResponse, Exception> {
        return updateCategoryUseCase(id, name)
    }

    suspend fun deleteCategory(id: Int): Result<DeleteCategoryResponse, Exception> {
        return deleteCategoryUseCase(id)
    }

    // Product Methods
    suspend fun getProducts(): Result<ProductsListResponse, Exception> {
        return getAllProductsUseCase()

    }

    suspend fun getProductsByCategory(categoryId: Int): Result<ProductsByCategoryResponse, Exception> {
        return getProductsByCategoryUseCase(categoryId)
    }

    suspend fun createProduct(
        categoryId: Int,
        name: String,
        quantity: Int
    ): Result<CreateProductResponse, Exception> {
        return createProductUseCase(categoryId, name, quantity)
    }

    suspend fun updateProduct(
        id: Int,
        name: String,
        quantity: Int
    ): Result<UpdateProductResponse, Exception> {
        return updateProductUseCase(id, name, quantity)
    }

    suspend fun deleteProduct(id: Int): Result<DeleteProductResponse, Exception> {
        return deleteProductUseCase(id)
    }

    // User Methods
    suspend fun registerUser(
        name: String,
        email: String,
        phone: String,
        password: String
    ): Result<RegisterUserResponse, Exception> {
        return registerUserUseCase(name, email, phone, password)
    }

    /**
     * Logs in a user with the provided credentials.
     *
     * On successful login, the [apiKey] is set for authenticated requests.
     *
     * @param email The user's email.
     * @param password The user's password.
     * @return A [Result] containing the API key on success or an exception on failure.
     */
    suspend fun loginUser(email: String, password: String): Result<LoginUserResponse, Exception> {
        return loginUserUseCase(email, password)
    }

    suspend fun getUserProfile(): Result<UserProfileResponse, Exception> {
        return getUserProfileUseCase()
    }

    suspend fun updateUserProfile(
        name: String?,
        email: String?,
        phone: String?,
        password: String?
    ): Result<UpdateUserProfileResponse, Exception> {
        return updateUserProfileUseCase(name, email, phone, password)
    }

    suspend fun deleteUserAccount(): Result<DeleteUserResponse, Exception> {
        return deleteUserAccountUseCase()
    }
}