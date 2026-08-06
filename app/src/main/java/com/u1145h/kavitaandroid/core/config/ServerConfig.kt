package com.u1145h.kavitaandroid.core.config

/**
 * Central server configuration. The active server URL is stored in DataStore
 * and editable from Settings; this object only holds defaults and tuning
 * values. Migrating to HTTPS or a public domain is a configuration-only change.
 */
object ServerConfig {

    /** Initial server address. */
    const val DEFAULT_SERVER_URL = "http://100.122.11.42:3005"

    /** Retrofit base path under the server host. */
    const val API_PATH = "/api/"

    const val CONNECT_TIMEOUT_SECONDS = 30L
    const val READ_TIMEOUT_SECONDS = 60L
    const val WRITE_TIMEOUT_SECONDS = 60L

    /** How often the periodic background sync runs. */
    const val SYNC_INTERVAL_MINUTES = 6L * 60L

    /** Name of the Kavita generic API key used for long-lived authenticated calls. */
    const val GENERIC_API_KEY_NAME = "Generic"
}
