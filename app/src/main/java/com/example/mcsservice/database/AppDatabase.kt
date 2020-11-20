package com.example.mcsservice.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mcsservice.model.database.DbMaterial
import com.example.mcsservice.model.database.DbSection
import com.example.mcsservice.model.database.DbSubject
import com.example.mcsservice.model.database.DbTask

@Database(
    entities = [DbMaterial::class, DbSection::class, DbSubject::class, DbTask::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context,
                        AppDatabase::class.java,
                        "roomdb"
                    )
                        .build()
                }
            }

            return INSTANCE as AppDatabase
        }
    }

    abstract fun materialDao(): MaterialDao
    abstract fun sectionDao(): SectionDao
    abstract fun subjectDao(): SubjectDao
    abstract fun taskDao(): TaskDao
}