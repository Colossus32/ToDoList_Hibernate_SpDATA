package com.colossus.todolist.controllers;

import com.colossus.todolist.domain.Todo;
import com.colossus.todolist.domain.User;
import com.colossus.todolist.domain.plainObjects.UserPojo;
import com.colossus.todolist.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class UserController {

    private final IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/user/create")
    public ResponseEntity<UserPojo> createUser(@RequestBody User user){
        UserPojo result = userService.createUser(user);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(path = "/user/{id}")
    public ResponseEntity<UserPojo> getUser(@PathVariable long id){
        UserPojo result = userService.getUser(id);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @GetMapping(path = "/user/all")
    public ResponseEntity<List<UserPojo>> getAllUsers(){
        List<UserPojo> result = userService.getAllUsers();
        return new ResponseEntity<>(result,HttpStatus.OK);
    }
}
