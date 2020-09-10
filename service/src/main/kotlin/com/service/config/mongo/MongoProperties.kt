package com.service.config.mongo

import com.mongodb.*
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties("spring.data.mongodb")
class MongoProperties {
    @Value("\${spring.application.name}")
    lateinit var appName: String

    lateinit var uri: MongoClientURI

    var username: String? = null
    var password: String? = null
    var authSource: String = "admin"
}
