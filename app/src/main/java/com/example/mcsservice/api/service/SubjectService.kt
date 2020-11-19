package com.example.mcsservice.api.service

import com.example.mcsservice.model.Material
import com.example.mcsservice.model.Section
import com.example.mcsservice.model.Subject
import com.example.mcsservice.model.Task
import com.example.mcsservice.util.Constants
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SubjectService {

    @GET(Constants.Api.ALL_SUBJECTS)
    suspend fun getAllSubjects(): List<Subject>

    @GET(Constants.Api.ALL_SUBJECTS)
    suspend fun getAllSections(): List<Section>

    @GET(Constants.Api.ALL_SUBJECTS)
    suspend fun getAllMaterials(): List<Material>

    @GET("${Constants.Api.SECTIONS_BY_SUBJECT}/{subjectId}")
    suspend fun getSectionBySubject(
        @Path("subjectId") subjectId: Int
    ): List<Section>

    @GET("${Constants.Api.TASKS_BY_SECTION}/{sectionId}")
    suspend fun getTaskBySection(
        @Path("sectionId") sectionId: Int,
        @Query("password") password: String = "random"
    ): List<Task>

    @GET("${Constants.Api.MATERIALS_BY_SECTION}/{sectionId}")
    suspend fun getMaterialBySection(
        @Path("sectionId") sectionId: Int
    ): List<Material>

}