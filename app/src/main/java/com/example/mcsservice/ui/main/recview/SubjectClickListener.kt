package com.example.mcsservice.ui.main.recview

import com.example.mcsservice.model.database.DbSubject

interface SubjectClickListener {
    fun onClick(subject: DbSubject)
}