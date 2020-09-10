package com.service

import com.service.utils.UtilityHelper
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.booleans.shouldBeTrue
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.TestPropertySource
import org.springframework.util.ResourceUtils

@SpringBootTest
@ContextConfiguration(classes = [ServiceApplication::class])
@TestPropertySource("${ResourceUtils.CLASSPATH_URL_PREFIX}${UtilityHelper.testPropertiesFilePath}")
class ServiceApplicationIT : StringSpec() {
    init {
        "Integration Tests" {
            true.shouldBeTrue()
        }
    }
}
