package com.beldier.marvel.data.network.models



data class Event(
    val id: Int,
    val title: String,
    val description: String,
    val thumbnail: Thumbnail,
    val characters: ReferenceList,
    val comics: ReferenceList,
    val series: ReferenceList,
    val stories: ReferenceList,
    val urls: List<Url>,
    val start: String,
    val end: String,
    val previous: Reference,
    val next: Reference,
    val modified: String,
    val resourceURI: String
)