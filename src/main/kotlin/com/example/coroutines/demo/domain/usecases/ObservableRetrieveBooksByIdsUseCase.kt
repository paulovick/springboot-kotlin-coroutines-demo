package com.example.coroutines.demo.domain.usecases

import arrow.core.Either
import com.example.coroutines.demo.domain.Book
import com.example.coroutines.demo.domain.BookId
import com.example.coroutines.demo.failures.ApiFailure
import java.util.logging.Logger
import kotlin.system.measureTimeMillis

class ObservableRetrieveBooksByIdsUseCase(private val delegate: RetrieveBooksByIdsUseCase)
    : RetrieveBooksByIdsUseCase
{

    private val logger = Logger.getLogger(this::class.java.simpleName)!!

    override fun invoke(bookIds: Collection<BookId>): Either<ApiFailure, Collection<Book>> {
        logger.info("Starting to fetch books: [${bookIds.joinToString(", ")}]")

        lateinit var result: Either<ApiFailure, Collection<Book>>

        val elapsed = measureTimeMillis {
            result = delegate(bookIds)
        }

        logger.info("Finished fetching books: [${bookIds.joinToString(", ")}]. Elapsed: $elapsed ms")

        return result
    }
}