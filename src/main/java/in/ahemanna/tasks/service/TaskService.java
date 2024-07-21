package in.ahemanna.tasks.service;

import in.ahemanna.api.management.model.ListTasks200Response;
import in.ahemanna.api.management.model.Status;
import in.ahemanna.api.management.model.Task;
import in.ahemanna.tasks.entity.TaskDocument;
import in.ahemanna.tasks.repository.TaskRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.query.UntypedExampleMatcher;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class TaskService {
    @Autowired
    TaskRepository repository;

    @Autowired
    ModelMapper mapper;

    public void deleteTask(UUID id) {
        repository.deleteById(id.toString());
    }

    public Task getTask(UUID id) {
        TaskDocument document = repository.findById(id.toString()).orElse(null);
        if(document == null) {
            throw new RuntimeException("RESOURCE_NOT_FOUND");
        }

        return mapper.map(document, Task.class);
    }

    public ListTasks200Response listTasks(Status status, Integer size, Integer page) {
        TaskDocument filter = new TaskDocument();
        if (status != null) {
            filter.setStatus(status.toString());
        }

        ExampleMatcher matcher = UntypedExampleMatcher.matching().withIgnoreNullValues();
        Example<TaskDocument> probe = Example.of(filter, matcher);
        Page<TaskDocument> taskDocuments = repository.findAll(probe, PageRequest.of(page - 1, size));

        ListTasks200Response response = new ListTasks200Response();
        List<Task> tasks = taskDocuments.map(task -> mapper.map(task, Task.class)).toList();
        response.setItems(tasks);
        response.setTotalItems(taskDocuments.getNumberOfElements());
        response.setTotalPages(taskDocuments.getTotalPages());

        return response;
    }

    public Task postTask(Task task) {
        task.setId(UUID.randomUUID());
        task.setCreatedAt(OffsetDateTime.now());
        task.setModifiedAt(OffsetDateTime.now());
        repository.insert(mapper.map(task, TaskDocument.class));

        return task;
    }

    public void putTask(UUID id, Task task) {
        TaskDocument document = repository.findById(id.toString()).orElse(null);
        if(document == null) {
            throw new RuntimeException("RESOURCE_NOT_FOUND");
        }
        document.setTitle(task.getTitle());
        document.setDescription(task.getDescription());
        document.setStatus(task.getStatus().toString());
        document.setModifiedAt(OffsetDateTime.now().toString());

        repository.save(document);
    }
}
