package com.colossus.todolist.controllers;

import com.colossus.todolist.domain.User;
import com.colossus.todolist.domain.plainObjects.UserPojo;
import com.colossus.todolist.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/user/registration")
    public ResponseEntity<UserPojo> createUser(@RequestBody User user){
        UserPojo result = userService.createUser(user);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(path = "/user/{id}")
    public ResponseEntity<UserPojo> getUser(@PathVariable long id){
        UserPojo result = userService.getUser(id);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @PutMapping (path = "/user/{id}")
    public ResponseEntity<UserPojo> updateUser (@PathVariable long id, @RequestBody User source){
        UserPojo result = userService.updateUser(source, id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping(path = "/user/{id}")
    public ResponseEntity<String> deleteUser (@PathVariable long id){
        String result = userService.deleteUser(id);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @GetMapping(path = "/users")
    public ResponseEntity<List<UserPojo>> getAllUsers(){
        List<UserPojo> result = userService.getAllUsers();
        return new ResponseEntity<>(result,HttpStatus.OK);
    }
}
