package com.example.mcsservice.model.mapper

import com.example.mcsservice.model.Task
import com.example.mcsservice.model.database.DbTask

object TaskRemoteToDbMapper : Mapper<Task, DbTask> {
    override fun map(input: Task): DbTask {
        return DbTask(
            input.id,
            input.updatedAt,
            input.name,
            input.description,
            input.difficulty,
            input.sectionId,
            input.taskTypeId,
            false
        )
    }
}