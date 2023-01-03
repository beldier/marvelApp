package com.beldier.marvel.data.repositories

import com.beldier.marvel.data.models.Comic
import com.beldier.marvel.data.models.Result
import com.beldier.marvel.data.models.tryCall
import com.beldier.marvel.data.network.ComicsService
import javax.inject.Inject

class ComicsRepository @Inject constructor(private val service: ComicsService){

    suspend fun get(format: Comic.Format): Result<List<Comic>> = tryCall {
        service
            .getComics(0, 20, format.toStringFormat())
            .data
            .results
            .map { it.asComic() }

    }


    suspend fun find(id: Int): Result<Comic> = tryCall {
        service
            .findComic(id)
            .data
            .results
            .first()
            .asComic()
    }
}