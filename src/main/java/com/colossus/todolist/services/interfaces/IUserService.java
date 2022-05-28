package com.colossus.todolist.services.interfaces;

import com.colossus.todolist.domain.User;
import com.colossus.todolist.domain.plainObjects.UserPojo;

import java.util.List;

public interface IUserService {

    UserPojo createUser(User user);
    UserPojo findUserByEmailAndPassword (String email, String password);
    UserPojo getUser (long id);
    UserPojo updateUser(User user, long id);
    String deleteUser(long id);
    List<UserPojo> getAllUsers();

}
