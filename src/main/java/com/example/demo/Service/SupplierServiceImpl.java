package test.Lekarennwm.Service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import test.Lekarennwm.Exeption.ItemNotFoundException;
import test.Lekarennwm.Model.Supplier;
import test.Lekarennwm.dao.SupplierRepository;

@Service
@Transactional
public class SupplierServiceImpl implements SupplierService {

    private SupplierRepository supplierRepository;

    public SupplierServiceImpl(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Override
    @Transactional
    public List<Supplier> getAllSuppliers() {
        return (List<Supplier>) this.supplierRepository.findAll();
    }

    @Override
    public Supplier getSupplierById(Long id) throws ItemNotFoundException {
        Supplier supplier = this.supplierRepository.findById(id).orElse(null);
        if (supplier == null) {
            throw new ItemNotFoundException("Producer with id=" + id + " is not found!");
        }
        return supplier;
    }

    @Override
    public Supplier saveSupplier(Supplier supplier) {
        this.supplierRepository.save(supplier);
        return supplier;
    }

    @Override
    public Supplier editSupplier(Supplier supplier) {
        return null;
    }

    @Override
    public void deleteSupplier(Long id) throws ItemNotFoundException {
    }
}
