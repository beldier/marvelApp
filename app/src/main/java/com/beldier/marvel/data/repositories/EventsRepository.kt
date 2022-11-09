package com.beldier.marvel.data.repositories

import com.beldier.marvel.data.models.Event
import com.beldier.marvel.data.models.Result
import com.beldier.marvel.data.network.ApiClient


object EventsRepository : Repository<Event>() {

    suspend fun get(): Result<List<Event>> = super.get {
        ApiClient
            .eventsService
            .getEvents(0, 100)
            .data
            .results
            .map { it.asEvent() }
    }

    suspend fun find(id: Int): Result<Event> = super.find(
        id,
        findActionRemote = {
            ApiClient
                .eventsService
                .findEvent(id)
                .data
                .results
                .first()
                .asEvent()
        }
    )
}