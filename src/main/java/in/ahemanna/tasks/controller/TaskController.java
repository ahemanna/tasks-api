package in.ahemanna.tasks.controller;

import in.ahemanna.api.management.api.TasksApi;
import in.ahemanna.api.management.model.ListTasks200Response;
import in.ahemanna.api.management.model.Status;
import in.ahemanna.api.management.model.Task;
import in.ahemanna.tasks.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class TaskController implements TasksApi {
    @Autowired
    TaskService service;

    @Override
    public ResponseEntity<Void> deleteTask(UUID id) {
        service.deleteTask(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<Task> getTask(UUID id) {
        return new ResponseEntity<>(service.getTask(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ListTasks200Response> listTasks(Status status, Integer size, Integer page) {
        return new ResponseEntity<>(service.listTasks(status, size, page), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Task> postTask(Task task) {
        return new ResponseEntity<>(service.postTask(task), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> putTask(UUID id, Task task) {
        service.putTask(id, task);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
