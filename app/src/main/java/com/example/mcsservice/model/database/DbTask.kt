package com.example.mcsservice.model.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task")
data class DbTask(
    @PrimaryKey(autoGenerate = false) val id: Int,
    @ColumnInfo(name = "updated_at") val updatedAt: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "difficulty") val difficulty: Int,
    @ColumnInfo(name = "section_id") val sectionId: Int,
    @ColumnInfo(name = "task_type_id") val taskTypeId: Int,
    @ColumnInfo(name = "is_description_decrypted") val isDescriptionDecrypted: Boolean,
)