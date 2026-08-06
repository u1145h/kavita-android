package com.u1145h.kavitaandroid.data.remote.api

import com.u1145h.kavitaandroid.data.remote.dto.BookInfoDto
import com.u1145h.kavitaandroid.data.remote.dto.ChapterInfoDto
import com.u1145h.kavitaandroid.data.remote.dto.LoginRequest
import com.u1145h.kavitaandroid.data.remote.dto.ProgressDto
import com.u1145h.kavitaandroid.data.remote.dto.TokenRefreshRequest
import com.u1145h.kavitaandroid.data.remote.dto.TokenRefreshResponse
import com.u1145h.kavitaandroid.data.remote.dto.UserDto
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import retrofit2.http.Streaming

/**
 * REST client for the Kavita server. The base URL resolves to
 * `{server}/api/` so paths below are relative to that prefix.
 */
interface KavitaApiService {

    @POST("Account/login")
    suspend fun login(@Body body: LoginRequest): Response<UserDto>

    @POST("Account/refresh-token")
    suspend fun refreshToken(@Body body: TokenRefreshRequest): Response<TokenRefreshResponse>

    @GET("Account")
    suspend fun getAccount(): Response<UserDto>

    @GET("Reader/get-progress")
    suspend fun getProgress(@Query("chapterId") chapterId: Int): Response<ProgressDto>

    @POST("Reader/progress")
    suspend fun saveProgress(@Body body: ProgressDto): Response<Unit>

    @GET("Reader/chapter-info")
    suspend fun getChapterInfo(@Query("chapterId") chapterId: Int): Response<ChapterInfoDto>

    @GET("Book/{chapterId}/book-info")
    suspend fun getBookInfo(@Query("chapterId") chapterId: Int): Response<BookInfoDto>

    @Streaming
    @GET("Image/series-cover")
    suspend fun getSeriesCover(@Query("seriesId") seriesId: Int): Response<ResponseBody>
}
