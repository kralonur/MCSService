package com.example.mcsservice.ui.section_detail.recview

import com.example.mcsservice.model.database.DbMaterial

interface MaterialClickListener {
    fun onClick(material: DbMaterial)
}