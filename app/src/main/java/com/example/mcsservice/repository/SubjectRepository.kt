package com.example.mcsservice.repository

import android.content.Context
import com.example.mcsservice.api.NetworkService
import com.example.mcsservice.database.AppDatabase
import com.example.mcsservice.model.SectionDetailItem
import com.example.mcsservice.model.SectionDetailItemType
import com.example.mcsservice.model.database.DbSection
import com.example.mcsservice.model.database.DbTask
import com.example.mcsservice.model.mapper.MaterialRemoteToDbMapper
import com.example.mcsservice.model.mapper.SectionRemoteToDbMapper
import com.example.mcsservice.model.mapper.SubjectRemoteToDbMapper
import com.example.mcsservice.model.mapper.TaskRemoteToDbMapper
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import timber.log.Timber

class SubjectRepository(context: Context) : BaseRepository() {
    private val api = NetworkService.subjectService
    private val db = AppDatabase.getInstance(context)

    fun initDatabase() = flowCall {
        val subjects = api.getAllSubjects().map { SubjectRemoteToDbMapper.map(it) }
        val sections = api.getAllSections().map { SectionRemoteToDbMapper.map(it) }
        val materials = api.getAllMaterials().map { MaterialRemoteToDbMapper.map(it) }
        val tasks = api.getAllTasks().map { TaskRemoteToDbMapper.map(it) }

        db.subjectDao().insertAll(subjects)
        db.sectionDao().insertAll(sections)
        db.materialDao().insertAll(materials)
        db.taskDao().insertAll(tasks)
    }

    fun updateDatabase() = flowCall {
        updateSubjectDatabase()
    }

    private suspend fun updateSubjectDatabase() {
        val subjectsRemote = api.getAllSubjects().map { SubjectRemoteToDbMapper.map(it) }
        val subjectsLocal = db.subjectDao().getAllSuspend()

        val subjectsToAdd =
            subjectsRemote.filter { remote -> subjectsLocal.find { local -> local.id == remote.id } == null }

        val subjectsToUpdate =
            subjectsRemote.filter { remote ->
                subjectsLocal.find { local -> local.id == remote.id }
                    ?.let { tL -> remote.updatedAt != tL.updatedAt } ?: false
            }

        val subjectIdChangeList = subjectsToAdd.toMutableList()
            .apply { addAll(subjectsToUpdate) }.map { m -> m.id }.toList()

        Timber.i("Subject update")
        Timber.i(subjectsToAdd.toString())
        Timber.i(subjectsToUpdate.toString())
        updateSectionDatabase(subjectIdChangeList)

        db.subjectDao().insertAll(subjectsToAdd)
        db.subjectDao().updateAll(subjectsToUpdate)
    }

    private suspend fun updateSectionDatabase(subjectList: List<Int>) {
        subjectList.forEach {
            val sectionsRemote =
                api.getSectionBySubject(it).map { m -> SectionRemoteToDbMapper.map(m) }
            val sectionsLocal = db.sectionDao().getAllBySubjectIdSuspend(it)

            val sectionsToAdd =
                sectionsRemote.filter { remote -> sectionsLocal.find { local -> local.id == remote.id } == null }

            val sectionsToUpdate =
                sectionsRemote.filter { remote ->
                    sectionsLocal.find { local -> local.id == remote.id }
                        ?.let { tL -> remote.updatedAt != tL.updatedAt } ?: false
                }

            val sectionIdChangeList = sectionsToAdd.toMutableList()
                .apply { addAll(sectionsToUpdate) }.map { m -> m.id }.toList()

            Timber.i("Section update")
            Timber.i(sectionsToAdd.toString())
            Timber.i(sectionsToUpdate.toString())

            updateMaterialDatabase(sectionIdChangeList)
            updateTaskDatabase(sectionIdChangeList)

            db.sectionDao().insertAll(sectionsToAdd)
            db.sectionDao().updateAll(sectionsToUpdate)
        }
    }

    private suspend fun updateMaterialDatabase(sectionList: List<Int>) {
        sectionList.forEach {
            val materialsRemote =
                api.getMaterialBySection(it).map { m -> MaterialRemoteToDbMapper.map(m) }
            val materialsLocal = db.materialDao().getAllBySectionIdSuspend(it)

            val materialsToAdd =
                materialsRemote.filter { remote -> materialsLocal.find { local -> local.id == remote.id } == null }

            val materialsToUpdate =
                materialsRemote.filter { remote ->
                    materialsLocal.find { local -> local.id == remote.id }
                        ?.let { tL -> remote.updatedAt != tL.updatedAt } ?: false
                }

            Timber.i("Material update")
            Timber.i(materialsToAdd.toString())
            Timber.i(materialsToUpdate.toString())

            db.materialDao().insertAll(materialsToAdd)
            db.materialDao().updateAll(materialsToUpdate)
        }
    }

    private suspend fun updateTaskDatabase(sectionList: List<Int>) {
        sectionList.forEach {
            val tasksRemote =
                api.getTaskBySection(it).map { m -> TaskRemoteToDbMapper.map(m) }
            val tasksLocal = db.taskDao().getAllBySectionIdSuspend(it)

            val tasksToAdd =
                tasksRemote.filter { remote -> tasksLocal.find { local -> local.id == remote.id } == null }

            val tasksToUpdate =
                tasksRemote.filter { remote ->
                    tasksLocal.find { local -> local.id == remote.id }
                        ?.let { tL -> remote.updatedAt != tL.updatedAt } ?: false
                }


            Timber.i("Task update")
            Timber.i(tasksToAdd.toString())
            Timber.i(tasksToUpdate.toString())

            db.taskDao().insertAll(tasksToAdd)
            db.taskDao().updateAll(tasksToUpdate)
        }
    }

    fun clearAllTables() = db.clearAllTables()

    fun getSubjectList() = db.subjectDao().getAll()

    fun getSectionListBySubjectId(subjectId: Int) = db.sectionDao().getAllBySubjectId(subjectId)

    fun getEncryptedTasks(sectionId: Int) =
        db.taskDao().getAllDecryptedBySectionId(sectionId, false)

    suspend fun updateTask(task: DbTask) = db.taskDao().update(task)

    suspend fun updateSection(section: DbSection) = db.sectionDao().update(section)

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

    private fun getDecryptedTaskListBySectionId(sectionId: Int) =
        db.taskDao().getAllDecryptedBySectionId(sectionId)
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
        getDecryptedTaskListBySectionId(sectionId)
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

        return@combine list
    }

}