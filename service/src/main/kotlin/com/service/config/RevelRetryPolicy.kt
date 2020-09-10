package com.service.config

import org.springframework.retry.RetryPolicy
import org.springframework.retry.backoff.ExponentialBackOffPolicy
import org.springframework.retry.policy.ExceptionClassifierRetryPolicy
import org.springframework.retry.policy.SimpleRetryPolicy
import org.springframework.stereotype.Component

@Component
class RevelRetryPolicy {
    fun createBackOffPolicy(): ExponentialBackOffPolicy {
        val backOffPolicy = ExponentialBackOffPolicy()
        backOffPolicy.initialInterval = ExponentialBackOffPolicy.DEFAULT_INITIAL_INTERVAL
        backOffPolicy.multiplier = ExponentialBackOffPolicy.DEFAULT_MULTIPLIER

        return backOffPolicy
    }

    fun createMultiplePolicies(retryPolicyMap: Map<Int, Class<out Exception>>): ExceptionClassifierRetryPolicy {
        val policyMap = retryPolicyMap.entries.associate { (retryCount, exceptionClass) ->
            exceptionClass to createSimpleRetryPolicy(retryCount, exceptionClass)
        }

        val multiplePolicies = ExceptionClassifierRetryPolicy()
        multiplePolicies.setPolicyMap(policyMap.toMutableMap<Class<out Throwable>, RetryPolicy>())

        return multiplePolicies
    }

    private fun createSimpleRetryPolicy(
        retryCount: Int = SimpleRetryPolicy.DEFAULT_MAX_ATTEMPTS,
        exceptionClass: Class<out Exception>
    ): SimpleRetryPolicy {
        return SimpleRetryPolicy(
            retryCount,
            mapOf(exceptionClass to true)
        )
    }
}
