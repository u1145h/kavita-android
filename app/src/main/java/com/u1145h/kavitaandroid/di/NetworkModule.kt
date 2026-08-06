package com.u1145h.kavitaandroid.di

import com.u1145h.kavitaandroid.BuildConfig
import com.u1145h.kavitaandroid.core.config.ServerConfig
import com.u1145h.kavitaandroid.data.remote.api.KavitaApiService
import com.u1145h.kavitaandroid.data.remote.auth.AuthInterceptor
import com.u1145h.kavitaandroid.data.remote.auth.DynamicBaseUrlInterceptor
import com.u1145h.kavitaandroid.data.remote.auth.SessionManager
import com.u1145h.kavitaandroid.data.repository.SettingsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private val json = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
    }

    @Provides
    @Singleton
    @Named("apiBaseUrl")
    fun provideApiBaseUrl(): String = "${ServerConfig.DEFAULT_SERVER_URL}${ServerConfig.API_PATH}"

    @Provides
    @Singleton
    fun provideOkHttpClient(
        settingsRepository: SettingsRepository,
        sessionManager: SessionManager,
    ): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .connectTimeout(ServerConfig.CONNECT_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .readTimeout(ServerConfig.READ_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .writeTimeout(ServerConfig.WRITE_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .addInterceptor(DynamicBaseUrlInterceptor { settingsRepository.serverUrl.value })
            .addInterceptor(AuthInterceptor { sessionManager.token })

        if (BuildConfig.DEBUG) {
            builder.addInterceptor(
                HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC },
            )
        }
        return builder.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        client: OkHttpClient,
        @Named("apiBaseUrl") baseUrl: String,
    ): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(client)
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .build()

    @Provides
    @Singleton
    fun provideKavitaApiService(retrofit: Retrofit): KavitaApiService =
        retrofit.create(KavitaApiService::class.java)
}
