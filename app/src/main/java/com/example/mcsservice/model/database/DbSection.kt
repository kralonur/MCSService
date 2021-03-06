package com.example.mcsservice.model.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "section")
data class DbSection(
    @PrimaryKey(autoGenerate = false) val id: Int,
    @ColumnInfo(name = "updated_at") val updatedAt: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "subject_id") val subjectId: Int,
    @ColumnInfo(name = "section_type_id") val sectionTypeId: Int,
    @ColumnInfo(name = "section_pass") val sectionPass: String,
)