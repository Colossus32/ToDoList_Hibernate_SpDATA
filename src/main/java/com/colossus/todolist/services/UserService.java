package com.colossus.todolist.services;

import com.colossus.todolist.domain.User;
import com.colossus.todolist.domain.plainObjects.UserPojo;
import com.colossus.todolist.services.interfaces.IUserService;
import com.colossus.todolist.utils.Converter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {

    private final Converter converter;

    @PersistenceContext
    EntityManager entityManager;

    public UserService(Converter converter) {
        this.converter = converter;
    }

    @Override
    @Transactional
    public UserPojo createUser(User user) {

        entityManager.persist(user);

        return converter.userToPojo(user);
    }

    @Override
    @Transactional(readOnly = true)
    public UserPojo getUser(long id) {


        User foundUser = entityManager.createQuery("SELECT user FROM User user WHERE user.id = :id", User.class)
                .setParameter("id", id)
                .getSingleResult();

        return converter.userToPojo(foundUser);
    }

    @Override
    public UserPojo updateUser(User updatedUser, long id) {

        return null;
    }

    @Override
    public UserPojo deleteUser(long id) {

        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserPojo> getAllUsers() {
        List<User> userList = entityManager.createQuery("SELECT user FROM User user", User.class).getResultList();

        List<UserPojo> result = userList.stream()
                .map(converter::userToPojo)
                .collect(Collectors.toList());

        return result;
    }
}
