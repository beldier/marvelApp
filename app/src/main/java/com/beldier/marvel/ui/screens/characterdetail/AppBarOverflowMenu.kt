package com.beldier.marvel.ui.screens.characterdetail

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalUriHandler
import com.beldier.marvel.data.models.Url

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AppBarOverflowMenu(urls: List<Url>) {
    if(urls.isEmpty()) return
    var showMenu by remember { mutableStateOf(false) }
    val uriHandler = LocalUriHandler.current
    IconButton(onClick = { showMenu = true }) {
        Icon(
            imageVector = Icons.Default.MoreVert,
            contentDescription = "More actions"
        )
        DropdownMenu(expanded = showMenu, onDismissRequest = { showMenu = false }) {
            urls.forEach {
                DropdownMenuItem(onClick = {
                    uriHandler.openUri(it.url)
                    showMenu = false
                }) {
                    ListItem(text = { Text(it.type) })
                }
            }
        }
    }
}