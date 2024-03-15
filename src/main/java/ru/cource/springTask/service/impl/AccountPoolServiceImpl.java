package ru.cource.springTask.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cource.springTask.model.Account;
import ru.cource.springTask.model.AccountPool;
import ru.cource.springTask.repository.AccountPoolRepository;
import ru.cource.springTask.service.AccountPoolService;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AccountPoolServiceImpl implements AccountPoolService {
    private final AccountPoolRepository accountPoolRepository;

    @Autowired
    public AccountPoolServiceImpl(AccountPoolRepository accountPoolRepository) {
        this.accountPoolRepository = accountPoolRepository;
    }
    @Override
    public Account getUnusedAccountFromPool(AccountPool accountPoolRequest) {
        var accountPool = accountPoolRepository.find(
                accountPoolRequest.getBranchCode(),
                accountPoolRequest.getCurrencyCode(),
                accountPoolRequest.getMdmCode(),
                accountPoolRequest.getPriorityCode(),
                accountPoolRequest.getRegistryType());

        if (accountPool == null)
            throw new NoSuchElementException("No suitable account pool for given parameters.");

        return accountPool.getAccountList()
                .stream()
                .filter(account -> !account.getBussy())
                .findAny()
                .orElseThrow(() -> new NoSuchElementException("No unbussy account in account pool."));
    }

    @Override
    public List<Account> getUnusedAccountsFromPool(List<AccountPool> accountPool) {
        var accountsPool = new ArrayList<Account>();

        accountPool.forEach(acc -> accountsPool.add(getUnusedAccountFromPool(acc)));

        return accountsPool;
    }
}
