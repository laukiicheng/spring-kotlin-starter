package com.service.config.mongo

import com.mongodb.*
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.core.convert.converter.Converter
import org.springframework.data.mongodb.config.AbstractMongoConfiguration
import org.springframework.data.mongodb.core.convert.MongoCustomConversions
import java.util.*

@Configuration
@EnableConfigurationProperties(MongoProperties::class)
class MongoConfig(private val mongoProperties: MongoProperties) : AbstractMongoConfiguration() {

    override fun getDatabaseName(): String {
        return mongoProperties.uri.database ?: mongoProperties.appName
    }

    @Bean
    @Profile("!unitTests")
    override fun mongoClient(): MongoClient {
        return MongoClient(mongoProperties.uri)
    }

    @Bean
    override fun customConversions(): MongoCustomConversions {
        val converterList = ArrayList<Converter<*, *>>()
        converterList.add(DateToOffsetDateTimeConverter())
        converterList.add(OffsetDateTimeToDateConverter())
        return MongoCustomConversions(converterList)
    }
}
