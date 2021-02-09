package com.example.mcsservice.model.mapper

import com.example.mcsservice.model.Section
import com.example.mcsservice.model.database.DbSection

object SectionRemoteToDbMapper : Mapper<Section, DbSection> {
    override fun map(input: Section): DbSection {
        return DbSection(
            input.id,
            input.updatedAt,
            input.name,
            input.description,
            input.subjectId,
            input.sectionTypeId,
            ""
        )
    }
}