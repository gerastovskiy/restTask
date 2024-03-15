package ru.cource.springTask.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.cource.springTask.dto.Response;
import ru.cource.springTask.dto.AccountRequest;
import ru.cource.springTask.dto.AccountResponse;
import ru.cource.springTask.service.AccountService;

@RestController
public class AccountController {
    private AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping(value = "/corporate-settlement-account/create")
    public ResponseEntity<Response<AccountResponse>> create(@RequestBody @Valid AccountRequest request){

        var response = new Response<AccountResponse>(accountService.processRequest(request));

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }
}
