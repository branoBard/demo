package test.Lekarennwm.Service;

import test.Lekarennwm.Exeption.ItemNotFoundException;
import test.Lekarennwm.Model.Medical;

import java.util.List;

public interface MedicalService {
    List<Medical> getAllMedicals();

    List<Medical> getMedicalsByName(String name) throws ItemNotFoundException;

    Medical getMedicalById(Long id) throws ItemNotFoundException;

    Medical saveMedical(Medical medical);

    Medical editMedical(Medical medical);

    void deleteMedical(Long id) throws ItemNotFoundException;
}
