package com.example.mcsservice.repository

import android.content.Context
import com.example.mcsservice.api.NetworkService
import com.example.mcsservice.database.AppDatabase
import com.example.mcsservice.model.mapper.MaterialRemoteToDbMapper
import com.example.mcsservice.model.mapper.SectionRemoteToDbMapper
import com.example.mcsservice.model.mapper.SubjectRemoteToDbMapper

class SubjectRepository(context: Context) : BaseRepository() {
    private val api = NetworkService.subjectService
    private val db = AppDatabase.getInstance(context)

    fun initDatabase() = flowCall {
        val subjects = api.getAllSubjects().map { SubjectRemoteToDbMapper.map(it) }
        val sections = api.getAllSections().map { SectionRemoteToDbMapper.map(it) }
        val materials = api.getAllMaterials().map { MaterialRemoteToDbMapper.map(it) }

        db.subjectDao().insertAll(subjects)
        db.sectionDao().insertAll(sections)
        db.materialDao().insertAll(materials)
    }

    fun getSubjectList() = db.subjectDao().getAll()

    fun getSectionListBySubjectId(subjectId: Int) = db.sectionDao().getAllBySubjectId(subjectId)

    fun getMaterialListBySectionId(sectionId: Int) = db.materialDao().getAllBySectionId(sectionId)

    fun getTaskListBySectionId(sectionId: Int) = db.taskDao().getAllBySectionId(sectionId)

    fun getSubjectById(subjectId: Int) = db.subjectDao().getById(subjectId)

}