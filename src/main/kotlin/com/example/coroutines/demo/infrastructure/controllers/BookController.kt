package com.example.coroutines.demo.infrastructure.controllers

import arrow.core.Either
import com.example.coroutines.demo.domain.Book
import com.example.coroutines.demo.domain.BookId
import com.example.coroutines.demo.domain.usecases.RetrieveBooksByIdsUseCase
import com.example.coroutines.demo.failures.ApiFailure
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate

@RestController
@RequestMapping("/v1/books", produces = [MediaType.APPLICATION_JSON_VALUE])
class BookController (
    private val retrieveBooksByIds: RetrieveBooksByIdsUseCase
) {
    @GetMapping
    fun getAllBooks(@RequestParam(name = "ids") bookIds: Collection<String>): Either<ApiFailure, BooksResponse> =
        retrieveBooksByIds(bookIds.map { BookId(it) })
            .map { BooksResponse(data = it.map(Book::toResponse)) }
}

data class BooksResponse(
    val data: Collection<BookResponse>,
)

data class BookResponse(
    val id: String,
    val title: String,
    val publicationDate: LocalDate
)

private fun Book.toResponse(): BookResponse = BookResponse(
    id = id.toString(),
    title = title,
    publicationDate = publicationDate
)
