package com.u1145h.kavitaandroid.data.remote.auth

import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Rewrites the scheme/host/port of every request to the currently configured
 * server URL. This lets the Retrofit instance stay fixed while the server
 * address (including migrating to HTTPS or a public domain) changes at runtime.
 */
class DynamicBaseUrlInterceptor(
    private val serverUrlProvider: () -> String,
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val parsed = runCatching { serverUrlProvider().trim().toHttpUrlOrNull() }.getOrNull()
        val request = chain.request()
        if (parsed == null) {
            return chain.proceed(request)
        }
        val url = request.url.newBuilder()
            .scheme(parsed.scheme)
            .host(parsed.host)
            .apply {
                if (parsed.port != HttpUrl.defaultPort(parsed.scheme)) port(parsed.port)
            }
            .build()
        return chain.proceed(request.newBuilder().url(url).build())
    }
}
