package com.example.coroutines.demo.infrastructure.config

import com.example.coroutines.demo.domain.repositories.FindBookByIdRepository
import com.example.coroutines.demo.infrastructure.repositories.DummyFindBookByIdRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RepoConfig {

    @Bean
    fun findBookByIdRepository(): FindBookByIdRepository =
        DummyFindBookByIdRepository()
}
