package com.beldier.marvel.ui.screens.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.beldier.marvel.data.models.Error
import com.beldier.marvel.ui.MarvelScreen

@Composable
fun ErrorMessage(error: Error) {
    val message = when (error) {
        Error.Connectivity -> "Connectivity Error"
        is Error.Server -> "Server Error: ${error.code}"
        is Error.Unknown -> "Unknown Error: ${error.message}"
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = Icons.Default.Warning,
            contentDescription = message,
            modifier = Modifier.size(128.dp),
            tint = MaterialTheme.colors.error
        )
        Text(
            text = message,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h4
        )
    }
}

@Preview
@Composable
fun ErrorMessagePreview() {
    MarvelScreen {
        ErrorMessage(error = Error.Connectivity)
    }
}