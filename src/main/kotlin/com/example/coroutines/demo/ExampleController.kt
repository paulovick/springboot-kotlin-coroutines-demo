package com.example.coroutines.demo

import arrow.core.Either
import com.example.coroutines.demo.failures.ApiFailure
import com.example.coroutines.demo.failures.InternalServerErrorApiFailure
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/example", produces = [APPLICATION_JSON_VALUE])
class ExampleController {

    @GetMapping
    fun exampleGet(): Either<ApiFailure, Int> =
        Either.Left(InternalServerErrorApiFailure(
            code = "INTERNAL_SERVER_ERROR",
            message = "Something happened :("
        ))

}
