package ke.co.banit.flowmartsdk.features.category

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.Divider
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
data class Category(val id: Int, val name: String)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryListScreen(
) {
    val categories = listOf(
        Category(id = 1, name = "Electronics"),
        Category(id = 2, name = "Books"),
        Category(id = 3, name = "Home Appliances"),
        Category(id = 4, name = "Fashion"),
        Category(id = 5, name = "Toys"),
    )
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Create Product Category",
                        style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold)
                    )
                },

                )
        },
        content = { paddingValues ->
            if (categories.isEmpty()) {
                // Show a loading indicator or "no categories" message if the list is empty
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    contentAlignment = Alignment.Center
                ) {
                    Text("No categories available", style = MaterialTheme.typography.labelMedium)
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .padding(16.dp)
                ) {
                    items(categories) { category ->
                        CategoryListItem(
                            category = category,
                            onDeleteCategory = {},
                            onEditCategory = {},
                            onViewProductsInCategory = {}
                        )
                        HorizontalDivider()
                    }
                }
            }
        }
    )
}

@Composable
fun CategoryListItem(
    category: Category,
    onDeleteCategory: (Category) -> Unit,
    onEditCategory: (Category) -> Unit,
    onViewProductsInCategory: (Category) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = category.name,
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.weight(1f)
        )

        // Action buttons
        Row {
            IconButton(onClick = { onViewProductsInCategory(category) }) {
                Icon(
                    imageVector = Icons.Default.Visibility,
                    contentDescription = "View Products"
                )
            }
            IconButton(onClick = { onEditCategory(category) }) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit Category"
                )
            }
            IconButton(onClick = { onDeleteCategory(category) }) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete Category",
                    tint = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}
