package ru.cource.springTask.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.cource.springTask.mapper.PostMapper;
import ru.cource.springTask.model.TppProductRegister;
import ru.cource.springTask.service.*;
import ru.cource.springTask.dto.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class InstanceServiceImpl implements InstanceService {
    private final AgreementService agreementService;
    private final TppProductService tppProductService;
    private final TppProductRegisterService tppProductRegisterService;
    private final TppRefProductRegisterTypeService tppRefProductRegisterTypeService;
    private final AccountPoolService accountPoolService;

    @Autowired
    public InstanceServiceImpl(AgreementService agreementService, TppProductService tppProductService, TppProductRegisterService tppProductRegisterService, TppRefProductRegisterTypeService tppRefProductRegisterTypeService, AccountPoolService accountPoolService) {
        this.agreementService = agreementService;
        this.tppProductService = tppProductService;
        this.tppProductRegisterService = tppProductRegisterService;
        this.tppRefProductRegisterTypeService = tppRefProductRegisterTypeService;
        this.accountPoolService = accountPoolService;
    }
    @Transactional
    @Override
    public InstanceResponse processRequest(InstanceRequest request){

        // check product
        var tppProduct = PostMapper.mapToTppProduct(request);
        tppProductService.checkRecordExist(tppProduct);

        List<TppProductRegister> productRegisters = new ArrayList<>();

        if (request.getInstanceId() == null) {

            // save product
            tppProductService.save(tppProduct);

            // get registersType (List)
            var registersTypes = tppRefProductRegisterTypeService.findAllByProductClass_ValueAndAccountType_Value(PostMapper.mapToTppRefProductRegisterType(request));

            // get accounts (List)
            var accounts = accountPoolService.getUnusedAccountsFromPool(PostMapper.mapToAccountPool(request, registersTypes));

            // save productRegisters (List)
            productRegisters = PostMapper.mapToTppProductRegisters(tppProduct, accounts);
            tppProductRegisterService.saveAll(productRegisters);
        }

        // check agreement
        var agreements = PostMapper.mapToAgreement(request, tppProduct);
        agreementService.checkRecordsExist(agreements);

        // save agreements
        agreementService.saveAll(agreements);

        return PostMapper.mapToInstanceResponse(tppProduct, productRegisters, agreements);
    }
}
