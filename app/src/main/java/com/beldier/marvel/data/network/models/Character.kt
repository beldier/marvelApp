package com.beldier.marvel.data.network.models

data class Character(
    val comics: ReferenceList,
    val events: ReferenceList,
    val series: ReferenceList,
    val stories: ReferenceList,
    val description: String,
    val id: Int,
    val modified: String,
    val name: String,
    val resourceURI: String,
    val thumbnail: Thumbnail,
    val urls: List<Url>
)