package com.rishabh.todo.entity;

/*
    lombok annotation to automatically generate getters, setters and constructors
 */

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
/*
@Entity annotation to specify this Todo class as a JPA entity
 */
@Entity
/*
@Table annotation to specify the table details
The below annotations maps Todo JPA entity with "todos" table in the database
 */

/*
    Todo JPA entity is used to transfer the data from database to server
 */
@Table(name = "todos")
public class Todo {
    /*
    Marking Primary Key of this JPA entity with @Id annotation
    @GeneratedValue annotation to specify Primary Key generation strategy
    @GeneratedValue(strategy = GenerationType.IDENTITY): entity type as auto-increment
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*
   @Column(name = "title",nullable = false) annotation to map a particular
   field with a specific column in todos table and it cannot be null
     */


    @Column(name = "title",nullable = false)
    private String title;

    @Column(name = "description",nullable = false)
    private String description;

    /*
    If we do not specify the @Column annotation,
    The JPA is smart enough to configure the column for the corresponding field
     */
    private boolean completed;
}
