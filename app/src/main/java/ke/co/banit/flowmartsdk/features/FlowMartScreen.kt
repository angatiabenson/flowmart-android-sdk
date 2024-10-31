package ke.co.banit.flowmartsdk.features

import androidx.compose.foundation.background
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
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import ke.co.banit.flowmartsdk.ui.theme.FlowMartSDKTheme

/**
 * @Author: Angatia Benson
 * @Date: 10/30/2024
 * Copyright (c) 2024 BanIT
 */


@Composable
fun FlowMartScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "FlowMart SDK Example App",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(20.dp))
        ElevatedButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = {},
            colors = ButtonDefaults.elevatedButtonColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
            )
        ) {
            Text(
                "Category",
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.labelLarge
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        ElevatedButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = {},
            colors = ButtonDefaults.elevatedButtonColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
            )
        ) {
            Text(
                "Product",
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.labelLarge
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        ElevatedButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = {},
            colors = ButtonDefaults.elevatedButtonColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
            )
        ) {
            Text(
                "User",
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.labelLarge
            )
        }
    }
}


@PreviewLightDark
@Composable
fun FlowMartScreenPreview() {
    FlowMartSDKTheme {
        FlowMartScreen(
            modifier = Modifier.background(color = MaterialTheme.colorScheme.background)
        )
    }
}