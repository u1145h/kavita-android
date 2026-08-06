package com.u1145h.kavitaandroid.feature.home

import android.util.Log
import com.u1145h.kavitaandroid.data.local.datastore.Session
import com.u1145h.kavitaandroid.data.remote.auth.SessionManager
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.contentOrNull
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

/**
 * Native side of the WebView bridge (`window.KavitaAndroid`). Receives the
 * Kavita auth token captured from the web UI and tracks the webpage body color
 * so the status and navigation bars can be painted to match it.
 */
@Singleton
class KavitaBridge @Inject constructor(
    private val sessionManager: SessionManager,
) {
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Default)

    private val _bodyColor = MutableStateFlow(DEFAULT_BODY_COLOR)
    val bodyColor: StateFlow<Long> = _bodyColor

    @android.webkit.JavascriptInterface
    fun getToken(): String = sessionManager.token ?: ""

    @android.webkit.JavascriptInterface
    fun getApiKey(): String = sessionManager.apiKey ?: ""

    @android.webkit.JavascriptInterface
    fun getUsername(): String = sessionManager.session.value.username ?: ""

    @android.webkit.JavascriptInterface
    fun onSession(json: String) {
        scope.launch {
            runCatching {
                val obj = Json.parseToJsonElement(json).jsonObject
                val token = obj["token"]?.jsonPrimitive?.contentOrNull
                val username = obj["username"]?.jsonPrimitive?.contentOrNull
                if (!token.isNullOrBlank()) {
                    sessionManager.update(
                        Session(
                            token = token,
                            apiKey = sessionManager.apiKey,
                            username = username ?: sessionManager.session.value.username,
                        ),
                    )
                }
            }.onFailure { Log.w("KavitaBridge", "Bad session payload", it) }
        }
    }

    @android.webkit.JavascriptInterface
    fun onBodyColor(color: String) {
        parseCssColor(color)?.let { _bodyColor.value = it }
    }

    @android.webkit.JavascriptInterface
    fun log(message: String) {
        Log.d("KavitaBridge", message)
    }

    private fun parseCssColor(color: String): Long? {
        val match = Regex("""rgba?\(\s*(\d+)\s*,\s*(\d+)\s*,\s*(\d+)\s*(?:,\s*([\d.]+)\s*)?\)""")
            .find(color.trim())
            ?: return null
        val channels = match.groupValues.drop(1).take(3)
            .map { it.toIntOrNull()?.coerceIn(0, 255) }
        val r = channels[0] ?: return null
        val g = channels[1] ?: return null
        val b = channels[2] ?: return null
        val alpha = match.groupValues.getOrNull(4)?.toFloatOrNull()?.coerceIn(0f, 1f) ?: 1f
        val a = (alpha * 255).toInt()
        return (a.toLong() shl 24) or (r.toLong() shl 16) or (g.toLong() shl 8) or b.toLong()
    }

    companion object {
        /** Kavita's dark-mode body background, used until the page reports its color. */
        private const val DEFAULT_BODY_COLOR = 0xFF0E0E0E
    }
}
