package com.rishabh.todo.exception;

import lombok.AllArgsConstructor;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Generated
@Setter
@Getter
public class TodoAPIException extends RuntimeException{

    private HttpStatus status;
    private String message;
}
