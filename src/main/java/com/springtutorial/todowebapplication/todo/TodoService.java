package com.springtutorial.todowebapplication.todo;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Service
@Slf4j
public class TodoService {
//    private static final List<Todo> todos = new ArrayList<>();
//    static {
//        new Todo(1, "ram", "Learn Java", LocalDate.now().plusYears(1), false);
//        new Todo(2, "ram", "Learn SpringBoot", LocalDate.now().plusYears(2), false);
//        new Todo(3, "ram", "Learn Dockers", LocalDate.now().plusYears(3), false);
//    }

    private static int todosCount = 0;

    private static List<Todo> todos = new ArrayList<>();
    static {
        todos.add(new Todo(++todosCount, "ram","Learn AWS",
                LocalDate.now().plusYears(1), false ));
        todos.add(new Todo(++todosCount, "ram","Learn DevOps",
                LocalDate.now().plusYears(2), false ));
        todos.add(new Todo(++todosCount, "ram","Learn Full Stack Development",
                LocalDate.now().plusYears(3), false ));
    }
    public List<Todo> findByUserName(String userName){
        Predicate<? super Todo> predicate = todo -> todo.getUserName().equalsIgnoreCase(userName);
        return todos.stream().filter(predicate).toList();
    }
    public void addTodo(String userName, String description, LocalDate targetDate,boolean done){
        Todo todo = new Todo(++todosCount,userName,description,targetDate,done);
        todos.add(todo);
    }
    public void deleteById(int id){
        Predicate<? super Todo> predicate = todo -> todo.getId() == id;
        todos.removeIf(predicate);
    }
    public Todo findById(int id) {
        Predicate<? super Todo> predicate = todo -> todo.getId() == id;
        Todo todo = todos.stream().filter(predicate).findFirst().get();
        return todo;
    }
    public void updateTodo(@Valid Todo todo) {
        deleteById(todo.getId());
        todos.add(todo);
    }

}
