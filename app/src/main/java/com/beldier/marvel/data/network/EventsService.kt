package com.beldier.marvel.data.network

import com.beldier.marvel.data.network.models.ApiResponse
import com.beldier.marvel.data.network.models.Event
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface EventsService {

    @GET("/v1/public/events")
    suspend fun getEvents(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): ApiResponse<Event>

    @GET("/v1/public/events/{eventId}")
    suspend fun findEvent(
        @Path("eventId") eventId: Int,
    ): ApiResponse<Event>

}