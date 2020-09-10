package com.service.config.mongo

import org.springframework.core.convert.converter.Converter
import org.springframework.data.convert.WritingConverter
import java.time.OffsetDateTime
import java.util.*

@WritingConverter
class OffsetDateTimeToDateConverter : Converter<OffsetDateTime, Date> {
    override fun convert(source: OffsetDateTime): Date {
        return Date.from(source.toInstant())
    }
}
