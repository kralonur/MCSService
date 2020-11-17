package com.example.mcsservice.model

import com.squareup.moshi.Json

data class TaskType(
    @Json(name = "id") val id: Int,
    @Json(name = "name") val name: String,
)