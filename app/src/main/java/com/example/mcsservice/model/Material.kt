package com.example.mcsservice.model

import com.squareup.moshi.Json

data class Material(
    @Json(name = "id") val id: Int,
    @Json(name = "created_at") val createdAt: String,
    @Json(name = "updated_at") val updatedAt: String,
    @Json(name = "name") val name: String,
    @Json(name = "content") val content: String,
)