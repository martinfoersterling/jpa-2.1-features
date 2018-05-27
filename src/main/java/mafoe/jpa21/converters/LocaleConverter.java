package mafoe.jpa21.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Locale;

@Converter(autoApply = true)
public class LocaleConverter implements AttributeConverter<Locale, String> {

    @Override
    public String convertToDatabaseColumn(Locale locale) {
        return locale.getLanguage();
    }

    @Override
    public Locale convertToEntityAttribute(String dbData) {
        return new Locale(dbData);
    }
}