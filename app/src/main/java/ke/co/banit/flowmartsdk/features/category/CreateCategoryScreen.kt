package ke.co.banit.flowmartsdk.features.category

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import ke.co.banit.flowmartsdk.features.components.LoadingDialog
import ke.co.banit.flowmartsdk.features.components.MessageDialog

/**
 * @Author: Angatia Benson
 * @Date: 11/1/2024
 * Copyright (c) 2024 BanIT
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateCategoryScreen(viewModel: CategoryViewModel) {
    var categoryName by remember { mutableStateOf("") }
    val isSubmitEnabled = categoryName.isNotBlank()
    val uiState by viewModel.uiState.collectAsState()


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
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(
                    value = categoryName,
                    onValueChange = { categoryName = it },
                    label = { Text("Category Name", style = MaterialTheme.typography.labelSmall) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    singleLine = true
                )

                Button(
                    onClick = {
                        viewModel.createCategory(categoryName)
                    },
                    enabled = isSubmitEnabled,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    contentPadding = PaddingValues(12.dp)
                ) {
                    Text(
                        "Submit",
                        style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold)
                    )
                }
            }

            // Show Loading Dialog if loading
            LoadingDialog(isLoading = uiState.isLoading)

            uiState.successMessage?.let { successMessage ->
                MessageDialog(
                    message = successMessage,
                    onDismiss = {
                        viewModel.resetUiState()
                    },
                    isError = false
                )
                categoryName = ""
            }

            uiState.errorMessage?.let { errorMessage ->
                MessageDialog(
                    message = errorMessage,
                    onDismiss = { viewModel.resetUiState() },
                    isError = true
                )
            }
        }
    )
}
