package com.rishabh.todo.repository;

import com.rishabh.todo.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

/*
    Once TodoRepository extends JpaRepository,
    TodoRepository will get CRUD operations to perform CRUD Database operations
    for "Todo" JPA entity. which is passed as an argument to JpaRepository<Todo,Long>
    The First argument is JPA entity and Second argument is the Primary Key of that JPA entity
 */
public interface TodoRepository extends JpaRepository<Todo,Long> {
}
