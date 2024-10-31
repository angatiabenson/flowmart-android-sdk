package ke.co.banit.flowmartsdk.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ke.co.banit.flowmartsdk.features.FlowMartScreen
import ke.co.banit.flowmartsdk.features.category.CategoryScreen
import ke.co.banit.flowmartsdk.features.product.ProductScreen
import ke.co.banit.flowmartsdk.features.user.UserScreen

/**
 * @Author: Angatia Benson
 * @Date: 9/27/2024
 * Copyright (c) 2024 BanIT
 */

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.Main.route) {
        composable(Screen.Main.route) {
            FlowMartScreen(
                onCategoryClicked = { navController.navigate(Screen.Category.route) },
                onProductClicked = { navController.navigate(Screen.Product.route) },
                onUserClicked = { navController.navigate(Screen.User.route) }
            )
        }
        composable(Screen.Category.route) {
            CategoryScreen(
//                onCreateCategoryClicked = { navController.navigate(Screen.CreateCategory.route) },
//                onFetchCategoryClicked = { navController.navigate(Screen.FetchCategory.route) }
            )
        }
        composable(Screen.Product.route) {
            ProductScreen()
        }
        composable(Screen.User.route) {
            UserScreen()
        }
    }
}