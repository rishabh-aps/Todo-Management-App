package com.rishabh.todo.dto;

/*
   DTO stands for Data Transfer Object
   It is a widely used design pattern to transfer the data between client and server
   Here TodoDto entity servers the same purpose.

   The Advantage of using TodoDto class is to de-couple the JPA entity with client

   If we directly return Todo JPA entity to the client, then there is a chance
   that Todo JPA entity may contain sensitive information like password etc.

 */

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/*
    lombok annotation to automatically generate getters, setters and constructors
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TodoDto {

    private Long id;
    private String title;
    private String description;
    private boolean completed;
}
