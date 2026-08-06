package com.u1145h.kavitaandroid.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavOptions
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.u1145h.kavitaandroid.feature.home.HomeScreen
import com.u1145h.kavitaandroid.feature.library.LibraryScreen
import com.u1145h.kavitaandroid.feature.reader.ReaderScreen
import com.u1145h.kavitaandroid.feature.settings.AboutScreen
import com.u1145h.kavitaandroid.feature.settings.LicensesScreen
import com.u1145h.kavitaandroid.feature.settings.SettingsScreen
import com.u1145h.kavitaandroid.ui.components.AppDestination
import com.u1145h.kavitaandroid.ui.components.AppNavBar
import com.u1145h.kavitaandroid.ui.navigation.AboutRoute
import com.u1145h.kavitaandroid.ui.navigation.HomeRoute
import com.u1145h.kavitaandroid.ui.navigation.LibraryRoute
import com.u1145h.kavitaandroid.ui.navigation.LicensesRoute
import com.u1145h.kavitaandroid.ui.navigation.ReaderRoute
import com.u1145h.kavitaandroid.ui.navigation.SettingsRoute
import com.u1145h.kavitaandroid.ui.theme.KavitaTheme

/**
 * Root composable. Holds the application theme, the navigation graph and the
 * pitch-black bottom navigation bar.
 */
@Composable
fun KavitaApp() {
    val navController = rememberNavController()
    val rootViewModel: RootViewModel = hiltViewModel()
    val settings by rootViewModel.settings.collectAsStateWithLifecycle()

    // The app is dark mode only; there is no light theme.
    val darkTheme = true

    val backStackEntry by navController.currentBackStackEntryAsState()
    val destination = backStackEntry?.destination

    val isReader = destination?.hasRoute(ReaderRoute::class) == true
    val selected = when {
        destination?.hasRoute(HomeRoute::class) == true -> AppDestination.HOME
        destination?.hasRoute(LibraryRoute::class) == true -> AppDestination.LIBRARY
        destination?.hasRoute(SettingsRoute::class) == true -> AppDestination.SETTINGS
        else -> null
    }

    val navigateTo: (AppDestination) -> Unit = { dest ->
        val options = NavOptions.Builder()
            .setPopUpTo(navController.graph.startDestinationId, true)
            .setLaunchSingleTop(true)
            .setRestoreState(true)
            .build()
        when (dest) {
            AppDestination.HOME -> navController.navigate(HomeRoute, options)
            AppDestination.LIBRARY -> navController.navigate(LibraryRoute, options)
            AppDestination.SETTINGS -> navController.navigate(SettingsRoute, options)
        }
    }

    KavitaTheme(darkTheme = darkTheme, dynamicColor = settings.dynamicColor) {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            Scaffold(
                containerColor = Color.Transparent,
                bottomBar = {
                    if (!isReader) {
                        AppNavBar(selected = selected, onSelect = navigateTo)
                    }
                },
            ) { inner ->
                Box(Modifier.fillMaxSize()) {
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopCenter)
                            .fillMaxWidth()
                            .background(Color.Black)
                            .statusBarsPadding(),
                    )
                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .fillMaxWidth()
                            .background(Color.Black)
                            .navigationBarsPadding(),
                    )
                    NavHost(
                        navController = navController,
                        startDestination = HomeRoute,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(bottom = inner.calculateBottomPadding()),
                    ) {
                        composable<HomeRoute> {
                            HomeScreen(
                                onOpenLibrary = { navController.navigate(LibraryRoute) },
                                onOpenSettings = { navController.navigate(SettingsRoute) },
                            )
                        }
                        composable<LibraryRoute> {
                            LibraryScreen(
                                onOpenBook = { bookId -> navController.navigate(ReaderRoute(bookId)) },
                                onOpenSettings = { navController.navigate(SettingsRoute) },
                            )
                        }
                        composable<SettingsRoute> {
                            SettingsScreen(
                                onBack = { navController.popBackStack() },
                                onOpenLicenses = { navController.navigate(LicensesRoute) },
                                onOpenAbout = { navController.navigate(AboutRoute) },
                            )
                        }
                        composable<ReaderRoute> { entry ->
                            ReaderScreen(
                                bookId = entry.toRoute<ReaderRoute>().bookId,
                                onBack = { navController.popBackStack() },
                            )
                        }
                        composable<LicensesRoute> {
                            LicensesScreen(onBack = { navController.popBackStack() })
                        }
                        composable<AboutRoute> {
                            AboutScreen(onBack = { navController.popBackStack() })
                        }
                    }
                }
            }
        }
    }
}
