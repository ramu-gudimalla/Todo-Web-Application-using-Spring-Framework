package com.springtutorial.todowebapplication.todo;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Todo {
    @Id
    @GeneratedValue
    private Integer id;
    private String userName;
    @Size(min = 10,message = "Enter at least 10 characters")
    private String description;
    private LocalDate targetDate;
    private boolean done;
}
