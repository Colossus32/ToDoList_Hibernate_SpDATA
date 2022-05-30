package com.colossus.todolist.services;

import com.colossus.todolist.domain.User;
import com.colossus.todolist.domain.plainObjects.UserPojo;
import com.colossus.todolist.services.interfaces.IUserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

import static org.junit.Assert.*;

public class UserServiceTest {

    private final String EMAIL = "test@gmail.com";
    private final String PASSWORD = "strong password";

    private ApplicationContext applicationContext;
    private IUserService userService;

    @Before
    public void init(){

        this.applicationContext = new ClassPathXmlApplicationContext("/mainTest.xml");
        this.userService = applicationContext.getBean("userService", IUserService.class);
    }

    @After
    public void cleanDB(){
        userService.getAllUsers().stream()
                .forEach(userPojo -> userService.deleteUser(userPojo.getId()));
    }

    @Test
    public void createUserTest(){

        User newUser = createSimpleUser();

        UserPojo actual = userService.createUser(newUser);

        assertEquals(EMAIL, actual.getEmail());
        assertEquals(PASSWORD, actual.getPassword());
        assertNotNull(actual.getId());
    }

    @Test
    public void getUserTest(){

        UserPojo actual = userService.createUser(createSimpleUser());

        UserPojo current = userService.getUser(actual.getId());

        assertEquals(EMAIL, current.getEmail());
        assertEquals(PASSWORD, current.getPassword());
        assertEquals(actual.getId(), current.getId());
    }

    @Test
    public void findUserByEmailAndPasswordTest(){

        UserPojo actual = userService.createUser(createSimpleUser());

        UserPojo current = userService.findUserByEmailAndPassword(EMAIL,PASSWORD);

        assertEquals(EMAIL, current.getEmail());
        assertEquals(PASSWORD, current.getPassword());
        assertEquals(actual.getId(), current.getId());
    }

    @Test
    public void updateUserTest(){

        UserPojo actual = userService.createUser(createSimpleUser());

        User userForUpdate = new User();
        userForUpdate.setEmail("new@gmai.com");
        userForUpdate.setPassword("weakpassword");

        UserPojo updatedUser = userService.updateUser(userForUpdate, actual.getId());

        assertEquals(actual.getId(), updatedUser.getId());
        assertEquals("new@gmai.com", updatedUser.getEmail());
        assertEquals("weakpassword", updatedUser.getPassword());
    }


    @Test
    public void deleteUserTest(){

        UserPojo user = userService.createUser(createSimpleUser());
        List<UserPojo> listAfterAdding = userService.getAllUsers();
        assertEquals(1, listAfterAdding.size());

        userService.deleteUser(user.getId());

        List<UserPojo> listAfterDeleting = userService.getAllUsers();
        assertEquals(0, listAfterDeleting.size());
    }

    private User createSimpleUser(){

        User newUser = new User();
        newUser.setEmail(EMAIL);
        newUser.setPassword(PASSWORD);

        return newUser;
    }
}
