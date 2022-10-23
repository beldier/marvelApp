package com.beldier.marvel.data.repositories

import com.beldier.marvel.data.models.Character
import com.beldier.marvel.data.models.Reference
import com.beldier.marvel.data.models.Url
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

fun NetworkCharacter.asCharacter(): Character {
    val comics = comics.items.map { Reference(it.name) }
    val series = series.items.map { Reference(it.name) }
    val events = events.items.map { Reference(it.name) }
    val stories = stories.items.map { Reference(it.name) }
    val urls = urls.map { Url(it.type,it.url) }
    return Character(
        id,
        name,
        description,
        thumbnail.asString(),
        comics,
        events, stories, series, urls
    )
}