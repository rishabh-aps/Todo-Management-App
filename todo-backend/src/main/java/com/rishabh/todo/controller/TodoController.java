package com.rishabh.todo.controller;

import com.rishabh.todo.dto.TodoDto;
import com.rishabh.todo.service.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
/*
    @RestController annotation makes this class as Spring MVC REST controller
    and within this REST controller, we can define the REST APIs.
 */
@RestController

/*
    @RequestMapping annotation defines the base url for all the REST APIs
    within this TodoController class.
 */
@RequestMapping("api/todos")


/*
    @AllArgsConstructor annotation will automatically create
    a parameterized constructor for this TodoServiceImpl class
 */
@AllArgsConstructor
public class TodoController {

    /*
         Dependency Injection is done by
         constructor based DI using lombok annotation
     */
    private TodoService todoService;

    /*
        addTodo REST API

        ResponseEntity is a Generic class, used to construct the response
        of the REST API

        @PostMapping annotation is used to map incoming HTTP post request to
        this particular method

        @RequestBody annotation extract the JSON object from the HTTP request
        and converts it into TodoDto java class object.
     */

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<TodoDto> addTodo(@RequestBody TodoDto todoDto){
      TodoDto savedTodoDto = todoService.addTodo(todoDto);
      return new ResponseEntity<>(savedTodoDto, HttpStatus.CREATED);
    }

    /*
        getTodo REST API

         @GetMapping("{id}") annotation is used to map incoming HTTP post request to
        this particular method

        "{id}" is called URI template variable.

        @PathVariable("id") annotation binds URI template variable {id} to todoId.
     */

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("{id}")
    public ResponseEntity<TodoDto> getTodo(@PathVariable("id") Long todoId){
      TodoDto todoDto =  todoService.getTodo(todoId);
      return new ResponseEntity<>(todoDto,HttpStatus.OK);
    }

    /*
        REST API for getAllTodos
     */
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping
    public ResponseEntity<List<TodoDto>> getAllTodos(){
        List<TodoDto> todos=todoService.getAllTodos();
        return ResponseEntity.ok(todos);
    }

    /*
        REST API for updateTodo
        @PutMapping("{id}") annotation is used to update the existing resource
     */

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("{id}")
    public ResponseEntity<TodoDto> updateTodo(@RequestBody TodoDto todoDto ,@PathVariable("id") Long todoId){
        TodoDto todoDto1 =todoService.updateTodo(todoDto,todoId);
        return ResponseEntity.ok(todoDto1);
    }

    /*
        REST API for deleteTodo
        @DeleteMapping("{id}") annotation is used to delete the existing resource
     */

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{id}")
    public String deleteTodo(@PathVariable("id") Long todoId){
        todoService.deleteTodo(todoId);
        return "Todo has been deleted successfully";
    }

    /*
        REST API for completeTodo
        @PatchMapping("{id}") annotation is used to update the existing resource partially
     */

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PatchMapping("{id}/complete")
    public ResponseEntity<TodoDto> completeTodo(@PathVariable("id") Long todoId){
       TodoDto todoDto= todoService.completeTodo(todoId);
       return ResponseEntity.ok(todoDto);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PatchMapping("{id}/in-complete")
    public ResponseEntity<TodoDto> inCompleteTodo(@PathVariable("id") Long todoId){
        TodoDto todoDto= todoService.inCompleteTodo(todoId);
        return ResponseEntity.ok(todoDto);
    }
}
