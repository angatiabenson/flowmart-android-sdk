package ke.co.banit.flowmartsdk.navigation

/**
 * @Author: Angatia Benson
 * @Date: 10/31/2024
 * Copyright (c) 2024 BanIT
 */
sealed class Screen(val route: String) {
    data object Main : Screen("main")
    data object Category : Screen("category")
    data object Product : Screen("product")
    data object User : Screen("user")

    data object CreateCategory : Screen("category/create_category")
    data object FetchCategory : Screen("category/fetch_category")
    data object UpdateCategory : Screen("category/update_category")
    data object ProductByCategory : Screen("category/product_category/{categoryId}") {
        fun createRoute(categoryId: Int) = "category/product_category/$categoryId"
    }


    data object CreateProduct : Screen("product/create_product")
    data object FetchProduct : Screen("product/fetch_product")
    data object UpdateProduct : Screen("product/update_product")
    data object Registration : Screen("user/registration")
    data object Login : Screen("user/login")
    data object UpdateUser : Screen("user/update_user")
}