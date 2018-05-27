package mafoe.jpa21.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.TimeZone;

@Converter
public class TimeZoneConverter implements AttributeConverter<TimeZone, String> {

    @Override
    public String convertToDatabaseColumn(TimeZone timeZone) {
        return timeZone.getID();
    }

    @Override
    public TimeZone convertToEntityAttribute(String dbData) {
        return TimeZone.getTimeZone(dbData);
    }
}
