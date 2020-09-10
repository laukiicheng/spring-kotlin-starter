package com.service

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.booleans.shouldBeTrue

class ServiceApplicationTests : StringSpec() {

    init {
        "Test Some Stuff" {
            true.shouldBeTrue()
        }
    }
}
