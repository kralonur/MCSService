package com.example.mcsservice.model

import com.squareup.moshi.Json

data class Task(
    @Json(name = "id") val id: Int,
    @Json(name = "created_at") val createdAt: String,
    @Json(name = "updated_at") val updatedAt: String,
    @Json(name = "name") val name: String,
    @Json(name = "description") val description: String,
    @Json(name = "difficulty") val difficulty: Int,
    @Json(name = "section_id") val sectionId: Int,
    @Json(name = "task_type_id") val taskTypeId: Int,
    @Json(name = "task_type") val taskType: TaskType,
)