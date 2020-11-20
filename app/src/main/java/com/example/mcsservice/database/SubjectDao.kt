package com.example.mcsservice.database

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.mcsservice.model.database.DbSubject
import kotlinx.coroutines.flow.Flow

interface SubjectDao {
    @Query("SELECT * FROM subject")
    fun getAll(): Flow<List<DbSubject>>

    @Query("SELECT * FROM subject WHERE id = (:subjectId)")
    fun getById(subjectId: Int): Flow<DbSubject>

    @Insert
    suspend fun insert(subject: DbSubject): Long

    @Insert
    suspend fun insertAll(subjects: List<DbSubject>)

    @Delete
    suspend fun delete(subject: DbSubject)
}