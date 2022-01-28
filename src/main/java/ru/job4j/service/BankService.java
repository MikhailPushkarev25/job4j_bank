package ru.job4j.service;

import ru.job4j.model.Account;
import ru.job4j.model.User;

import java.util.Optional;

public interface BankService {

    void addUser(User user);

    void addAccount(String passport, Account account);

    Optional<User> findByPassport(String passport);

    Optional<Account> findByRequisite(String passport, String requisite);

    boolean transferMany(String srcPassword, String srcRequisite,
                         String destPassport, String destRequisite, double amount);
}
