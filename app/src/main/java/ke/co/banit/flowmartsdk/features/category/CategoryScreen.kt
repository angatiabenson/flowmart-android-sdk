package ke.co.banit.flowmartsdk.features.category

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import ke.co.banit.flowmartsdk.ui.theme.FlowMartSDKTheme

/**
 * @Author: Angatia Benson
 * @Date: 10/31/2024
 * Copyright (c) 2024 BanIT
 */

@Composable
fun CategoryScreen(
    modifier: Modifier = Modifier,
    onCreateCategoryClicked: () -> Unit = {},
    onFetchCategoryClicked: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Category Operations",
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(20.dp))
        ElevatedButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            onClick = onCreateCategoryClicked,
            colors = ButtonDefaults.elevatedButtonColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
            )
        ) {
            Text(
                "Create Category",
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.labelLarge
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        ElevatedButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            onClick = onFetchCategoryClicked,
            colors = ButtonDefaults.elevatedButtonColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
            )
        ) {
            Text(
                "Fetch Category",
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.labelLarge
            )
        }
    }
}

@PreviewLightDark
@Composable
fun CategoryScreenPreview() {
    FlowMartSDKTheme {
        CategoryScreen()
    }
}