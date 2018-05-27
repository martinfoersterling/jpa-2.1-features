package mafoe.jpa21.criteria_delete;

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
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
public class CriteriaDeleteTest {

    @Autowired
    private CountryRepository repo;
    @PersistenceContext
    private EntityManager em;

    private TimeZone cet = TimeZone.getTimeZone("CET");

    @Before
    public void setUp() throws Exception {

        repo.save(new Country("Germany", Locale.GERMAN, cet));
        repo.save(new Country("Italy", Locale.ITALIAN, cet));
        repo.save(new Country("France", Locale.FRENCH, TimeZone.getTimeZone("GMT")));
        repo.save(new Country("Spain", new Locale("es"), TimeZone.getTimeZone("GMT")));
        repo.save(new Country("Japan", Locale.JAPANESE, TimeZone.getTimeZone("Japan")));

        List<Country> countriesWithCet = repo.findByTimeZone(cet);
        assertEquals(2, countriesWithCet.size());
    }

    @Test
    public void testUpdate() {

        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();

        CriteriaDelete<Country> delete = criteriaBuilder.createCriteriaDelete(Country.class);
        Root<Country> root = delete.from(Country.class);
        delete.where(criteriaBuilder.equal(root.get("timeZone"), cet));
        Query query = em.createQuery(delete);
        int rowCount = query.executeUpdate();

        assertEquals(2, rowCount);

        List<Country> countriesWithCet = repo.findByTimeZone(cet);
        assertEquals(0, countriesWithCet.size());
    }
}