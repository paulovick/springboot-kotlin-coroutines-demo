package com.example.coroutines.demo.domain.repositories

import arrow.core.Either
import com.example.coroutines.demo.domain.Book
import com.example.coroutines.demo.domain.BookId

interface FindBookByIdRepository {
    fun findById(id: BookId): Either<FindBookByIdFailure, Book>
}

data class FindBookByIdFailure(
    val id: BookId,
    val code: FindBookByIdFailureType,
    val message: String
) {
    companion object {

        fun notFound(id: BookId): FindBookByIdFailure =
            FindBookByIdFailure(
                id = id,
                code = FindBookByIdFailureType.NOT_FOUND,
                message = "Book with id $id was not found"
            )

        enum class FindBookByIdFailureType(val code: String) {
            NOT_FOUND("BOOK_NOT_FOUND")
        }
    }
}
