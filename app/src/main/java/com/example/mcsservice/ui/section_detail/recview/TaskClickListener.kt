package com.example.mcsservice.ui.section_detail.recview

import com.example.mcsservice.model.database.DbTask

interface TaskClickListener {
    fun onClick(task: DbTask)
}