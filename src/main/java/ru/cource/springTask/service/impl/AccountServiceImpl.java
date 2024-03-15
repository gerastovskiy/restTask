package ru.cource.springTask.service.impl;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.cource.springTask.mapper.PostMapper;
import ru.cource.springTask.service.*;
import ru.cource.springTask.dto.*;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountPoolService accountPoolService;
    private final TppProductRegisterService tppProductRegisterService;
    private final TppRefProductRegisterTypeService tppRefProductRegisterTypeService;

    @Autowired
    public AccountServiceImpl(AccountPoolService accountPoolService, TppProductRegisterService tppProductRegisterService, TppRefProductRegisterTypeService tppRefProductRegisterTypeService) {
        this.accountPoolService = accountPoolService;
        this.tppProductRegisterService = tppProductRegisterService;
        this.tppRefProductRegisterTypeService = tppRefProductRegisterTypeService;
    }

    @Transactional
    @Override
    public AccountResponse processRequest(AccountRequest request){
        // check productRegister
        var tppProductRegister = PostMapper.mapToTppProductRegister(request);
        tppProductRegisterService.checkRecordExist(tppProductRegister);

        // get registerType
        tppRefProductRegisterTypeService.get(tppProductRegister);

        // get account
        var account = accountPoolService.getUnusedAccountFromPool(PostMapper.mapToAccountPool(request));

        // save productRegister
        tppProductRegisterService.save(PostMapper.mapToTppProductRegister(tppProductRegister, account));

        return PostMapper.mapToAccountResponse(tppProductRegister);
    }
}
