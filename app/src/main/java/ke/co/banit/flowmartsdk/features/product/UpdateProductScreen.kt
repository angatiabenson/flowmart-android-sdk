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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import ke.co.banit.flowmartsdk.data.models.dto.Category

/**
 * @Author: Angatia Benson
 * @Date: 11/1/2024
 * Copyright (c) 2024 BanIT
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateProductScreen(
) {
    val categories = listOf(
        Category(id = 1, name = "Electronics"),
        Category(id = 2, name = "Books"),
        Category(id = 3, name = "Home Appliances"),
        Category(id = 4, name = "Fashion"),
        Category(id = 5, name = "Toys"),
    )
    var productName by remember { mutableStateOf("") }
    var productQuantity by remember { mutableStateOf("") }
    var selectedCategoryId by remember { mutableStateOf<Int?>(null) }
    var isDropdownExpanded by remember { mutableStateOf(false) }

    val isSubmitEnabled =
        productName.isNotBlank() && productQuantity.isNotBlank() && selectedCategoryId != null

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Update Product",
                        style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold)
                    )
                },
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                // Product Name Field
                OutlinedTextField(
                    value = productName,
                    onValueChange = { productName = it },
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
                    onValueChange = { productQuantity = it },
                    textStyle = MaterialTheme.typography.bodySmall,
                    label = { Text("Quantity", style = MaterialTheme.typography.bodySmall) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    singleLine = true,
                )

                // Category Dropdown
                ExposedDropdownMenuBox(
                    expanded = isDropdownExpanded,
                    onExpandedChange = { isDropdownExpanded = !isDropdownExpanded }
                ) {
                    OutlinedTextField(
                        value = selectedCategoryId?.let { id -> categories.find { it.id == id }?.name }
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
                    ExposedDropdownMenu(
                        expanded = isDropdownExpanded,
                        onDismissRequest = { isDropdownExpanded = false }
                    ) {
                        categories.forEach { category ->
                            DropdownMenuItem(
                                text = { Text(text = category.name) },
                                onClick = {
                                    selectedCategoryId = category.id
                                    isDropdownExpanded = false
                                },
                            )
                        }
                    }
                }

                // Submit Button
                Button(
                    onClick = {
                        if (selectedCategoryId != null) {
//                            onSubmit(
//                                productName,
//                                productQuantity.toInt(),
//                                selectedCategoryId!!
//                            )
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
    )
}
