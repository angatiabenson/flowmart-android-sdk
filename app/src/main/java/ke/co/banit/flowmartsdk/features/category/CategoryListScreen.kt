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
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.unit.dp
import ke.co.banit.flowmartsdk.data.models.dto.Category
import ke.co.banit.flowmartsdk.features.components.ErrorView

/**
 * @Author: Angatia Benson
 * @Date: 11/1/2024
 * Copyright (c) 2024 BanIT
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryListScreen(viewModel: CategoryViewModel) {
    LaunchedEffect(Unit) {
        viewModel.fetchCategories()
    }
    DisposableEffect(Unit) {
        onDispose {
            viewModel.resetUiState()
        }
    }
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(topBar = {
        TopAppBar(title = {
            Text(
                "Create Product Category",
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold)
            )
        })
    }, content = { paddingValues ->
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
            if (uiState.categories.isNotEmpty()) {
                DisplayCategoryList(
                    modifier = Modifier.padding(paddingValues), categories = uiState.categories
                )
            }
            uiState.errorMessage?.let { errorMessage ->
                ErrorView(modifier = Modifier.padding(paddingValues),
                    text = errorMessage,
                    onRetry = { viewModel.fetchCategories() })
            }
        }
    })
}

@Composable
fun DisplayCategoryList(modifier: Modifier = Modifier, categories: List<Category>) {
    if (categories.isEmpty()) {
        // Show a loading indicator or "no categories" message if the list is empty
        Box(
            modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center
        ) {
            Text("No categories available", style = MaterialTheme.typography.labelMedium)
        }
    } else {
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            items(categories) { category ->
                CategoryListItem(category = category,
                    onDeleteCategory = {},
                    onEditCategory = {},
                    onViewProductsInCategory = {})
                HorizontalDivider()
            }
        }
    }
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
                    imageVector = Icons.Default.Visibility, contentDescription = "View Products"
                )
            }
            IconButton(onClick = { onEditCategory(category) }) {
                Icon(
                    imageVector = Icons.Default.Edit, contentDescription = "Edit Category"
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
