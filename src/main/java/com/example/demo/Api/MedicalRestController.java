package test.Lekarennwm.Api;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import test.Lekarennwm.Exeption.ItemNotFoundException;
import test.Lekarennwm.Model.Medical;
import test.Lekarennwm.Service.CategoryService;
import test.Lekarennwm.Service.MedicalService;
import test.Lekarennwm.Service.ProducerService;
import test.Lekarennwm.Service.SupplierService;

@RestController
@RequestMapping("/api")
public class MedicalRestController {
    private final MedicalService medicalService;
    private final SupplierService supplierService;
    private final ProducerService producerService;
    private final CategoryService categoryService;
    private final ModelMapper modelMapper;

    public MedicalRestController(MedicalService medicalService, SupplierService supplierService,
            ProducerService producerService, CategoryService categoryService, ModelMapper modelMapper) {
        this.medicalService = medicalService;
        this.supplierService = supplierService;
        this.producerService = producerService;
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
    }

    @CrossOrigin
    @GetMapping(value = "/medicals", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MedicalDTO> getAllMedicals(@RequestParam(required = false) String name) {
        if (name != null && !name.isEmpty() && !name.trim().isEmpty()) {
            return medicalService.getMedicalsByName(name).stream().map(medical -> modelMapper.map(medical, MedicalDTO.class))
            .collect(Collectors.toList());
        }
        return medicalService.getAllMedicals().stream().map(medical -> modelMapper.map(medical, MedicalDTO.class))
                .collect(Collectors.toList());
    }

    @CrossOrigin
    @PostMapping(value = "/medicals", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createMedical(@RequestBody CreateMedicalDTO medicalDTO) {
        this.modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        Medical medical = this.modelMapper.map(medicalDTO, Medical.class);
        try {
            medical.setProducer(this.producerService.getProducerById(medicalDTO.producer));
            medical.setSupplier(this.supplierService.getSupplierById(medicalDTO.supplier));
            medical.getCategories().add(this.categoryService.getCategoryById(medicalDTO.category));
            medicalService.saveMedical(medical);
        } catch (ItemNotFoundException e) {
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<String>(HttpStatus.CREATED);
    }

    @CrossOrigin
    @PutMapping(value = "/medicals/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> put(@PathVariable Long id, @RequestBody CreateMedicalDTO medicalDTO) throws ItemNotFoundException {
        Medical medical = this.medicalService.getMedicalById(id);
        medical.setName(medicalDTO.name);
        medical.setDescription(medicalDTO.description);
        medical.setPrice(medicalDTO.price);
        medical.setProducer(this.producerService.getProducerById(medicalDTO.producer));
        medical.setSupplier(this.supplierService.getSupplierById(medicalDTO.supplier));
        medicalService.saveMedical(medical);
        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @CrossOrigin
    @DeleteMapping(value = "/medicals/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) throws ItemNotFoundException {
       medicalService.deleteMedical(id);
        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping(value = "/medicals/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public MedicalDTO getMedicalById(@PathVariable Long id) throws ItemNotFoundException {
        return modelMapper.map(medicalService.getMedicalById(id), MedicalDTO.class);
    }
}
