package ru.tandemservice.palindrome.bd;

import ru.tandemservice.palindrome.entity.User;

public interface UserRepo {
    User getByName(String userName);

    User save(String userName);
}
