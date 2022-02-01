package test.Lekarennwm.Service;

import test.Lekarennwm.Exeption.ItemNotFoundException;
import test.Lekarennwm.Model.Producer;

import java.util.List;

public interface ProducerService {
    List<Producer> getAllProducers();

    Producer getProducerById(Long id) throws ItemNotFoundException;

    Producer saveProducer(Producer producer);

    Producer editProducer(Producer producer);

    void deleteProducer(Long id) throws ItemNotFoundException;
}
