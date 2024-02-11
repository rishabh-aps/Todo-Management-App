package com.rishabh.todo.service.impl;

import com.rishabh.todo.dto.TodoDto;
import com.rishabh.todo.entity.Todo;
import com.rishabh.todo.exception.ResourceNotFoundException;
import com.rishabh.todo.repository.TodoRepository;
import com.rishabh.todo.service.TodoService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/*

    @Service annotation is used to mark a class as a service component
    in the Spring application context. It's typically applied to classes
    that perform service tasks such as business logic, data manipulation,
    or external interactions.
 */

@Service
@AllArgsConstructor
/*
    @AllArgsConstructor annotation will automatically create
    a parameterized constructor for this TodoServiceImpl class
    using these 2 fields

    private TodoRepository todoRepository;

    private ModelMapper modelMapper;
 */
public class TodoServiceImpl implements TodoService {

    /*
         Dependency Injection is done by
         constructor based DI using lombok annotation
     */
    private TodoRepository todoRepository;

    private ModelMapper modelMapper;

    @Override
    public TodoDto addTodo(TodoDto todoDto) {
        /*
            1. Convert todoDto into todo JPA entity
            2. Save this todo JPA entity into the database
         */

        //  1. Convert todoDto into todo JPA entity
        /*
            Old Code:  Now we do the same mapping using ModelMapper Object

        Todo todo = new Todo();
        todo.setTitle(todoDto.getTitle());
        todo.setDescription(todoDto.getDescription());
        todo.setCompleted(todoDto.isCompleted());
        */

        Todo todo = modelMapper.map(todoDto,Todo.class);


        //  2. Save this todo JPA entity into the database
        Todo savedTodo = todoRepository.save(todo);

        // 3. Convert savedTodo JPA entity object into TodoDto object to return

        /*
            Old Code: Now we do the same mapping using ModelMapper Object
        TodoDto savedTodoDto= new TodoDto();
        savedTodoDto.setId(savedTodo.getId());
        savedTodoDto.setTitle(savedTodo.getTitle());
        savedTodoDto.setDescription(savedTodo.getDescription());
        savedTodoDto.setCompleted(savedTodo.isCompleted());
        */

        return modelMapper.map(savedTodo,TodoDto.class);
    }

    @Override
    public TodoDto getTodo(Long id) {
       Todo todo= todoRepository.findById(id)
               .orElseThrow(()->new ResourceNotFoundException("Todo not found with given id:"+id));
       return modelMapper.map(todo,TodoDto.class);
    }

    @Override
    public List<TodoDto> getAllTodos() {
       List<Todo> todos = todoRepository.findAll();
       return todos.stream().map((todo)->modelMapper.map(todo,TodoDto.class))
               .collect(Collectors.toList());
    }

    @Override
    public TodoDto updateTodo(TodoDto todoDto, Long id) {
     Todo todo = todoRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Todo not found with given id"+id));
     todo.setTitle(todoDto.getTitle());
     todo.setDescription(todoDto.getDescription());
     todo.setCompleted(todoDto.isCompleted());
     Todo updatedTodo=todoRepository.save(todo);
     return modelMapper.map(updatedTodo,TodoDto.class);
    }

    @Override
    public void deleteTodo(Long id) {
        Todo todo = todoRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Todo not found with given id"+id));
        todoRepository.deleteById(id);
    }

    @Override
    public TodoDto completeTodo(Long id) {
        Todo todo = todoRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Todo not found with given id"+id));
        todo.setCompleted(Boolean.TRUE);
        Todo updatedTodo = todoRepository.save(todo);
        return modelMapper.map(updatedTodo,TodoDto.class);
    }

    @Override
    public TodoDto inCompleteTodo(Long id) {
        Todo todo = todoRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Todo not found with given id"+id));
        todo.setCompleted(Boolean.FALSE);
        Todo updatedTodo = todoRepository.save(todo);
        return modelMapper.map(updatedTodo,TodoDto.class);
    }
}
