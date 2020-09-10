package com.service.config.mongo

import org.springframework.core.convert.converter.Converter
import org.springframework.data.convert.ReadingConverter
import java.time.OffsetDateTime
import java.time.ZoneOffset
import java.util.*

@ReadingConverter
class DateToOffsetDateTimeConverter : Converter<Date, OffsetDateTime> {
    override fun convert(source: Date): OffsetDateTime {
        return OffsetDateTime.ofInstant(source.toInstant(), ZoneOffset.UTC)
    }
}
