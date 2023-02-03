package com.example.coroutines.demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CoroutinesDemoApplication

fun main(args: Array<String>) {
	runApplication<CoroutinesDemoApplication>(*args)
}
