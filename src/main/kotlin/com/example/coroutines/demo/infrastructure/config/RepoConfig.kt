package com.example.coroutines.demo.infrastructure.config

import com.example.coroutines.demo.domain.repositories.FindBookByIdRepository
import com.example.coroutines.demo.infrastructure.repositories.JdbcFindBookByIdRepository
import com.example.coroutines.demo.infrastructure.repositories.ObservableFindBookByIdRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.core.JdbcTemplate

@Configuration
class RepoConfig {

    @Bean
    fun findBookByIdRepository(jdbcTemplate: JdbcTemplate): FindBookByIdRepository =
        ObservableFindBookByIdRepository(JdbcFindBookByIdRepository(jdbcTemplate))
}
