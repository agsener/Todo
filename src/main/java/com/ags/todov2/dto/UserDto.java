package com.ags.todov2.dto;

import lombok.Data;

@Data
public class UserDto {
    
    private String username;
    
    private String name;
    
    private String surname;

    private String base64;
}
