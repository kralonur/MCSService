package com.example.mcsservice.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.mcsservice.model.database.DbSection
import kotlinx.coroutines.flow.Flow

@Dao
interface SectionDao {
    @Query("SELECT * FROM section")
    fun getAll(): Flow<List<DbSection>>

    @Query("SELECT * FROM section WHERE subject_id = (:subjectId)")
    fun getAllBySubjectId(subjectId: Int): Flow<List<DbSection>>

    @Query("SELECT * FROM section WHERE id = (:sectionId)")
    fun getById(sectionId: Int): Flow<DbSection>

    @Insert
    suspend fun insert(section: DbSection): Long

    @Insert
    suspend fun insertAll(sections: List<DbSection>)

    @Delete
    suspend fun delete(section: DbSection)
}