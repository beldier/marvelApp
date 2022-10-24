package com.beldier.marvel.ui.screens.characterdetail

import android.content.Context
import com.beldier.marvel.R
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.core.app.ShareCompat
import com.beldier.marvel.data.models.Character
import com.beldier.marvel.ui.navigation.ArrowBackIcon

@Composable
fun CharacterDetailScaffold(
    character: Character,
    onUpClick: () -> Unit,
    content: @Composable (PaddingValues) -> Unit
) {
    val context = LocalContext.current
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(character.name) },
                navigationIcon = { ArrowBackIcon(onUpClick) },
                actions = {
                    AppBarOverflowMenu(urls = character.urls)
                })
        },
        floatingActionButton = {
            if (character.urls.isNotEmpty()) {
                FloatingActionButton(onClick = { shareCharacter(context, character) }) {
                    Icon(
                        imageVector = Icons.Default.Share,
                        contentDescription = stringResource(id = R.string.share_character)
                    )
                }
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        content = content
    )
}


fun shareCharacter(context: Context, character: Character) {
    val intent = ShareCompat
        .IntentBuilder(context).setType("text/plain").setSubject(character.name)
        .setText(character.urls.first().url).intent.also { context.startActivity(it) }

}
