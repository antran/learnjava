package petclinic.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Collection;

/**
 * Created by antt on 6/27/15.
 */
@Entity
@Table(name = "vets")
public class Vet extends Person {

    private Collection<Specialty> specialties;
}
