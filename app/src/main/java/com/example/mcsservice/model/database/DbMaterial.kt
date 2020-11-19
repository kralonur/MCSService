package com.example.mcsservice.model.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "material")
data class DbMaterial(
    @PrimaryKey(autoGenerate = false) val id: Int,
    @ColumnInfo(name = "updated_at") val updatedAt: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "content") val content: String,
    @ColumnInfo(name = "is_visible") val isVisible: Boolean,
    @ColumnInfo(name = "section_id") val sectionId: Int,
)