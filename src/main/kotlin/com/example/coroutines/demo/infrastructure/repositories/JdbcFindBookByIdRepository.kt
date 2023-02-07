package com.example.coroutines.demo.infrastructure.repositories

import arrow.core.Either
import com.example.coroutines.demo.domain.Book
import com.example.coroutines.demo.domain.BookId
import com.example.coroutines.demo.domain.repositories.FindBookByIdFailure
import com.example.coroutines.demo.domain.repositories.FindBookByIdRepository
import org.springframework.jdbc.core.JdbcTemplate

class JdbcFindBookByIdRepository (
    private val jdbcTemplate: JdbcTemplate
) : FindBookByIdRepository {
    override suspend fun findById(id: BookId): Either<FindBookByIdFailure, Book> =
        jdbcTemplate.queryForObject(
            "SELECT * FROM book WHERE id = '$id';"
        ) { resultSet, _ ->
            Book(
                id = BookId(resultSet.getString("id")),
                title = resultSet.getString("title"),
                publicationDate = resultSet.getDate("publication_date").toLocalDate()
            )
        }?.let { book -> Either.Right(book) } ?: Either.Left(FindBookByIdFailure.notFound(id))
}
