package com.example.coroutines.demo.domain.usecases

import arrow.core.Either
import arrow.core.continuations.either.eager
import com.example.coroutines.demo.domain.Book
import com.example.coroutines.demo.domain.BookId
import com.example.coroutines.demo.domain.repositories.FindBookByIdRepository
import com.example.coroutines.demo.failures.ApiFailure
import com.example.coroutines.demo.failures.NotFoundErrorApiFailure
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking

interface RetrieveBooksByIdsUseCase {
    operator fun invoke(bookIds: Collection<BookId>): Either<ApiFailure, Collection<Book>>
}

class DefaultRetrieveBooksByIdsUseCase(
    private val findBookByIdRepository: FindBookByIdRepository
) : RetrieveBooksByIdsUseCase {

    override fun invoke(bookIds: Collection<BookId>): Either<ApiFailure, Collection<Book>> {
        val books = runBlocking {
            bookIds.map {
                async { findBookByIdRepository.findById(it) }
            }.awaitAll()
        }

        return eager {
            books.map { it.bind() }
        }.mapLeft {
            NotFoundErrorApiFailure(
                code = it.code.code,
                message = it.message
            )
        }
    }
}
