package com.example.mcsservice.database

import androidx.room.*
import com.example.mcsservice.model.database.DbMaterial
import kotlinx.coroutines.flow.Flow

@Dao
interface MaterialDao {
    @Query("SELECT * FROM material")
    fun getAll(): Flow<List<DbMaterial>>

    @Query("SELECT * FROM material WHERE section_id = (:sectionId)")
    fun getAllBySectionId(sectionId: Int): Flow<List<DbMaterial>>

    @Query("SELECT * FROM material WHERE section_id = (:sectionId)")
    suspend fun getAllBySectionIdSuspend(sectionId: Int): List<DbMaterial>

    @Query("SELECT * FROM material WHERE id = (:materialId)")
    fun getById(materialId: Int): Flow<DbMaterial>

    @Insert
    suspend fun insert(material: DbMaterial): Long

    @Insert
    suspend fun insertAll(materials: List<DbMaterial>)

    @Delete
    suspend fun delete(material: DbMaterial)

    @Update
    suspend fun update(material: DbMaterial)

    @Update
    suspend fun updateAll(materials: List<DbMaterial>)
}