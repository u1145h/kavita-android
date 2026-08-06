package com.u1145h.kavitaandroid.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

/** Top-level destinations reachable from the bottom navigation bar. */
enum class AppDestination(val label: String) {
    HOME("Home"),
    LIBRARY("Library"),
    SETTINGS("Settings"),
}

@Composable
private fun AppDestination.icon(): ImageVector = when (this) {
    AppDestination.HOME -> Icons.Filled.Home
    AppDestination.LIBRARY -> Icons.Filled.List
    AppDestination.SETTINGS -> Icons.Filled.Settings
}

/**
 * Bottom navigation bar with a pitch-black background, matching the status bar.
 */
@Composable
fun AppNavBar(
    selected: AppDestination?,
    onSelect: (AppDestination) -> Unit,
    modifier: Modifier = Modifier,
) {
    NavigationBar(
        containerColor = Color.Black,
        modifier = modifier,
    ) {
        AppDestination.entries.forEach { dest ->
            NavigationBarItem(
                selected = dest == selected,
                onClick = { onSelect(dest) },
                icon = {
                    Icon(
                        imageVector = dest.icon(),
                        contentDescription = dest.label,
                    )
                },
                label = { Text(dest.label) },
            )
        }
    }
}
