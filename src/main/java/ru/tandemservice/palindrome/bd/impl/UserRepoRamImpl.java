package ru.tandemservice.palindrome.bd.impl;

import ru.tandemservice.palindrome.bd.UserRepo;
import ru.tandemservice.palindrome.entity.User;

import java.util.HashMap;

/**
 * Класс - имплементация репозитория, организованного в памяти
 * репозиторий хранит в себе всех пользователей которые участвовали в игре
 * @author Smagin-KV
 */
public class UserRepoRamImpl implements UserRepo {
    private HashMap<String, User> users;

    public UserRepoRamImpl() {
        users = new HashMap<>();
    }

    @Override
    public User getByName(String userName) {
        return users.get(userName);
    }

    @Override
    public User save(String userName) {
        User user = new User(userName);
        users.put(userName, user);
        return user;
    }
}
