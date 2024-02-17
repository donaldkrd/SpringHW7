package gb.SpringJWT;

import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private PerformerService performerService;
    public Task createTask(Task task){
        task.setDate(String.valueOf(Calendar.getInstance().getTime()));
        return taskRepository.save(task);
    }
    public List<Task> getAllTask(){
        return taskRepository.findAll();
    }
    public Task getTaskById(Long id){
        return taskRepository.findById(id).
                orElseThrow(()-> new ResourceNotFoundException("No task with id: " + id));
    }
    public List<Task> filterByStatus(Task.Status status){
        return taskRepository.findAll().stream()
                .filter(task -> task.getStatus().equals(status))
                .collect(Collectors.toList());
    }
    public Task updateTaskByStatus(Long id, Task task){
        taskRepository.findById(id).get().setStatus(task.getStatus());
        return taskRepository.save(taskRepository.findById(id).get());
    }
    public void deleteTask (Long id) {
        taskRepository.deleteById(id);
    }
    public List<Task> sortById() {
        return taskRepository
                .findAll()
                .stream()
                .sorted(Comparator.comparing(Task::getId))
                .collect(Collectors.toList());
    }
    public Task assignPerformerToTask(Long id, Long performerId){
        Task existingTask = getTaskById(id);
        Performer performer = performerService.findPerformerById(performerId);
        existingTask.getPerformers().add(performer);
        return taskRepository.save(existingTask);
    }
    public Task deassingPerformerToTask(Long id, Long performerId){
        Task existingTask = getTaskById(id);
        existingTask.getPerformers().removeIf(performer -> performer.getId().equals(performerId));
        Performer performer = performerService.findPerformerById(performerId);
        return taskRepository.save(existingTask);
    }
}