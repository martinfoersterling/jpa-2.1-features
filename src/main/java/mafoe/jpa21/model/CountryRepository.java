package mafoe.jpa21.model;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional(Transactional.TxType.MANDATORY)
public interface CountryRepository extends JpaRepository<Country, Long> {
}
