package test.Lekarennwm.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;
import test.Lekarennwm.Model.Supplier;

@Repository
public interface SupplierRepository extends CrudRepository<Supplier, Long> {
    List<Supplier> findByName(String name);
}
