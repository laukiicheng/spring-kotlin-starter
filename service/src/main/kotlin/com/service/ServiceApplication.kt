package com.service

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan(basePackages = ["com.revelhealth"])
class ServiceApplication
@Suppress("SpreadOperator")
fun main(args: Array<String>) {
    runApplication<ServiceApplication>(*args)
}
