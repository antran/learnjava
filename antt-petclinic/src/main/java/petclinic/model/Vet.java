package petclinic.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

/**
 * Created by antt on 6/27/15.
 */
@Entity
@Table(name = "vets")
public class Vet extends Person {

    @ManyToMany
    @JoinTable(name = "vet_specialties", joinColumns = @JoinColumn(name = "vet_id"),
        inverseJoinColumns = @JoinColumn(name = "specialty_id"))
    private Set<Specialty> specialties;
}
