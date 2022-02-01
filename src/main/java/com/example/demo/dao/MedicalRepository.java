package test.Lekarennwm.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import test.Lekarennwm.Model.Medical;

import java.util.List;

@Repository
public interface MedicalRepository extends CrudRepository <Medical, Long> {
    List<Medical> findByName(String name);
    List<Medical> findByNameContaining(String name); /*//aj na začiatku aj na konci aj v strede, klučove slova*/
}
