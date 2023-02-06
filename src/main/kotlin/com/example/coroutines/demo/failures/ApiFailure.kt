package com.example.coroutines.demo.failures

sealed class ApiFailure {
    abstract val code: String
    abstract val message: String
}

data class InternalServerErrorApiFailure(
    override val code: String,
    override val message: String
) : ApiFailure()

data class NotFoundErrorApiFailure(
    override val code: String,
    override val message: String
) : ApiFailure()
