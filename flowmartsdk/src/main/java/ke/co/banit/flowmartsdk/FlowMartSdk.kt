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
import ke.co.banit.flowmartsdk.util.FlowMartConfiguration
import ke.co.banit.flowmartsdk.util.Result
import ke.co.banit.flowmartsdk.util.Settings

/**
 * @Author: Angatia Benson
 * @Date: 10/29/2024
 * Copyright (c) 2024 BanIT
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
    suspend fun getCategories(): Result<CategoriesListResponse, Exception> {
        return getCategoriesUseCase.value.invoke()
    }

    suspend fun createCategory(name: String): Result<CreateCategoryResponse, Exception> {
        return createCategoryUseCase.value.invoke(name)
    }

    suspend fun updateCategory(id: Int, name: String): Result<UpdateCategoryResponse, Exception> {
        return updateCategoryUseCase.value.invoke(id, name)
    }

    suspend fun deleteCategory(id: Int): Result<DeleteCategoryResponse, Exception> {
        return deleteCategoryUseCase.value.invoke(id)
    }

    // Product Methods
    suspend fun getProducts(): Result<ProductsListResponse, Exception> {
        return getAllProductsUseCase.value.invoke()
    }

    suspend fun getProductsByCategory(categoryId: Int): Result<ProductsByCategoryResponse, Exception> {
        return getProductsByCategoryUseCase.value.invoke(categoryId)
    }

    suspend fun createProduct(
        categoryId: Int,
        name: String,
        quantity: String
    ): Result<CreateProductResponse, Exception> {
        return createProductUseCase.value.invoke(categoryId, name, quantity)
    }

    suspend fun updateProduct(
        productId: Int,
        categoryId: Int,
        name: String,
        quantity: String
    ): Result<UpdateProductResponse, Exception> {
        return updateProductUseCase.value.invoke(productId, categoryId, name, quantity)
    }

    suspend fun deleteProduct(id: Int): Result<DeleteProductResponse, Exception> {
        return deleteProductUseCase.value.invoke(id)
    }

    // User Methods
    suspend fun registerUser(
        name: String,
        email: String,
        phone: String,
        password: String
    ): Result<RegisterUserResponse, Exception> {
        return registerUserUseCase.value.invoke(name, email, phone, password)
    }

    suspend fun loginUser(email: String, password: String): Result<LoginUserResponse, Exception> {
        return loginUserUseCase.value.invoke(email, password)
    }

    suspend fun getUserProfile(): Result<UserProfileResponse, Exception> {
        return getUserProfileUseCase.value.invoke()
    }

    suspend fun updateUserProfile(
        name: String?,
        email: String?,
        phone: String?,
        password: String?
    ): Result<UpdateUserProfileResponse, Exception> {
        return updateUserProfileUseCase.value.invoke(name, email, phone, password)
    }

    suspend fun deleteUserAccount(): Result<DeleteUserResponse, Exception> {
        return deleteUserAccountUseCase.value.invoke()
    }


    class Builder {

        private var configuration: FlowMartConfiguration? = null
        private lateinit var interceptor: AuthInterceptor

        private fun setupInterceptor() {
            interceptor = AuthInterceptor(configuration?.apiKey!!)
        }

        private fun <T, R : Any> createRepository(
            serviceCreator: (String, AuthInterceptor) -> T,
            repositoryFactory: (T) -> R
        ): R {
            val baseUrl = configuration?.baseUrl ?: Settings.BASE_URL
            val service = serviceCreator(baseUrl, interceptor)
            return repositoryFactory(service)
        }

        fun configure(configuration: FlowMartConfiguration): Builder =
            apply {
                this.configuration = configuration
                setupInterceptor()
            }

        fun build(): FlowMartSdk {
            // Initialize Repositories
            val categoryRepository = createRepository(
                serviceCreator = ApiClient::getCategoryAPIService,
                repositoryFactory = ::CategoryRepositoryImpl
            )
            val productRepository = createRepository(
                serviceCreator = ApiClient::getProductAPIService,
                repositoryFactory = ::ProductRepositoryImpl
            )

            val userRepository = createRepository(
                serviceCreator = ApiClient::getUserAPIService,
                repositoryFactory = ::UserRepositoryImpl
            )

            // Initialize Use Cases
            val getCategoriesUseCase = GetCategoriesUseCase(categoryRepository)
            val createCategoryUseCase = CreateCategoryUseCase(categoryRepository)
            val updateCategoryUseCase = UpdateCategoryUseCase(categoryRepository)
            val deleteCategoryUseCase = DeleteCategoryUseCase(categoryRepository)

            val getAllProductsUseCase = GetAllProductsUseCase(productRepository)
            val getProductsByCategoryUseCase = GetProductsByCategoryUseCase(productRepository)
            val createProductUseCase = CreateProductUseCase(productRepository)
            val updateProductUseCase = UpdateProductUseCase(productRepository)
            val deleteProductUseCase = DeleteProductUseCase(productRepository)

            val registerUserUseCase = RegisterUserUseCase(userRepository)
            val loginUserUseCase = LoginUserUseCase(userRepository)
            val getUserProfileUseCase = GetUserProfileUseCase(userRepository)
            val updateUserProfileUseCase = UpdateUserProfileUseCase(userRepository)
            val deleteUserAccountUseCase = DeleteUserAccountUseCase(userRepository)

            val flowMartSdk = FlowMartSdk(
                getCategoriesUseCase = lazy { getCategoriesUseCase },
                createCategoryUseCase = lazy { createCategoryUseCase },
                updateCategoryUseCase = lazy { updateCategoryUseCase },
                deleteCategoryUseCase = lazy { deleteCategoryUseCase },
                getAllProductsUseCase = lazy { getAllProductsUseCase },
                getProductsByCategoryUseCase = lazy { getProductsByCategoryUseCase },
                createProductUseCase = lazy { createProductUseCase },
                updateProductUseCase = lazy { updateProductUseCase },
                deleteProductUseCase = lazy { deleteProductUseCase },
                registerUserUseCase = lazy { registerUserUseCase },
                loginUserUseCase = lazy { loginUserUseCase },
                getUserProfileUseCase = lazy { getUserProfileUseCase },
                updateUserProfileUseCase = lazy { updateUserProfileUseCase },
                deleteUserAccountUseCase = lazy { deleteUserAccountUseCase }
            )

            return flowMartSdk
        }
    }
}