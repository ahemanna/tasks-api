package in.ahemanna.tasks.repository;

import in.ahemanna.tasks.entity.TaskDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends MongoRepository<TaskDocument, String> {
}
