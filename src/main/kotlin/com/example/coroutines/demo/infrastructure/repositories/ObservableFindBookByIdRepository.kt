package com.example.coroutines.demo.infrastructure.repositories

import arrow.core.Either
import com.example.coroutines.demo.domain.Book
import com.example.coroutines.demo.domain.BookId
import com.example.coroutines.demo.domain.repositories.FindBookByIdFailure
import com.example.coroutines.demo.domain.repositories.FindBookByIdRepository
import java.util.logging.Logger
import kotlin.system.measureTimeMillis

class ObservableFindBookByIdRepository(private val delegate: FindBookByIdRepository) : FindBookByIdRepository {

    private val logger = Logger.getLogger(this::class.java.simpleName)!!

    override suspend fun findById(id: BookId): Either<FindBookByIdFailure, Book> {
        logger.info("Starting to fetch book: $id")

        lateinit var result: Either<FindBookByIdFailure, Book>

        val elapsed = measureTimeMillis {
            result = delegate.findById(id)
        }

        logger.info("Finished fetching book: $id. Elapsed: $elapsed ms")

        return result
    }
}
