package gb.SpringJWT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
public class TaskController {
    @Autowired
    private final TaskService taskService;
    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }
    @Autowired
    private PerformerService performerService;
    @GetMapping("/login")
    public String loginEndpoint(){
        return "You need enter login and password";
    }
    @GetMapping("/tasks")
    public List<Task> getAllTask() {
        return taskService.getAllTask();
    }
    @PostMapping("/tasks")
    public Task createTask(@RequestBody Task task) {
        return taskService.createTask(task);
    }
    @GetMapping("/tasks/{id}")
    public Task getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }
    @GetMapping("/tasks/filter/{status}")
    public List<Task> filterByStatus(@PathVariable Task.Status status) {
        return taskService.filterByStatus(status);
    }
    @GetMapping("/tasks/sort")
    public List<Task> sortById() {
        return taskService.sortById();
    }
    @PutMapping("/updateStatus/{id}")
    public Task updateTaskByStatus(@PathVariable Long id, @RequestBody Task task) {
        return taskService.updateTaskByStatus(id, task);
    }
    @DeleteMapping("tasks/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }
    @PutMapping("/tasks/{id}/performers/{performerId}")
    public Task assignPerformerToTask(@PathVariable Long id, @PathVariable Long performerId) {
        return taskService.assignPerformerToTask(id, performerId);
    }
    @DeleteMapping("/tasks/{id}/performers/{performerId}")
    public Task deassignPerformerToTask(@PathVariable Long id, @PathVariable Long performerId){
        return taskService.deassingPerformerToTask(id, performerId);
    }
    @GetMapping("/performers")
    public List<Performer> findAll(){
        return performerService.findAll();
    }
    @PostMapping("/performers")
    public Performer save(@RequestBody Performer performer){
        return performerService.save(performer);
    }
    @GetMapping("/performers/{id}")
    public Performer findPerformerById(@PathVariable Long id){
        return performerService.findPerformerById(id);
    }
    @PutMapping("/performers/{id}")
    public Performer updatePerformerById(@PathVariable Long id, @RequestBody Performer performer){
        return performerService.updatePerformerName(id, performer);
    }
    @DeleteMapping("/performers/{id}")
    public void deletePerformerById (@PathVariable Long id){
        performerService.deletePerformer(id);
    }
    @DeleteMapping("/performersDeleteAll")
    public void deletePerformersAll(){
        performerService.clearListPerformer();
    }
}