package in.ahemanna.tasks.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@ToString
@Document(collection = "tasks")
public class TaskDocument {
    @Id
    private String id;

    @Field("title")
    private String title;

    @Field("description")
    private String description;

    @Field("status")
    private String status;

    @Field("created_at")
    private String createdAt;

    @Field("modified_at")
    private String modifiedAt;
}
