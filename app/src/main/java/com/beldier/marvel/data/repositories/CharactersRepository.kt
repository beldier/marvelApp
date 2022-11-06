package com.beldier.marvel.data.repositories

import com.beldier.marvel.data.models.Character
import com.beldier.marvel.data.network.ApiClient

object CharactersRepository : Repository<com.beldier.marvel.data.models.Character>() {

    suspend fun get(): List<Character> = super.get {
        ApiClient
            .charactersService
            .getCharacters(0, 100)
            .data
            .results
            .map { it.asCharacter() }
    }

    suspend fun find(id: Int): Character = super.find(
        id,
        findActionRemote = {
            ApiClient
                .charactersService
                .findCharacter(id)
                .data
                .results
                .first()
                .asCharacter()
        }
    )
}