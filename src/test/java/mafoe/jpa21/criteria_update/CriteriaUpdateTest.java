package mafoe.jpa21.criteria_update;

import mafoe.jpa21.model.Country;
import mafoe.jpa21.model.CountryRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
public class CriteriaUpdateTest {

    @Autowired
    private CountryRepository repo;
    @PersistenceContext
    private EntityManager em;

    @Before
    public void setUp() throws Exception {

        repo.save(new Country("Germany", Locale.GERMAN, TimeZone.getTimeZone("CET")));
        repo.save(new Country("Italy", Locale.ITALIAN, TimeZone.getTimeZone("CET")));
        repo.save(new Country("France", Locale.FRENCH, TimeZone.getTimeZone("GMT")));
        repo.save(new Country("Spain", new Locale("es"), TimeZone.getTimeZone("GMT")));
        repo.save(new Country("Japan", Locale.JAPANESE, TimeZone.getTimeZone("Japan")));

        List<Country> countriesWithJapaneseLocale = repo.findByLocale(Locale.JAPANESE);
        assertEquals(1, countriesWithJapaneseLocale.size());
    }

    @Test
    public void testUpdate() {

        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();

        CriteriaUpdate<Country> update = criteriaBuilder.createCriteriaUpdate(Country.class);
        Root<Country> root = update.from(Country.class);
        update.set("locale", Locale.JAPANESE);
        update.where(criteriaBuilder.equal(root.get("timeZone"), TimeZone.getTimeZone("GMT")));
        Query query = em.createQuery(update);
        int rowCount = query.executeUpdate();

        assertEquals(2, rowCount);

        List<Country> countriesWithJapaneseLocale = repo.findByLocale(Locale.JAPANESE);
        assertEquals(3, countriesWithJapaneseLocale.size());

    }
}