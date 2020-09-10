package com.service.config

import mu.KLogger
import mu.KotlinLogging
import org.springframework.beans.factory.InjectionPoint
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Scope

@Configuration
class KLoggingConfig {
    @Bean
    @Scope("prototype")
    fun kLogger(injectionPoint: InjectionPoint): KLogger {
        return KotlinLogging.logger(injectionPoint.methodParameter!!.containingClass.name)
    }
}
