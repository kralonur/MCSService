package com.example.mcsservice.ui.section_detail.validator

import java.util.regex.Pattern

class HtmlValidator : Validator {
    override fun isValid(desc: String) =
        Pattern.compile("<[a-z][\\s\\S]*>.*").matcher(desc).matches()

}