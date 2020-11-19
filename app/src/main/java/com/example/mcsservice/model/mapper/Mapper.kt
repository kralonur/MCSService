package com.example.mcsservice.model.mapper

interface Mapper<I, O> {
    fun map(input: I): O
}