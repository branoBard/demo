package test.Lekarennwm.Api;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import test.Lekarennwm.Service.ProducerService;

@RestController
@RequestMapping("/api")
public class ProducerRestController {
    private final ProducerService producerService;
    private final ModelMapper modelMapper;

    public ProducerRestController(ProducerService producerService, ModelMapper modelMapper) {
        this.producerService = producerService;
        this.modelMapper = modelMapper;
    }

    @CrossOrigin
    @GetMapping(value = "/producers", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProducerDTO> getAllSuppliers() {
        return this.producerService.getAllProducers().stream()
                .map(producer -> modelMapper.map(producer, ProducerDTO.class)).collect(Collectors.toList());
    }
}
