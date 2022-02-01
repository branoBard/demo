package test.Lekarennwm.Service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import test.Lekarennwm.Exeption.ItemNotFoundException;
import test.Lekarennwm.Model.Medical;
import test.Lekarennwm.dao.MedicalRepository;

import java.util.List;

@Service
@Transactional
public class MedicalServiceImpl implements MedicalService {

    private MedicalRepository medicalRepository;

    public MedicalServiceImpl(MedicalRepository medicalRepository) {
        this.medicalRepository = medicalRepository;
    }

    @Override
    // @Transactional(readOnly = true)
    public List<Medical> getAllMedicals() {
        return (List<Medical>) this.medicalRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Medical getMedicalById(Long id) throws ItemNotFoundException {
        Medical medical = this.medicalRepository.findById(id).orElse(null);
        if (medical == null) {
            throw new ItemNotFoundException("Drug with id=" + id + " is not found!");
        }
        return medical;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Medical> getMedicalsByName(String name) throws ItemNotFoundException {
        List<Medical> medicals = this.medicalRepository.findByNameContaining(name);
        if (medicals == null) {
            throw new ItemNotFoundException("Medical with name:" + name + " is not found!");
        }
        return medicals;
    }

    @Override
    @Transactional
    public Medical saveMedical(Medical medical) {
        medicalRepository.save(medical);
        return medical;
    }

    @Override
    @Transactional
    public Medical editMedical(Medical medical) {
        return null;
    }

    @Override
    @Transactional
    public void deleteMedical(Long id) throws ItemNotFoundException {
        medicalRepository.deleteById(id);

    }
}
