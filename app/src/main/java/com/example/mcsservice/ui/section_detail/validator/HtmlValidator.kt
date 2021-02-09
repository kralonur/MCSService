package com.example.mcsservice.ui.section_detail.validator

import java.util.regex.Pattern

class HtmlValidator : Validator {
    private val pattern = Pattern.compile("<[a-z][\\s\\S]*>.*")

    override fun isValid(desc: String): Boolean {
        val matcher = pattern.matcher(desc)
        return matcher.matches()
    }
}