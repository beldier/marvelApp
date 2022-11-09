package com.beldier.marvel.data.repositories

import com.beldier.marvel.data.models.Comic
import com.beldier.marvel.data.models.Result
import com.beldier.marvel.data.models.tryCall
import com.beldier.marvel.data.network.ApiClient

object ComicsRepository {

    suspend fun get(format: Comic.Format): Result<List<Comic>> = tryCall {
        ApiClient
            .comicsService
            .getComics(0, 20, format.toStringFormat())
            .data
            .results
            .map { it.asComic() }

    }


    suspend fun find(id: Int): Result<Comic> = tryCall {
        ApiClient
            .comicsService
            .findComic(id)
            .data
            .results
            .first()
            .asComic()
    }
}