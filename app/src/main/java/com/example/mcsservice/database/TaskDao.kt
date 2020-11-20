package com.example.mcsservice.database

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.mcsservice.model.database.DbTask
import kotlinx.coroutines.flow.Flow

interface TaskDao {
    @Query("SELECT * FROM task")
    fun getAll(): Flow<List<DbTask>>

    @Query("SELECT * FROM task WHERE section_id = (:sectionId)")
    fun getAllBySectionId(sectionId: Int): Flow<List<DbTask>>

    @Query("SELECT * FROM task WHERE id = (:taskId)")
    fun getById(taskId: Int): Flow<DbTask>

    @Insert
    suspend fun insert(task: DbTask): Long

    @Insert
    suspend fun insertAll(tasks: List<DbTask>)

    @Delete
    suspend fun delete(task: DbTask)
}