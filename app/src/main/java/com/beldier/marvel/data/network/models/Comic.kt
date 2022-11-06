package com.beldier.marvel.data.network.models

data class Comic(
    val characters: ReferenceList,
    val creators: ReferenceList,
    val description: String?,
    val diamondCode: String,
    val digitalId: Int,
    val ean: String,
    val events: ReferenceList,
    val format: String,
    val id: Int,
    val isbn: String,
    val issn: String,
    val issueNumber: Int,
    val modified: String,
    val pageCount: Int,
    val prices: List<Price>,
    val resourceURI: String,
    val series: ReferenceList,
    val stories: ReferenceList,
    val thumbnail: Thumbnail,
    val title: String,
    val upc: String,
    val urls: List<Url>,
    val variantDescription: String,
    val variants: List<Variant>
)