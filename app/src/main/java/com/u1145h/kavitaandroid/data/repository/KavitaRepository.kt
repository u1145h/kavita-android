package com.u1145h.kavitaandroid.data.repository

import com.u1145h.kavitaandroid.data.remote.api.KavitaApiService
import com.u1145h.kavitaandroid.data.remote.dto.BookInfoDto
import com.u1145h.kavitaandroid.data.remote.dto.ChapterInfoDto
import com.u1145h.kavitaandroid.data.remote.dto.LoginRequest
import com.u1145h.kavitaandroid.data.remote.dto.ProgressDto
import com.u1145h.kavitaandroid.data.remote.dto.UserDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton

interface KavitaRepository {
    suspend fun login(username: String, password: String): Result<UserDto>
    suspend fun getProgress(chapterId: Int): Result<ProgressDto>
    suspend fun saveProgress(dto: ProgressDto): Result<Unit>
    suspend fun getBookInfo(chapterId: Int): Result<BookInfoDto>
    suspend fun getChapterInfo(chapterId: Int): Result<ChapterInfoDto>
    suspend fun fetchSeriesCover(seriesId: Int, destination: File): Result<File>
}

@Singleton
class KavitaRepositoryImpl @Inject constructor(
    private val api: KavitaApiService,
) : KavitaRepository {

    override suspend fun login(username: String, password: String): Result<UserDto> =
        safeCall { api.login(LoginRequest(username = username, password = password)).body() }

    override suspend fun getProgress(chapterId: Int): Result<ProgressDto> =
        safeCall { api.getProgress(chapterId).body() }

    override suspend fun saveProgress(dto: ProgressDto): Result<Unit> =
        safeCall { api.saveProgress(dto) }.map { }

    override suspend fun getBookInfo(chapterId: Int): Result<BookInfoDto> =
        safeCall { api.getBookInfo(chapterId).body() }

    override suspend fun getChapterInfo(chapterId: Int): Result<ChapterInfoDto> =
        safeCall { api.getChapterInfo(chapterId).body() }

    override suspend fun fetchSeriesCover(seriesId: Int, destination: File): Result<File> =
        safeCall {
            val response = api.getSeriesCover(seriesId)
            val body = response.body() ?: throw IllegalStateException("Empty cover response")
            body.byteStream().use { input ->
                destination.outputStream().use { output -> input.copyTo(output) }
            }
            destination
        }

    private suspend fun <T> safeCall(block: suspend () -> T?): Result<T> =
        withContext(Dispatchers.IO) {
            runCatching { block() ?: throw IllegalStateException("Empty response from server") }
        }
}
