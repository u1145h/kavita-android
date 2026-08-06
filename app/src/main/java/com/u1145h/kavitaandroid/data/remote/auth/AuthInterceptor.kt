package com.u1145h.kavitaandroid.data.remote.auth

import okhttp3.Interceptor
import okhttp3.Response

/**
 * Attaches the Kavita JWT as a Bearer token when a session is active.
 */
class AuthInterceptor(
    private val tokenProvider: () -> String?,
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = tokenProvider()
        val request = if (!token.isNullOrBlank()) {
            chain.request().newBuilder()
                .header("Authorization", "Bearer $token")
                .build()
        } else {
            chain.request()
        }
        return chain.proceed(request)
    }
}
