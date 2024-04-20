package se.distansakademin.simple_spring_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import se.distansakademin.simple_spring_app.models.Task;
import se.distansakademin.simple_spring_app.repositories.TaskRepository;

import java.util.List;

@Controller
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @GetMapping("/tasks")
    public String listTasks(Model model) {
        model.addAttribute("tasks", (List<Task>) taskRepository.findAll());
        model.addAttribute("task", new Task());
        model.addAttribute("total", countTotal());
        model.addAttribute("active", countActive());
        model.addAttribute("done", countInActive());
        return "tasks"; // Namnet på Thymeleaf-vymallen som ska renderas (ex. tasks.html)
    }

    @PostMapping("/tasks/new")
    public String createTask(@ModelAttribute Task task) {
        if(task.getDescription().isEmpty()) return "";
        task.setDone(false);
        taskRepository.save(task);
        return "redirect:/tasks";
    }

    @PostMapping("/tasks/update/{id}")
    public String updateTask(@PathVariable Long id, @ModelAttribute Task updatedTask) {
        Task task = taskRepository.findById(id).orElse(null);
        if (task != null) {
            task.setDescription(updatedTask.getDescription());
            task.setDone(updatedTask.isDone());
            taskRepository.save(task);
        }
        return "redirect:/tasks";
    }

    @GetMapping("/tasks/delete/{id}")
    public String deleteTask(@PathVariable Long id) {
        taskRepository.deleteById(id);
        return "redirect:/tasks";
    }

    @GetMapping("/tasks/delete-all")
    public String deleteAll() {
        taskRepository.deleteAll();
        return "redirect:/tasks";
    }

    @GetMapping("/tasks/overload")
    public String overload() {

        for (int i = 0; i < 1000; i++) {
            var task = new Task();
            task.setDone(false);
            task.setDescription("Autotask "+ (i+1));
            taskRepository.save(task);
        }

        return "redirect:/tasks";
    }

    @GetMapping("/overload")
    public String overloadFull() {

        for (int i = 0; i < 1000; i++) {
            var task = new Task();
            task.setDone(false);
            task.setDescription("Autotask "+ (i+1));
            taskRepository.save(task);
        }

        return "overload";
    }

    private Integer countTotal() {
        return (int) taskRepository.count();
    }
    private Integer countActive() {
        return (int) taskRepository.findByDone(false).size();
    }
    private Integer countInActive() {
        return (int) taskRepository.findByDone(true).size();
    }

}