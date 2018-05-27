# JPA 2.1 Features
Just a small repository to test JPA 2 and JPA 2.1 features.
## Converters
JPA converters help to persist complex data types not covered by JPA itself. It's a similar mechanism to f.e. hibernate's types.

See LocaleConverter for a converter that auto-applies to any Entity's field of the designated type (Locale.class). TimeZoneConverter on the other hand does not apply automatically, but needs to be applied via @Convert(converter = TimeZoneConverter.class).
