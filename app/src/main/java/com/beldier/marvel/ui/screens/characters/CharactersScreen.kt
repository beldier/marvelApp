@file:OptIn(ExperimentalFoundationApi::class)

package com.beldier.marvel.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.beldier.marvel.MarvelApp
import com.beldier.marvel.data.models.Character
import com.beldier.marvel.data.repositories.CharactersRepository


@Composable
fun CharactersScreen(onClick: (Character) -> Unit){
    var charactersState by rememberSaveable{
        mutableStateOf(emptyList<Character>())
    }
    LaunchedEffect(Unit) {
        charactersState = CharactersRepository.getCharacters()
    }

    CharactersScreen(
        characters = charactersState,
        onClick = onClick
    )
}

@ExperimentalFoundationApi
@Composable
fun CharactersScreen(characters: List<Character>, onClick: (Character) -> Unit) {
    LazyVerticalGrid(
        cells = GridCells.Adaptive(180.dp),
        contentPadding = PaddingValues(4.dp)
    ) {
        items(characters) {
            CharacterItem(character = it, modifier = Modifier.clickable {
                onClick(it)
            })
        }
    }
}

@Composable
fun CharacterItem(character: Character, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(8.dp)
    ) {
        Card {
            Image(
                painter = rememberImagePainter(character.thumbnail),
                contentDescription = character.name,
                modifier = Modifier
                    .background(Color.LightGray)
                    .background(Color.LightGray)
                    .fillMaxWidth()
                    .aspectRatio(1f)
            )
        }
        Text(
            text = character.name, style = MaterialTheme.typography.subtitle1,
            maxLines = 2,
            modifier = Modifier.padding(8.dp, 16.dp)
        )
    }
}

