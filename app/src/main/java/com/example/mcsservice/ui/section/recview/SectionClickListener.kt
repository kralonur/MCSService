package com.example.mcsservice.ui.section.recview

import com.example.mcsservice.model.database.DbSection

interface SectionClickListener {
    fun onClick(section: DbSection)
}