package ke.co.banit.flowmartsdk.features.user

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
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
fun UpdateUserScreen(viewModel: UserViewModel) {
    // Fetch user details when screen opens
    LaunchedEffect(Unit) {
        viewModel.fetchUser()
    }
    // Reset state when leaving the screen
    DisposableEffect(Unit) {
        onDispose {
            viewModel.resetUiState()
        }
    }

    val uiState by viewModel.uiState.collectAsState()

    // Local state for form fields
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }

    // Initialize form fields with user data when fetched
    LaunchedEffect(uiState) {
        if (uiState.user != null) {
            val user = uiState.user!!
            name = user.name
            email = user.email
            phone = user.phone
        }
    }

    val isFormValid =
        name.isNotBlank() || email.isNotBlank() || phone.isNotBlank() || password.isNotBlank()

    Scaffold(topBar = {
        TopAppBar(title = {
            Text(
                "Update Profile",
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold)
            )
        }, actions = {
            IconButton(onClick = { viewModel.deleteUser() }) {
                Icon(
                    imageVector = Icons.Default.Delete, contentDescription = "Delete"
                )
            }
        })
    }, content = { paddingValues ->
        DisplayUserDetails(modifier = Modifier.padding(paddingValues),
            name = name,
            email = email,
            phone = phone,
            password = password,
            isPasswordVisible = isPasswordVisible,
            onNameChange = { name = it },
            onEmailChange = { email = it },
            onPhoneChange = { phone = it },
            onPasswordChange = { password = it },
            onPasswordVisibilityChange = { isPasswordVisible = !isPasswordVisible },
            isFormValid = isFormValid,
            onSubmit = {
                viewModel.updateUser(name, email, phone, password)
            })
        // Show Loading Dialog if loading
        LoadingDialog(isLoading = uiState.isLoading)

        uiState.successMessage?.let { successMessage ->
            MessageDialog(
                message = successMessage, onDismiss = {
                    viewModel.resetUiState()
                }, isError = false
            )
        }

        uiState.errorMessage?.let { errorMessage ->
            MessageDialog(
                message = errorMessage, onDismiss = { viewModel.resetUiState() }, isError = true
            )
        }
    })
}

@Composable
fun DisplayUserDetails(
    modifier: Modifier = Modifier,
    name: String,
    email: String,
    phone: String,
    password: String,
    isPasswordVisible: Boolean,
    onNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPhoneChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onPasswordVisibilityChange: () -> Unit,
    isFormValid: Boolean,
    onSubmit: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Name Field
        OutlinedTextField(
            value = name,
            onValueChange = onNameChange,
            textStyle = MaterialTheme.typography.bodySmall,
            label = { Text("Name", style = MaterialTheme.typography.bodySmall) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            singleLine = true
        )

        // Email Field
        OutlinedTextField(
            value = email,
            onValueChange = onEmailChange,
            label = { Text("Email", style = MaterialTheme.typography.bodySmall) },
            textStyle = MaterialTheme.typography.bodySmall,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )

        // Phone Field
        OutlinedTextField(
            value = phone,
            onValueChange = onPhoneChange,
            label = { Text("Phone", style = MaterialTheme.typography.bodySmall) },
            textStyle = MaterialTheme.typography.bodySmall,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
        )

        // Password Field
        OutlinedTextField(value = password,
            onValueChange = onPasswordChange,
            label = { Text("Password", style = MaterialTheme.typography.bodySmall) },
            textStyle = MaterialTheme.typography.bodySmall,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            singleLine = true,
            visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = onPasswordVisibilityChange) {
                    Icon(
                        imageVector = if (isPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                        contentDescription = if (isPasswordVisible) "Hide password" else "Show password"
                    )
                }
            })

        // Submit Button
        Button(
            onClick = onSubmit,
            enabled = isFormValid,
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(12.dp)
        ) {
            Text("Update", style = MaterialTheme.typography.labelLarge)
        }
    }
}

