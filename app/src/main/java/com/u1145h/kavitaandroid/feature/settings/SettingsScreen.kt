package com.u1145h.kavitaandroid.feature.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.outlined.SafetyDivider
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.u1145h.kavitaandroid.core.util.toFormattedSize

@OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    onBack: () -> Unit,
    onOpenLicenses: () -> Unit,
    onOpenAbout: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val viewModel: SettingsViewModel = hiltViewModel()
    val settings by viewModel.settings.collectAsStateWithLifecycle()
    val isLoggedIn by viewModel.isLoggedIn.collectAsStateWithLifecycle()
    val storageStats by viewModel.storageStats.collectAsStateWithLifecycle()

    var serverUrlText by rememberSaveable { mutableStateOf(settings.serverUrl) }
    var confirmClear by rememberSaveable { mutableStateOf(false) }

    Scaffold(
        modifier = modifier,
        containerColor = androidx.compose.ui.graphics.Color.Transparent,
        contentWindowInsets = WindowInsets(0),
        topBar = {
            TopAppBar(
                title = { Text("Settings") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
            )
        },
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState()),
        ) {
            SectionHeader("Server")
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                OutlinedTextField(
                    value = serverUrlText,
                    onValueChange = { serverUrlText = it },
                    label = { Text("Server URL") },
                    singleLine = true,
                    modifier = Modifier.weight(1f),
                )
                Spacer(Modifier.width(12.dp))
                Button(onClick = { viewModel.setServerUrl(serverUrlText) }) {
                    Text("Save")
                }
            }
            Spacer(Modifier.height(8.dp))

            SectionHeader("Appearance")
            SwitchRow(
                title = "Dynamic color",
                subtitle = "Use the system color palette on Android 12+",
                checked = settings.dynamicColor,
                onCheckedChange = viewModel::setDynamicColor,
            )

            SectionHeader("Offline library")
            ListItem(
                headlineContent = { Text("Storage used") },
                supportingContent = {
                    Text(
                        "${storageStats.bookCount} books · " +
                            storageStats.totalBytes.toFormattedSize(),
                    )
                },
            )
            ListItem(
                headlineContent = { Text("Clear offline library") },
                supportingContent = { Text("Delete all downloaded books and their covers.") },
                trailingContent = {
                    TextButton(onClick = { confirmClear = true }) {
                        Text("Clear", color = MaterialTheme.colorScheme.error)
                    }
                },
            )

            SectionHeader("Account")
            ListItem(
                headlineContent = { Text("Signed in") },
                supportingContent = { Text(if (isLoggedIn) "Yes" else "No") },
                trailingContent = {
                    TextButton(onClick = { viewModel.logout() }) {
                        Text("Log out")
                    }
                },
            )

            SectionHeader("Developer")
            SwitchRow(
                title = "Developer mode",
                subtitle = "Enable diagnostic tools and verbose logging",
                checked = settings.developerMode,
                onCheckedChange = viewModel::setDeveloperMode,
            )
            SwitchRow(
                title = "Verbose logging",
                subtitle = "Log extra detail to Logcat",
                checked = settings.verboseLogging,
                onCheckedChange = viewModel::setVerboseLogging,
            )

            SectionHeader("About")
            ListItem(
                headlineContent = { Text("Licenses") },
                leadingContent = {
                    Icon(Icons.Outlined.SafetyDivider, contentDescription = null)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(onClick = onOpenLicenses),
            )
            ListItem(
                headlineContent = { Text("About") },
                leadingContent = { Icon(Icons.Filled.Info, contentDescription = null) },
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(onClick = onOpenAbout),
            )

            Spacer(Modifier.height(24.dp))
        }
    }

    if (confirmClear) {
        AlertDialog(
            onDismissRequest = { confirmClear = false },
            title = { Text("Clear offline library?") },
            text = { Text("This deletes every downloaded book. This cannot be undone.") },
            confirmButton = {
                TextButton(onClick = {
                    viewModel.clearLibrary()
                    confirmClear = false
                }) { Text("Clear") }
            },
            dismissButton = {
                TextButton(onClick = { confirmClear = false }) { Text("Cancel") }
            },
        )
    }
}

@Composable
private fun SectionHeader(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleSmall,
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 4.dp),
    )
    HorizontalDivider()
}

@Composable
private fun SwitchRow(
    title: String,
    subtitle: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
) {
    ListItem(
        headlineContent = { Text(title) },
        supportingContent = { Text(subtitle) },
        trailingContent = { Switch(checked = checked, onCheckedChange = onCheckedChange) },
    )
}
