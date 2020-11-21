package com.example.mcsservice.model

import com.example.mcsservice.model.database.DbMaterial
import com.example.mcsservice.model.database.DbTask

data class SectionDetailItem(
    val type: SectionDetailItemType,
    val header: String? = null,
    val material: DbMaterial? = null,
    val task: DbTask? = null,
)