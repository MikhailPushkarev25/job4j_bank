package ru.job4j.repository;


import org.springframework.stereotype.Repository;
import ru.job4j.model.User;

import java.util.Optional;
@Repository
public class UserRepository extends Store<User> {

    public Optional<User> findByPassport(String passport) {
        return store.values().stream()
                .filter(u -> u.getPassport().equals(passport))
                .findFirst();
    }
}
