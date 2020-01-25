package ru.tandemservice.palindrome.service;

import org.omg.CORBA.DynAnyPackage.InvalidValue;
import ru.tandemservice.palindrome.bd.UserRepo;
import ru.tandemservice.palindrome.entity.User;

public class UserService {
    private UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public User getByName(String userName) throws InvalidValue {
        userName = userName.trim();
        if (userName.isEmpty()) {
            throw new InvalidValue("User name must not be blank");
        }

        User byName = userRepo.getByName(userName);
        if (null == byName) {
            byName = userRepo.save(userName);
        }
        return byName;
    }
}
