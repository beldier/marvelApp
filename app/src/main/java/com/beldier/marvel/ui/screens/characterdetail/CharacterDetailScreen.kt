package com.beldier.marvel.ui.screens.characterdetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.ui.Alignment
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalProvider
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.beldier.marvel.MarvelApp
import com.beldier.marvel.data.repositories.CharactersRepository
import com.beldier.marvel.data.models.Character
import com.beldier.marvel.data.models.Reference
import com.beldier.marvel.ui.navigation.ArrowBackIcon

@Composable
fun CharacterDetailScreen(id: Int, onUpClick: () -> Unit) {
    var characterState by remember { mutableStateOf<Character?>(null) }
    LaunchedEffect(Unit) {
        characterState = CharactersRepository.findCharacter(id)
    }
    characterState?.let { c ->
        CharacterDetailScreen(character = c, onUpClick = onUpClick)
    }

}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CharacterDetailScreen(character: Character, onUpClick: () -> Unit) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(character.name) },
                navigationIcon = { ArrowBackIcon(onUpClick) },
                actions = { 
                    AppBarOverflowMenu(urls = character.urls)
                })
        }) {
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            item {
                Header(character)
            }
            section(Icons.Default.Collections, "Series", character.series)
            section(Icons.Default.Event, "Events", character.events)
            section(Icons.Default.Book, " Comics", character.comics)
            section(Icons.Default.Bookmark, "Stories", character.stories)
        }
    }

}

@ExperimentalMaterialApi
fun LazyListScope.section(icon: ImageVector, name: String, items: List<Reference>) {
    if (items.isEmpty()) return
    item {
        Text(
            text = name,
            style = MaterialTheme.typography.h5,
            modifier = Modifier.padding(16.dp)
        )
    }
    items(items) {
        ListItem(
            icon = { Icon(imageVector = icon, contentDescription = null) },
            text = { Text(it.name) }
        )
    }
}

@Composable
fun Header(character: Character) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Image(
            painter = rememberImagePainter(data = character.thumbnail),
            contentDescription = character.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray)
                .aspectRatio(1f)
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = character.name,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 0.dp),
            style = MaterialTheme.typography.h4
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = character.description,
            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(16.dp, 0.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))

    }

}

//@Preview(widthDp = 400, heightDp = 700)
//@Composable
//fun CharacterDetailScreenPreview() {
//    val c = Character(
//        id = 1,
//        name = "Iron Man",
//        description = "Lorem ipsum asd1231231 312 3213 1231 23",
//        thumbnail = "",
//        listOf(Reference("Comic 1"), Reference("Comic 2")),
//        listOf(Reference("Comic 1"), Reference("Comic 2")),
//        listOf(Reference("Comic 1"), Reference("Comic 2")),
//        listOf(Reference("Comic 1"), Reference("Comic 2")),
//    )
//    MarvelApp {
//        CharacterDetailScreen(character = c, onUpClick = {})
//    }
//}