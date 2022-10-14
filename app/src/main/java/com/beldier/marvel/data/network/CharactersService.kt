package com.beldier.marvel.data.network

import com.beldier.marvel.data.network.models.ApiResponse
import com.beldier.marvel.data.network.models.Character
import retrofit2.http.GET
import retrofit2.http.Query

interface CharactersService {

    @GET("/v1/public/characters")
    suspend fun getCharacters(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): ApiResponse<Character>
}