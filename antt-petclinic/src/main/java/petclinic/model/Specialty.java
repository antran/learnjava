package petclinic.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by antt on 6/27/15.
 */
@Entity
@Table(name = "specialties")
public class Specialty extends NamedEntity {
    @Override
    public String toString() {
        return "Specialty " + getName();
    }
}
