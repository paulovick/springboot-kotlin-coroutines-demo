package com.example.coroutines.demo.domain

import java.time.LocalDate

data class Book (
    val id: BookId,
    val title: String,
    val publicationDate: LocalDate
)

@JvmInline value class BookId(private val value: String) {
    override fun toString(): String = value
}
