package com.springtutorial.todowebapplication.todo;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Controller
@SessionAttributes("name")
public class todoControllerJpa {
    @Autowired
    private TodoRepository todoRepository;
    @RequestMapping("list-todos")
    public String listAllTodos(ModelMap modelMap){
        String username = getLoggedInUsername(modelMap);
        List<Todo> todos = todoRepository.findByUserName(username);
        modelMap.addAttribute("todos",todos);
        return "listTodos";
    }
    @RequestMapping(value = "add-todo", method = RequestMethod.GET)
    public String showNewTodo(ModelMap modelMap){
        String userName = getLoggedInUsername(modelMap);
        Todo todo = new Todo(0,userName,"",LocalDate.now().plusYears(1),false);
        modelMap.put("todo",todo);
        return "todo";
    }
    @RequestMapping(value = "add-todo", method = RequestMethod.POST)
    public String addNewTodo(ModelMap modelMap, @Valid @ModelAttribute("todo") Todo todo, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "todo";
        }
        String userName = getLoggedInUsername(modelMap);
        todo.setUserName(userName);
        todoRepository.save(todo);
        return "redirect:list-todos";
    }
    @RequestMapping("delete-todo")
    public String deleteTodo(@RequestParam int id){
        todoRepository.deleteById(id);
        return "redirect:list-todos";
    }
    @RequestMapping(value = "update-todo",method = RequestMethod.GET)
    public String showUpdateTodo(@RequestParam int id, ModelMap modelMap){
        Todo todo = todoRepository.findById(id).get();
        modelMap.addAttribute(todo);
        return "todo";
    }
    @RequestMapping(value = "update-todo",method = RequestMethod.POST)
    public String UpdateTodo(@RequestParam int id, ModelMap modelMap, @Valid Todo todo, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "todo";
        }
        String userName = getLoggedInUsername(modelMap);
        todo.setUserName(userName);
        todoRepository.save(todo);
        return "redirect:list-todos";
    }
    private String getLoggedInUsername(ModelMap modelMap){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }
}
