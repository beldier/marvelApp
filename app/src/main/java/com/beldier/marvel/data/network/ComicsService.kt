package com.beldier.marvel.data.network

import com.beldier.marvel.data.network.models.ApiResponse
import com.beldier.marvel.data.network.models.Comic
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ComicsService {
    @GET("/v1/public/comics")
    suspend fun getComics(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int,
        @Query("format") format: String?
    ): ApiResponse<Comic>

    @GET("/v1/public/comics/{comicId}")
    suspend fun findComic(
        @Path("comicId") comicId: Int,
    ): ApiResponse<Comic>
}