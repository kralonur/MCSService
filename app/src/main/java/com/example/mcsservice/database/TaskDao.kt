package com.example.mcsservice.database

import androidx.room.*
import com.example.mcsservice.model.database.DbTask
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Query("SELECT * FROM task")
    fun getAll(): Flow<List<DbTask>>

    @Query("SELECT * FROM task WHERE section_id = (:sectionId)")
    fun getAllBySectionId(sectionId: Int): Flow<List<DbTask>>

    @Query("SELECT * FROM task WHERE section_id = (:sectionId)")
    suspend fun getAllBySectionIdSuspend(sectionId: Int): List<DbTask>

    @Query("SELECT * FROM task WHERE section_id = (:sectionId) and is_description_decrypted = (:decrypted)")
    fun getAllDecryptedBySectionId(sectionId: Int, decrypted: Boolean = true): Flow<List<DbTask>>

    @Query("SELECT * FROM task WHERE section_id = (:sectionId) and is_description_decrypted = (:decrypted)")
    suspend fun getAllDecryptedBySectionIdSuspend(
        sectionId: Int,
        decrypted: Boolean = true
    ): List<DbTask>

    @Query("SELECT * FROM task WHERE id = (:taskId)")
    fun getById(taskId: Int): Flow<DbTask>

    @Insert
    suspend fun insert(task: DbTask): Long

    @Insert
    suspend fun insertAll(tasks: List<DbTask>)

    @Delete
    suspend fun delete(task: DbTask)

    @Update
    suspend fun update(task: DbTask)

    @Update
    suspend fun updateAll(tasks: List<DbTask>)
}