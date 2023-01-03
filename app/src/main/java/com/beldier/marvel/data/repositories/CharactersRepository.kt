package com.beldier.marvel.data.repositories

import com.beldier.marvel.data.models.Character
import com.beldier.marvel.data.models.Result
import com.beldier.marvel.data.network.CharactersService
import javax.inject.Inject

class CharactersRepository @Inject constructor(private val service: CharactersService) : Repository<Character>() {

    suspend fun get(): Result<List<Character>> = super.get {
        service
            .getCharacters(0, 100)
            .data
            .results
            .map { it.asCharacter() }
    }

    suspend fun find(id: Int): Result<Character> = super.find(
        id,
        findActionRemote = {
            service
                .findCharacter(id)
                .data
                .results
                .first()
                .asCharacter()
        }
    )
}