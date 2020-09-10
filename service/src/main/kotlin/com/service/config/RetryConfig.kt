package com.service.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.retry.annotation.EnableRetry
import org.springframework.retry.support.RetryTemplate

@Configuration
@EnableRetry
class RetryConfig(private val revelRetryPolicy: RevelRetryPolicy) {

    private object RetryAttempts {
        const val TRANSIENT_DATA_ACCESS = 3
        const val QUEUE_DELETED_RECENTLY = 11
        const val AMAZON_S3 = 5
    }

    @Bean
    fun retryTemplate(): RetryTemplate {
        val retryTemplate = RetryTemplate()
        retryTemplate.setBackOffPolicy(revelRetryPolicy.createBackOffPolicy())
        retryTemplate.setRetryPolicy(
            revelRetryPolicy.createMultiplePolicies(
                mapOf()
            )
        )

        return retryTemplate
    }
}
