package com.example.coroutines.demo

import arrow.core.Either
import org.springframework.core.MethodParameter
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.server.ServerHttpRequest
import org.springframework.http.server.ServerHttpResponse
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice

@ControllerAdvice
class EitherResponseInterceptor : ResponseBodyAdvice<Any> {
    override fun supports(returnType: MethodParameter, converterType: Class<out HttpMessageConverter<*>>): Boolean =
        Either::class.java.isAssignableFrom(returnType.parameterType)

    override fun beforeBodyWrite(
        body: Any?,
        returnType: MethodParameter,
        selectedContentType: MediaType,
        selectedConverterType: Class<out HttpMessageConverter<*>>,
        request: ServerHttpRequest,
        response: ServerHttpResponse
    ): Any? =
        body?.let { either ->
            either as Either<*, *>
            when (either) {
                is Either.Left<*> -> ResponseEntity.internalServerError().body(either.value)
                is Either.Right<*> -> ResponseEntity.ok(either.value)
            }
        }
}
