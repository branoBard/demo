package test.Lekarennwm.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;
import test.Lekarennwm.Model.Producer;

@Repository
public interface ProducerRepository extends CrudRepository<Producer, Long> {
    List<Producer> findByName(String name);
}
