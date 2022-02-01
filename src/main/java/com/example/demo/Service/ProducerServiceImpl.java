package test.Lekarennwm.Service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import test.Lekarennwm.Exeption.ItemNotFoundException;
import test.Lekarennwm.Model.Producer;
import test.Lekarennwm.dao.ProducerRepository;

import java.util.List;

@Service
@Transactional
public class ProducerServiceImpl implements ProducerService {

    private ProducerRepository producerRepository;

    public ProducerServiceImpl(ProducerRepository producerRepository) {
        this.producerRepository = producerRepository;
    }

    @Override
    @Transactional
    public List<Producer> getAllProducers() {
        return (List<Producer>) this.producerRepository.findAll();
    }

    @Override
    public Producer getProducerById(Long id) throws ItemNotFoundException {
        Producer producer = this.producerRepository.findById(id).orElse(null);
        if(producer == null){
            throw new ItemNotFoundException("Producer with id=" + id+ " is not found!");
        }
        return producer;
    }

    @Override
    public Producer saveProducer(Producer producer) {
        this.producerRepository.save(producer);
        return producer;
    }

    @Override
    public Producer editProducer(Producer producer) {
        return null;
    }

    @Override
    public void deleteProducer(Long id) throws ItemNotFoundException {
        this.producerRepository.deleteById(id);
    }
}
