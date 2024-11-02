package ke.co.banit.flowmartsdk.features.category

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import ke.co.banit.flowmartsdk.features.components.ErrorView
import ke.co.banit.flowmartsdk.features.components.ProductList

/**
 * @Author: Angatia Benson
 * @Date: 11/2/2024
 * Copyright (c) 2024 BanIT
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductByCategoryScreen(
    viewModel: CategoryViewModel,
    productId: Int?
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchProductsByCategory(productId ?: 0)
    }
    DisposableEffect(Unit) {
        onDispose {
            viewModel.resetUiState()
        }
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Products By Category",
                        style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold)
                    )
                },
            )
        },
        content = { paddingValues ->
            if (uiState.isLoading) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Loading...")
                }
            } else {
                ProductList(
                    modifier = Modifier.padding(paddingValues), products = uiState.products
                )

                uiState.errorMessage?.let { errorMessage ->
                    ErrorView(modifier = Modifier.padding(paddingValues),
                        text = errorMessage,
                        onRetry = { viewModel.fetchProductsByCategory(productId ?: 0) })
                }
            }
        }
    )
}