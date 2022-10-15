package com.beldier.marvel.data.repositories

import com.beldier.marvel.data.models.Character
import com.beldier.marvel.data.network.models.Character as NetworkCharacter

import com.beldier.marvel.data.network.ApiClient
import com.beldier.marvel.data.network.models.asString

object CharactersRepository {

    suspend fun getCharacters(): List<Character> {
        val result = ApiClient.charactersService.getCharacters(0, 100)
        return result.data.results.map {
            it.asCharacter()
        }
    }

    suspend fun findCharacter(characterId: Int): Character {
        val result = ApiClient.charactersService.findCharacter(characterId)
        return result.data.results.first().asCharacter()
    }
}

fun NetworkCharacter.asCharacter() = Character(
    id,
    name,
    description,
    thumbnail.asString()
)