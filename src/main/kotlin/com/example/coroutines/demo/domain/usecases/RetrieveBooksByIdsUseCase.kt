package com.example.coroutines.demo.domain.usecases

import arrow.core.Either
import arrow.core.continuations.either.eager
import com.example.coroutines.demo.domain.Book
import com.example.coroutines.demo.domain.BookId
import com.example.coroutines.demo.domain.repositories.FindBookByIdRepository
import com.example.coroutines.demo.failures.ApiFailure
import com.example.coroutines.demo.failures.NotFoundErrorApiFailure

interface RetrieveBooksByIdsUseCase {
    operator fun invoke(bookIds: Collection<BookId>): Either<ApiFailure, Collection<Book>>
}

class DefaultRetrieveBooksByIdsUseCase(
    private val findBookByIdRepository: FindBookByIdRepository
) : RetrieveBooksByIdsUseCase {

    override fun invoke(bookIds: Collection<BookId>): Either<ApiFailure, Collection<Book>> = eager {
        bookIds.map { bookId ->
            findBookByIdRepository.findById(bookId).bind()
        }
    }.mapLeft {
        NotFoundErrorApiFailure(
            code = it.code.code,
            message = it.message
        )
    }
}
