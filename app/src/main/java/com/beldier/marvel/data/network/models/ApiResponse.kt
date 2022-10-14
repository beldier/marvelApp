package com.beldier.marvel.data.network.models

data class ApiResponse<T>(
    val attributionHTML: String,
    val attributionText: String,
    val code: Int,
    val copyright: String,
    val data: Data<T>,
    val etag: String,
    val status: String
)