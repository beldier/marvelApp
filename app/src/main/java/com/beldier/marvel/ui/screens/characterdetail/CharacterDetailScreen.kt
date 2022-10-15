package com.beldier.marvel.ui.screens.characterdetail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Alignment
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.beldier.marvel.data.repositories.CharactersRepository
import com.beldier.marvel.data.models.Character

@Composable
fun CharacterDetailScreen(id: Int){

    var characterState by remember { mutableStateOf<Character?>(null) }
    LaunchedEffect(Unit ){
        characterState = CharactersRepository.findCharacter(id)
    }
    characterState?.let{ c ->
        CharacterDetailScreen(c)
    }

}

@Composable
fun CharacterDetailScreen(character: Character){
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(character.toString())

    }
}