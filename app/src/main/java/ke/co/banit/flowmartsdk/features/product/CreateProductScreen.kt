package ke.co.banit.flowmartsdk.features.product

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import ke.co.banit.flowmartsdk.data.models.dto.Category
import ke.co.banit.flowmartsdk.features.category.CategoryViewModel
import ke.co.banit.flowmartsdk.features.components.LoadingDialog
import ke.co.banit.flowmartsdk.features.components.MessageDialog

/**
 * @Author: Angatia Benson
 * @Date: 11/1/2024
 * Copyright (c) 2024 BanIT
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateProductScreen(
    viewModel: ProductViewModel,
    categoryViewModel: CategoryViewModel,
) {
    val categoryUiState by categoryViewModel.uiState.collectAsState()
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        categoryViewModel.fetchCategories()
    }

    var productName by remember { mutableStateOf("") }
    var productQuantity by remember { mutableStateOf("") }
    var selectedCategoryId by remember { mutableStateOf<Int?>(null) }
    var isDropdownExpanded by remember { mutableStateOf(false) }

    val isSubmitEnabled =
        productName.isNotBlank() && productQuantity.isNotBlank() && selectedCategoryId != null

    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text(
                    "Create Product",
                    style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold)
                )
            },
        )
    }, content = { paddingValues ->
        CreateProductForm(modifier = Modifier.padding(paddingValues),
            productName = productName,
            onProductNameChange = { productName = it },
            productQuantity = productQuantity,
            onProductQuantityChange = { productQuantity = it },
            selectedCategoryId = selectedCategoryId,
            onSelectedCategoryIdChange = { selectedCategoryId = it },
            isDropdownExpanded = isDropdownExpanded,
            onIsDropdownExpandedChange = { isDropdownExpanded = it },
            isSubmitEnabled = isSubmitEnabled,
            categories = categoryUiState.categories,
            onSubmit = { name, quantity, categoryId ->
                viewModel.createProduct(name, categoryId, quantity)
            })

        LoadingDialog(isLoading = uiState.isLoading)
        LoadingDialog(isLoading = categoryUiState.isLoading)

        uiState.successMessage?.let { successMessage ->
            MessageDialog(
                message = successMessage, onDismiss = {
                    viewModel.resetUiState()
                }, isError = false
            )
            productName = ""
            productQuantity = ""
            selectedCategoryId = null
        }

        uiState.errorMessage?.let { errorMessage ->
            MessageDialog(
                message = errorMessage, onDismiss = { viewModel.resetUiState() }, isError = true
            )
        }
    })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateProductForm(
    productName: String,
    onProductNameChange: (String) -> Unit,
    productQuantity: String,
    onProductQuantityChange: (String) -> Unit,
    selectedCategoryId: Int?,
    onSelectedCategoryIdChange: (Int?) -> Unit,
    isDropdownExpanded: Boolean,
    onIsDropdownExpandedChange: (Boolean) -> Unit,
    isSubmitEnabled: Boolean,
    categories: List<Category>,
    onSubmit: (String, String, Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Product Name Field
        OutlinedTextField(
            value = productName,
            onValueChange = onProductNameChange,
            label = { Text("Product Name", style = MaterialTheme.typography.bodySmall) },
            textStyle = MaterialTheme.typography.bodySmall,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            singleLine = true
        )

        // Quantity Field
        OutlinedTextField(
            value = productQuantity,
            onValueChange = onProductQuantityChange,
            textStyle = MaterialTheme.typography.bodySmall,
            label = { Text("Quantity", style = MaterialTheme.typography.bodySmall) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            singleLine = true,
        )

        // Category Dropdown
        ExposedDropdownMenuBox(
            expanded = isDropdownExpanded, onExpandedChange = onIsDropdownExpandedChange
        ) {
            OutlinedTextField(value = selectedCategoryId?.let { id -> categories.find { it.id == id }?.name }
                ?: "",
                onValueChange = {},
                textStyle = MaterialTheme.typography.bodySmall,
                readOnly = true,
                label = { Text("Category", style = MaterialTheme.typography.bodySmall) },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = isDropdownExpanded)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
                    .menuAnchor(
                        type = MenuAnchorType.PrimaryNotEditable
                    )
            )
            ExposedDropdownMenu(expanded = isDropdownExpanded,
                onDismissRequest = { onIsDropdownExpandedChange(false) }) {
                categories.forEach { category ->
                    DropdownMenuItem(
                        text = { Text(text = category.name) },
                        onClick = {
                            onSelectedCategoryIdChange(category.id)
                            onIsDropdownExpandedChange(false)
                        },
                    )
                }
            }
        }

        // Submit Button
        Button(
            onClick = {
                selectedCategoryId?.let { categoryId ->
                    onSubmit(productName, productQuantity, categoryId)
                }
            },
            enabled = isSubmitEnabled,
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(12.dp)
        ) {
            Text("Submit", style = MaterialTheme.typography.labelLarge)
        }
    }
}

