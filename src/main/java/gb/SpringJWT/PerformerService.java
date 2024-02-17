package gb.SpringJWT;

import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class PerformerService {
    //Добавил репозиторий
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private PerformerRepository performerRepository;
    public List<Performer> findAll() {
        return performerRepository.findAll();
    }
    public Performer findPerformerById(Long id) {
        return performerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Performer not found"));
    }
    public Performer save(Performer performer) {
        return performerRepository.save(performer);
    }
    public Performer updatePerformerName(Long id, Performer performer) {
        Performer newPerformer = findPerformerById(id);
        newPerformer.setName(performer.getName());
        return save(newPerformer);
    }
    public void deletePerformer(Long id) {
        findPerformerById(id);
        performerRepository.findAll().remove(id);
    }
    public void clearListPerformer() {
        performerRepository.deleteAll();
    }
}