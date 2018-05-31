# JPA 2.1 Features [![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0) [![CircleCI](https://circleci.com/gh/martinfoersterling/jpa-2.1-features/tree/master.svg?style=svg)](https://circleci.com/gh/martinfoersterling/jpa-2.1-features/tree/master)
Just a small repository to test JPA 2 and JPA 2.1 features.

## Materials

https://en.wikibooks.org/wiki/Java_Persistence/What_is_new_in_JPA_2.1%3F
https://www.thoughts-on-java.org/jpa-21-overview/

## Converters
JPA converters help to persist complex data types not covered by JPA itself. It's a similar mechanism to f.e. hibernate's types.

See mafoe.jpa21.converters.LocaleConverter for a converter that auto-applies to any Entity's field of the designated type (Locale.class). mafoe.jpa21.converters.TimeZoneConverter on the other hand does not apply automatically, but needs to be applied via @Convert(converter = TimeZoneConverter.class).

## Criteria Update/Delete
Bulk updates and deletes using criteria API.

See mafoe.jpa21.criteria_update.CriteriaUpdateTest and mafoe.jpa21.criteria_delete.CriteriaDeleteTest.

## Stored procedures
Call stored procedures and functions either dynamically or via an annotation query on an entity.

See mafoe.jpa21.stored_procedures.StoredProcedureTest.

