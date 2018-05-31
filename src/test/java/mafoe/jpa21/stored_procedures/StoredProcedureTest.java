package mafoe.jpa21.stored_procedures;

import mafoe.jpa21.model.Country;
import mafoe.jpa21.model.CountryRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
public class StoredProcedureTest {

    @PersistenceContext
    private EntityManager em;
    @Autowired
    private CountryRepository repo;

    @Before
    public void setUp() throws Exception {

        repo.save(new Country("Germany", Locale.GERMAN, TimeZone.getTimeZone("CET")));
        repo.save(new Country("Italy", Locale.ITALIAN, TimeZone.getTimeZone("CET")));
        repo.save(new Country("France", Locale.FRENCH, TimeZone.getTimeZone("GMT")));
        repo.save(new Country("Spain", new Locale("es"), TimeZone.getTimeZone("GMT")));
        repo.save(new Country("Japan", Locale.JAPANESE, TimeZone.getTimeZone("Japan")));
        repo.flush();
    }

    @Test
    public void testNamedStoredProcedure() {

        StoredProcedureQuery query = em.createNamedStoredProcedureQuery("Country.all");
        List<Country> result = (List) query.getResultList();
        Assert.assertEquals(5, result.size());
    }

    @Test
    public void testStoredProcedure() {

        StoredProcedureQuery query = em.createStoredProcedureQuery("get_magic_number");
        query.registerStoredProcedureParameter("MAGIC", Integer.class, ParameterMode.IN);

        query.setParameter("MAGIC", 13);

        query.execute();
        Integer output = (Integer) query.getSingleResult();

        assertEquals(new Integer(169), output);
    }
}