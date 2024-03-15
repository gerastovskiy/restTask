package ru.cource.springTask.service;

import ru.cource.springTask.model.Account;
import ru.cource.springTask.model.AccountPool;
import java.util.List;

public interface AccountPoolService {
    Account getUnusedAccountFromPool(AccountPool accountPool);
    List<Account> getUnusedAccountsFromPool(List<AccountPool> accountPool);
}
