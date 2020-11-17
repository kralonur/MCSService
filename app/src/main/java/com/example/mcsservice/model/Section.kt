package com.example.mcsservice.model

import com.squareup.moshi.Json

data class Section(
    @Json(name = "id") val id: Int,
    @Json(name = "created_at") val createdAt: String,
    @Json(name = "updated_at") val updatedAt: String,
    @Json(name = "name") val name: String,
    @Json(name = "description") val description: String,
    @Json(name = "subject_id") val subjectId: Int,
    @Json(name = "section_type_id") val sectionTypeId: Int,
    @Json(name = "section_type") val sectionType: SectionType,
)