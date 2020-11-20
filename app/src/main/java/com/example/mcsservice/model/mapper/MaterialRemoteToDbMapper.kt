package com.example.mcsservice.model.mapper

import com.example.mcsservice.model.Material
import com.example.mcsservice.model.database.DbMaterial

object MaterialRemoteToDbMapper : Mapper<Material, DbMaterial> {
    override fun map(input: Material): DbMaterial {
        return DbMaterial(
            input.id,
            input.updatedAt,
            input.name,
            input.content,
            input.isVisible,
            input.sectionId
        )
    }
}