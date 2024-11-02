package ke.co.banit.flowmartsdk.features.components

import android.util.Log
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
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ke.co.banit.flowmartsdk.data.models.dto.Product

/**
 * @Author: Angatia Benson
 * @Date: 11/2/2024
 * Copyright (c) 2024 BanIT
 */


@Composable
fun ProductList(modifier: Modifier = Modifier, products: List<Product>) {
    val result = if (products.isEmpty())
        "Empty"
    else "Not Empty"
    Log.d("ProductList", result)
    if (products.isEmpty()) {
        // Show a message if no products are available
        Box(
            modifier = modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("No products available", style = MaterialTheme.typography.labelLarge)
        }
    } else {
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
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
                text = "Category: ${product.category.name}",
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
