package com.example.mcsservice.model

import com.squareup.moshi.Json

data class Subject(
    @Json(name = "id") val id: Int,
    @Json(name = "created_at") val createdAt: String,
    @Json(name = "updated_at") val updatedAt: String,
    @Json(name = "name") val name: String,
    @Json(name = "description") val description: String,
    @Json(name = "teachers") val teachers: String,
)