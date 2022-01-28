package ru.job4j.service;

import org.springframework.stereotype.Service;
import ru.job4j.model.Account;
import ru.job4j.model.User;
import ru.job4j.repository.AccountRepository;
import ru.job4j.repository.UserRepository;

import java.util.Optional;

@Service
public class SimpleBankService implements BankService {
    private final UserRepository userRepository;
    private final AccountRepository accountRepository;

    public SimpleBankService(UserRepository userRepository,
                             AccountRepository accountRepository) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public void addUser(User user) {
        this.userRepository.saveOrUpdate(user);
    }

    @Override
    public void addAccount(String passport, Account account) {
        this.userRepository.findByPassport(passport)
                .ifPresent(u -> {
                    account.setUser(u);
                    accountRepository.saveOrUpdate(account);
                    u.getAccounts().add(account);
                });
    }

    @Override
    public Optional<User> findByPassport(String passport) {
        return this.userRepository.findByPassport(passport);
    }

    @Override
    public Optional<Account> findByRequisite(String passport, String requisite) {
        return this.accountRepository.findByRequisite(passport, requisite);
    }

    @Override
    public boolean transferMany(String srcPassword, String srcRequisite, String destPassport, String destRequisite, double amount) {
        var srcAccount = findByRequisite(srcPassword, srcRequisite);
        if (srcAccount.isEmpty()) {
            return false;
        }

        var destAccount = findByRequisite(destPassport, destRequisite);
        if (destAccount.isEmpty()) {
            return false;
        }

        if (srcAccount.get().getBalance() - amount < 0) {
            return false;
        }

        srcAccount.get().setBalance(srcAccount.get().getBalance() - amount);
        destAccount.get().setBalance(destAccount.get().getBalance() + amount);
        return false;
    }
}
