package test.Lekarennwm.Api;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import test.Lekarennwm.Service.SupplierService;

@RestController
@RequestMapping("/api")
public class SupplierRestController {
    private final SupplierService supplierService;
    private final ModelMapper modelMapper;

    public SupplierRestController(SupplierService supplierService, ModelMapper modelMapper) {
        this.supplierService = supplierService;
        this.modelMapper = modelMapper;
    }

    @CrossOrigin
    @GetMapping(value = "/suppliers", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<SupplierDTO> getAllSuppliers() {
        return this.supplierService.getAllSuppliers().stream()
                .map(supplier -> modelMapper.map(supplier, SupplierDTO.class)).collect(Collectors.toList());
    }

}
