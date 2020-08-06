package com.ags.todov2.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "Task")
@TypeAlias("Task")
@Accessors(chain = true)
public class Task extends Base {

    @Getter
    @Setter
    private String title;

    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    private boolean done;

    @Getter
    @Setter
    private Date createdDate;

    @Getter
    @Setter
    private Date updatedDate;

    @Getter
    @Setter
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date dueDate;

    @Indexed
    @Getter
    @Setter
    private String user;
}
