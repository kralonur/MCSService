package com.example.mcsservice.model.mapper

import com.example.mcsservice.model.DomainSection
import com.example.mcsservice.model.database.DbSection

object SectionDbToDomainMapper : Mapper<DbSection, DomainSection> {
    override fun map(input: DbSection): DomainSection {
        return DomainSection(
            input.id,
            input.updatedAt,
            input.name,
            input.description,
            input.subjectId,
            input.sectionTypeId,
            input.sectionPass,
            false
        )
    }
}