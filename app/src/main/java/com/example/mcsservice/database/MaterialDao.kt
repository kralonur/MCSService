package com.example.mcsservice.database

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.mcsservice.model.database.DbMaterial
import kotlinx.coroutines.flow.Flow

interface MaterialDao {
    @Query("SELECT * FROM material")
    fun getAll(): Flow<List<DbMaterial>>

    @Query("SELECT * FROM material WHERE section_id = (:sectionId)")
    fun getAllBySectionId(sectionId: Int): Flow<List<DbMaterial>>

    @Query("SELECT * FROM material WHERE id = (:materialId)")
    fun getById(materialId: Int): Flow<DbMaterial>

    @Insert
    suspend fun insert(material: DbMaterial): Long

    @Insert
    suspend fun insertAll(materials: List<DbMaterial>)

    @Delete
    suspend fun delete(material: DbMaterial)
}