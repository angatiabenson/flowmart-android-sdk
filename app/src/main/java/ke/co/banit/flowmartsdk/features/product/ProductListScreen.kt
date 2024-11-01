package ke.co.banit.flowmartsdk.features.product

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

/**
 * @Author: Angatia Benson
 * @Date: 11/1/2024
 * Copyright (c) 2024 BanIT
 */

data class Product(val id: Int, val name: String, val quantity: Int, val categoryName: String)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductListScreen() {
    val products = listOf(
        Product(id = 1, name = "Laptop", quantity = 5, categoryName = "Electronics"),
        Product(id = 2, name = "Novel", quantity = 10, categoryName = "Books"),
        Product(id = 3, name = "T-Shirt", quantity = 20, categoryName = "Fashion")
    )
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Products",
                        style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold)
                    )
                },
            )
        },
        content = { paddingValues ->
            if (products.isEmpty()) {
                // Show a message if no products are available
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    contentAlignment = Alignment.Center
                ) {
                    Text("No products available", style = MaterialTheme.typography.labelLarge)
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .padding(16.dp)
                ) {
                    items(products) { product ->
                        ProductListItem(
                            product = product,
                            onEditProduct = {
                                // Handle edit product action
                            },
                            onDeleteCategory = {
                                // Handle delete product action
                            }
                        )
                        HorizontalDivider()
                    }
                }
            }
        }
    )
}

@Composable
fun ProductListItem(
    product: Product,
    onEditProduct: (Product) -> Unit,
    onDeleteCategory: (Int) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = product.name,
                style = MaterialTheme.typography.labelLarge
            )
            Text(
                text = "Category: ${product.categoryName}",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
            )
            Text(
                text = "qty: ${product.quantity}",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
            )
        }
        Row {
            IconButton(onClick = { onEditProduct(product) }) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit Category"
                )
            }
            IconButton(onClick = { onDeleteCategory(product.id) }) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete Category",
                    tint = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}
