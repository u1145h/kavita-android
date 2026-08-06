package com.u1145h.kavitaandroid.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(
    val username: String = "",
    val password: String = "",
    val apiKey: String? = null,
)

@Serializable
data class TokenRefreshRequest(
    val token: String = "",
    val refreshToken: String = "",
)

@Serializable
data class TokenRefreshResponse(
    val token: String = "",
    val refreshToken: String = "",
)

@Serializable
data class AuthKeyDto(
    val id: Int = 0,
    val name: String = "",
    val key: String = "",
)

@Serializable
data class UserDto(
    val id: Int = 0,
    val username: String = "",
    val email: String? = null,
    val token: String? = null,
    val refreshToken: String? = null,
    val roles: List<String> = emptyList(),
    val authKeys: List<AuthKeyDto> = emptyList(),
)

/**
 * Reading progress for a single chapter.
 *
 * The Kavita API also returns `lastModifiedUtc` (ISO-8601) which is used for
 * conflict resolution during offline progress sync.
 */
@Serializable
data class ProgressDto(
    val chapterId: Int = 0,
    val pageNum: Int = 0,
    val seriesId: Int = 0,
    val volumeId: Int = 0,
    val libraryId: Int = 0,
    val bookScrollId: String? = null,
    val lastModifiedUtc: String? = null,
)

/** Metadata for a downloaded book, fetched from the Book endpoint. */
@Serializable
data class BookInfoDto(
    val bookTitle: String? = null,
    val seriesId: Int = 0,
    val volumeId: Int = 0,
    val seriesName: String? = null,
    val chapterNumber: String? = null,
    val volumeNumber: String? = null,
    val libraryId: Int = 0,
    val pages: Int = 0,
    val isSpecial: Boolean = false,
    val chapterTitle: String? = null,
)

/** Extended chapter information. */
@Serializable
data class ChapterInfoDto(
    val chapterNumber: String? = null,
    val volumeNumber: String? = null,
    val volumeId: Int = 0,
    val seriesName: String? = null,
    val seriesFormat: Int? = null,
    val seriesId: Int = 0,
    val libraryId: Int = 0,
    val chapterTitle: String? = null,
    val pages: Int = 0,
    val fileName: String? = null,
    val isSpecial: Boolean = false,
    val subtitle: String? = null,
    val title: String? = null,
    val seriesTotalPages: Int = 0,
    val seriesTotalPagesRead: Int = 0,
)
