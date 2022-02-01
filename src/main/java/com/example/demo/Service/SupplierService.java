package test.Lekarennwm.Service;

import test.Lekarennwm.Exeption.ItemNotFoundException;
import test.Lekarennwm.Model.Supplier;

import java.util.List;

public interface SupplierService {
    List<Supplier> getAllSuppliers();

    Supplier getSupplierById(Long id) throws ItemNotFoundException;

    Supplier saveSupplier(Supplier supplier);

    Supplier editSupplier(Supplier supplier);

    void deleteSupplier(Long id) throws ItemNotFoundException;
}
