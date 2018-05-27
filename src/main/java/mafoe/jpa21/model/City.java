package mafoe.jpa21.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
public class City extends SuperEntity {

    private String name;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Country country;
}
