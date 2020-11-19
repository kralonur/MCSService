package com.example.mcsservice.model.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "subject")
data class DbSubject(
    @PrimaryKey(autoGenerate = false) val id: Int,
    @ColumnInfo(name = "updated_at") val updatedAt: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "teachers") val teachers: String,
)