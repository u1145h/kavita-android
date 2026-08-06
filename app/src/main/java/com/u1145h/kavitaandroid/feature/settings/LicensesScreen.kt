package com.u1145h.kavitaandroid.feature.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

private data class License(
    val name: String,
    val copyright: String,
    val license: String,
)

private val LICENSES = listOf(
    License("AndroidX", "The Android Open Source Project", "Apache-2.0"),
    License("Jetpack Compose", "The Android Open Source Project", "Apache-2.0"),
    License("Material Design", "Google", "Apache-2.0"),
    License("Kotlin", "JetBrains", "Apache-2.0"),
    License("Kotlinx.serialization", "JetBrains", "Apache-2.0"),
    License("Coroutines", "JetBrains", "Apache-2.0"),
    License("Room", "The Android Open Source Project", "Apache-2.0"),
    License("Hilt / Dagger", "Google", "Apache-2.0"),
    License("OkHttp", "Square", "Apache-2.0"),
    License("Retrofit", "Square", "Apache-2.0"),
    License("Coil", "Coil Contributors", "Apache-2.0"),
    License("WorkManager", "The Android Open Source Project", "Apache-2.0"),
    License("Readium Kotlin Toolkit", "Readium Foundation", "BSD-3-Clause"),
    License("junrar", "Edmund Wagner", "LGPL-3.0"),
    License("Kavita", "Kareem Khedr and contributors", "GPL-3.0"),
)

@OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)
@Composable
fun LicensesScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        containerColor = androidx.compose.ui.graphics.Color.Transparent,
        contentWindowInsets = WindowInsets(0),
        topBar = {
            TopAppBar(
                title = { Text("Licenses") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
            )
        },
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
        ) {
            items(count = LICENSES.size) { index ->
                val license = LICENSES[index]
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                ) {
                    Text(license.name, style = MaterialTheme.typography.titleSmall)
                    Text(
                        license.copyright,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                    Text(
                        license.license,
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.primary,
                    )
                }
                HorizontalDivider()
            }
            item {
                Spacer(Modifier.height(24.dp))
            }
        }
    }
}
