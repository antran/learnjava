package petclinic.service;

import petclinic.model.Vet;

import java.util.Collection;

/**
 * Created by antt on 6/27/15.
 */
public interface ClinicService {
    Collection<Vet> findVets();
}
