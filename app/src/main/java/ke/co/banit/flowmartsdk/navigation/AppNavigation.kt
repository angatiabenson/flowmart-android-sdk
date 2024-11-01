package ke.co.banit.flowmartsdk.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ke.co.banit.flowmartsdk.features.FlowMartScreen
import ke.co.banit.flowmartsdk.features.category.CategoryListScreen
import ke.co.banit.flowmartsdk.features.category.CategoryScreen
import ke.co.banit.flowmartsdk.features.category.CategoryViewModel
import ke.co.banit.flowmartsdk.features.category.CreateCategoryScreen
import ke.co.banit.flowmartsdk.features.category.UpdateCategoryScreen
import ke.co.banit.flowmartsdk.features.product.CreateProductScreen
import ke.co.banit.flowmartsdk.features.product.ProductListScreen
import ke.co.banit.flowmartsdk.features.product.ProductScreen
import ke.co.banit.flowmartsdk.features.user.LoginScreen
import ke.co.banit.flowmartsdk.features.user.RegistrationScreen
import ke.co.banit.flowmartsdk.features.user.UpdateUserScreen
import ke.co.banit.flowmartsdk.features.user.UserScreen
import ke.co.banit.flowmartsdk.features.user.UserViewModel

/**
 * @Author: Angatia Benson
 * @Date: 9/27/2024
 * Copyright (c) 2024 BanIT
 */

@Composable
fun AppNavigation(categoryViewModel: CategoryViewModel, userViewModel: UserViewModel) {
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
                onCreateCategoryClicked = { navController.navigate(Screen.CreateCategory.route) },
                onFetchCategoryClicked = { navController.navigate(Screen.FetchCategory.route) }
            )
        }
        composable(Screen.CreateCategory.route) {
            CreateCategoryScreen(viewModel = categoryViewModel)
        }
        composable(Screen.FetchCategory.route) {
            CategoryListScreen(viewModel = categoryViewModel)
        }
        composable(Screen.UpdateCategory.route) {
            UpdateCategoryScreen()
        }

        composable(Screen.Product.route) {
            ProductScreen(
                onCreateProductClicked = { navController.navigate(Screen.CreateProduct.route) },
                onFetchProductClicked = { navController.navigate(Screen.FetchProduct.route) }
            )
        }
        composable(Screen.CreateProduct.route) {
            CreateProductScreen()
        }
        composable(Screen.FetchProduct.route) {
            ProductListScreen()
        }
        composable(Screen.User.route) {
            UserScreen(
                onRegisterClicked = { navController.navigate(Screen.Registration.route) },
                onLoginClicked = { navController.navigate(Screen.Login.route) },
                onFetchProfileClicked = { navController.navigate(Screen.UpdateUser.route) }
            )
        }

        composable(Screen.Registration.route) {
            RegistrationScreen(viewModel = userViewModel)
        }

        composable(Screen.Login.route) {
            LoginScreen(viewModel = userViewModel)
        }
        composable(Screen.UpdateUser.route) {
            UpdateUserScreen()
        }
    }
}