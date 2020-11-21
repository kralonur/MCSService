package com.example.mcsservice.repository

import android.content.Context
import com.example.mcsservice.api.NetworkService
import com.example.mcsservice.database.AppDatabase
import com.example.mcsservice.model.SectionDetailItem
import com.example.mcsservice.model.SectionDetailItemType
import com.example.mcsservice.model.mapper.MaterialRemoteToDbMapper
import com.example.mcsservice.model.mapper.SectionRemoteToDbMapper
import com.example.mcsservice.model.mapper.SubjectRemoteToDbMapper
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

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

    private fun getMaterialListBySectionId(sectionId: Int) =
        db.materialDao().getAllBySectionId(sectionId)
            .map {
                return@map it.map { item ->
                    SectionDetailItem(
                        SectionDetailItemType.MATERIAL,
                        material = item
                    )
                }
            }

    private fun getMaterialHeader() = flow {
        emit(
            listOf(
                SectionDetailItem(
                    SectionDetailItemType.HEADER,
                    "Materials"
                )
            )
        )
    }

    private fun getTaskListBySectionId(sectionId: Int) = db.taskDao().getAllBySectionId(sectionId)
        .map {
            return@map it.map { item ->
                SectionDetailItem(
                    SectionDetailItemType.TASK,
                    task = item
                )
            }
        }

    private fun getTaskHeader() = flow {
        emit(
            listOf(
                SectionDetailItem(
                    SectionDetailItemType.HEADER,
                    "Tasks"
                )
            )
        )
    }

    fun getSubjectById(subjectId: Int) = db.subjectDao().getById(subjectId)

    fun getSectionById(sectionId: Int) = db.sectionDao().getById(sectionId)

    fun getSectionDetailList(sectionId: Int) = combine(
        getMaterialHeader(),
        getMaterialListBySectionId(sectionId),
        getTaskHeader(),
        getTaskListBySectionId(sectionId)
    ) { f1, f2, f3, f4 ->
        val list = ArrayList<SectionDetailItem>()
        if (f2.isNotEmpty()) {
            list.addAll(f1)
            list.addAll(f2)
        }
        if (f4.isNotEmpty()) {
            list.addAll(f3)
            list.addAll(f4)
        }

        return@combine list as List<SectionDetailItem>
    }

}