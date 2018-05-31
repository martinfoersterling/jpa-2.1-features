package mafoe.jpa21.model;

import mafoe.jpa21.converters.TimeZoneConverter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;
import java.util.TimeZone;

@NamedStoredProcedureQuery(
        name = "Country.all",
        resultClasses = Country.class,
        procedureName = "get_countries")
@Entity
public class Country extends SuperEntity {

    @NotNull
    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "country")
    private Collection<City> cities = new ArrayList<>();

    //automatically applied converter
    @NotNull
    @Column(nullable = false)
    private Locale locale;

    //explicitly applied converter
    @Convert(converter = TimeZoneConverter.class)
    @NotNull
    @Column(nullable = false)
    private TimeZone timeZone;

    public Country() {
    }

    public Country(String name, Locale locale, TimeZone timeZone) {
        this.name = name;
        this.locale = locale;
        this.timeZone = timeZone;
    }

    public TimeZone getTimeZone() {
        return timeZone;
    }

    public String getName() {
        return name;
    }

    public Collection<City> getCities() {
        return cities;
    }

    public Locale getLocale() {
        return locale;
    }
}
