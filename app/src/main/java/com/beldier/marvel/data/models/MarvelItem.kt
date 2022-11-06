package com.beldier.marvel.data.models

interface MarvelItem{
    val id: Int
    val title: String
    val description: String
    val thumbnail: String
    val references: List<ReferenceList>
    val urls: List<Url>
}