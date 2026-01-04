package ru.backendpro.entity;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.sql.Timestamp;
import java.time.Clock;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Converter(autoApply = true)
public class ZonedDateTimeConverter implements AttributeConverter<ZonedDateTime, Timestamp> {

    private final ZoneId zoneId;

    ZonedDateTimeConverter(Clock clock){
        zoneId = ZoneId.of(clock.getZone().getId());
    }

    @Override
    public Timestamp convertToDatabaseColumn(ZonedDateTime zonedDateTime) {
        if (zonedDateTime == null) {
            return null;
        }
        return Timestamp.from(zonedDateTime.withZoneSameInstant(zoneId).toInstant());
    }

    @Override
    public ZonedDateTime convertToEntityAttribute(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return ZonedDateTime.ofInstant(timestamp.toInstant(), zoneId);
    }
}