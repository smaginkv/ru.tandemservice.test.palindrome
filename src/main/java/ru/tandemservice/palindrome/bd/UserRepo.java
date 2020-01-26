package ru.tandemservice.palindrome.bd;

import ru.tandemservice.palindrome.entity.User;

/**
 * Интерфейс, используется для быстрой подмены репозитория (например если нужно хранить в bd)
 * @author Smagin-KV
 */
public interface UserRepo {
    User getByName(String userName);

    User save(String userName);
}
