package com.beldier.marvel.data.network.models

data class Thumbnail(
    val extension: String,
    val path: String
)

fun Thumbnail.asString() = "$path.$extension".replace("http","https")