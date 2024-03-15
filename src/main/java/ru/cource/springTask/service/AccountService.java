package ru.cource.springTask.service;

import ru.cource.springTask.dto.AccountRequest;
import ru.cource.springTask.dto.AccountResponse;

public interface AccountService {
    AccountResponse processRequest(AccountRequest request);
}
