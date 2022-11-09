package com.beldier.marvel.data.repositories

import com.beldier.marvel.data.models.Comic
import com.beldier.marvel.data.network.ApiClient

object ComicsRepository {

    suspend fun get(format: Comic.Format): List<Comic> =
        ApiClient
            .comicsService
            .getComics(0, 20, format.toStringFormat())
            .data
            .results
            .map { it.asComic() }


    suspend fun find(id: Int): Comic =
        ApiClient
            .comicsService
            .findComic(id)
            .data
            .results
            .first()
            .asComic()
}