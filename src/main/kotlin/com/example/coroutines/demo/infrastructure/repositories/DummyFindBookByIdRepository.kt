package com.example.coroutines.demo.infrastructure.repositories

import arrow.core.Either
import com.example.coroutines.demo.domain.Book
import com.example.coroutines.demo.domain.BookId
import com.example.coroutines.demo.domain.repositories.FindBookByIdFailure
import com.example.coroutines.demo.domain.repositories.FindBookByIdRepository
import java.time.LocalDate

class DummyFindBookByIdRepository : FindBookByIdRepository {
    override fun findById(id: BookId): Either<FindBookByIdFailure, Book> {
        Thread.sleep(1000)
        return books.firstOrNull { it.id == id }?.let { Either.Right(it) }
            ?: Either.Left(FindBookByIdFailure.notFound(id))
    }

    companion object {
        val books = listOf(
            Book(
                id = BookId("1"),
                title = "Book 1",
                publicationDate = LocalDate.now().minusWeeks(1)
            ),
            Book(
                id = BookId("2"),
                title = "Book 2",
                publicationDate = LocalDate.now().minusWeeks(2)
            ),
            Book(
                id = BookId("3"),
                title = "Book 3",
                publicationDate = LocalDate.now().minusWeeks(3)
            ),
            Book(
                id = BookId("4"),
                title = "Book 4",
                publicationDate = LocalDate.now().minusWeeks(4)
            ),
            Book(
                id = BookId("5"),
                title = "Book 5",
                publicationDate = LocalDate.now().minusWeeks(5)
            )
        )
    }
}
