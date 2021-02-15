package com.example.mcsservice.model

data class DomainSection(
    val id: Int,
    val updatedAt: String,
    val name: String,
    val description: String,
    val subjectId: Int,
    val sectionTypeId: Int,
    val sectionPass: String,
    var unlockRequired: Boolean
)