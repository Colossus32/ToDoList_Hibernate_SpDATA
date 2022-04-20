package com.colossus.todolist.services.interfaces;

import com.colossus.todolist.domain.User;
import com.colossus.todolist.domain.plainObjects.UserPojo;

import java.util.List;

public interface IUserService {

    UserPojo createUser(User user);
    UserPojo getUser (long id);
    UserPojo updateUser(User user, long id);
    UserPojo deleteUser(long id);
    List<UserPojo> getAllUsers();

}
