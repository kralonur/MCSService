package com.example.mcsservice.model.mapper

import com.example.mcsservice.model.Subject
import com.example.mcsservice.model.database.DbSubject

object SubjectRemoteToDbMapper : Mapper<Subject, DbSubject> {
    override fun map(input: Subject): DbSubject {
        return DbSubject(
            input.id,
            input.updatedAt,
            input.name,
            input.description,
            input.teachers
        )
    }
}