package com.beldier.marvel.data.network.models

data class ReferenceList(
    val available: Int,
    val collectionURI: String,
    val items: List<Reference>?,
    val returned: Int
)