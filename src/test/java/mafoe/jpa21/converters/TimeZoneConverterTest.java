package mafoe.jpa21.converters;

import mafoe.jpa21.model.Country;
import mafoe.jpa21.model.CountryRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Locale;
import java.util.Optional;
import java.util.TimeZone;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
public class TimeZoneConverterTest {

    @Autowired
    private CountryRepository repository;

    @Test
    public void converterReadWrite() {

        Country germany = new Country("Germany", Locale.GERMAN, TimeZone.getTimeZone("CET"));
        Long id = repository.save(germany).getId();

        Optional<Country> result = repository.findById(id);
        assertTrue(result.isPresent());
        assertEquals(germany, result.get());
        assertEquals(TimeZone.getTimeZone("CET"), result.get().getTimeZone());
    }
}