package com.u1145h.kavitaandroid.ui.navigation

import kotlinx.serialization.Serializable

/** Top-level destination: the embedded Kavita web interface. */
@Serializable
object HomeRoute

/** Top-level destination: offline library. */
@Serializable
object LibraryRoute

/** Top-level destination: settings. */
@Serializable
object SettingsRoute

/** Full-screen reader for an offline book. */
@Serializable
data class ReaderRoute(val bookId: Long)

/** Open-source licenses. */
@Serializable
object LicensesRoute

/** About screen. */
@Serializable
object AboutRoute
