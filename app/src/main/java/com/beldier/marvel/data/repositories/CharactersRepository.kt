package com.beldier.marvel.data.repositories

import com.beldier.marvel.data.models.Character

import com.beldier.marvel.data.network.ApiClient
import com.beldier.marvel.data.network.models.asString

object CharactersRepository {

    suspend fun getCharacters(): List<Character> {
        val result = ApiClient.charactersService.getCharacters(0, 100)
        return result.data.results.map {
            Character(
                it.id,
                it.name,
                it.description,
                it.thumbnail.asString()
            )
        }

    }
}