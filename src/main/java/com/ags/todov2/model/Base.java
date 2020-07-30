package com.ags.todov2.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

public class Base {

    @Id
    @Getter
    @Setter
    private String id;
}
