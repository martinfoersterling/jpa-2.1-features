package mafoe.jpa21.model;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

@Transactional(Transactional.TxType.MANDATORY)
public interface CountryRepository extends JpaRepository<Country, Long> {

    List<Country> findByLocale(Locale locale);

    List<Country> findByTimeZone(TimeZone timeZone);
}
