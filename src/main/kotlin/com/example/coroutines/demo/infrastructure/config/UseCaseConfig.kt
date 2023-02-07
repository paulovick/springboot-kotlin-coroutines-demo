package com.example.coroutines.demo.infrastructure.config

import com.example.coroutines.demo.domain.repositories.FindBookByIdRepository
import com.example.coroutines.demo.domain.usecases.DefaultRetrieveBooksByIdsUseCase
import com.example.coroutines.demo.domain.usecases.ObservableRetrieveBooksByIdsUseCase
import com.example.coroutines.demo.domain.usecases.RetrieveBooksByIdsUseCase
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class UseCaseConfig {

    @Bean
    fun retrieveBooksByIdsUseCase(
        findBookByIdRepository: FindBookByIdRepository
    ): RetrieveBooksByIdsUseCase =
        ObservableRetrieveBooksByIdsUseCase(
            DefaultRetrieveBooksByIdsUseCase(findBookByIdRepository)
        )
}
