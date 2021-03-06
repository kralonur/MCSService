package com.example.mcsservice.database

import androidx.room.*
import com.example.mcsservice.model.database.DbSubject
import kotlinx.coroutines.flow.Flow

@Dao
interface SubjectDao {
    @Query("SELECT * FROM subject")
    fun getAll(): Flow<List<DbSubject>>

    @Query("SELECT * FROM subject")
    suspend fun getAllSuspend(): List<DbSubject>

    @Query("SELECT * FROM subject WHERE id = (:subjectId)")
    fun getById(subjectId: Int): Flow<DbSubject>

    @Insert
    suspend fun insert(subject: DbSubject): Long

    @Insert
    suspend fun insertAll(subjects: List<DbSubject>)

    @Delete
    suspend fun delete(subject: DbSubject)

    @Update
    suspend fun update(subject: DbSubject)

    @Update
    suspend fun updateAll(subjects: List<DbSubject>)
}