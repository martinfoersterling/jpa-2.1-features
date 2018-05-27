package mafoe.jpa21.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class SuperEntity {

    @GeneratedValue
    @Id
    private Long id;

    public Long getId() {
        return id;
    }
}
