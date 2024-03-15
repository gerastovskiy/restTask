package ru.cource.springTask;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;
import ru.cource.springTask.repository.*;
import ru.cource.springTask.service.AccountPoolService;
import ru.cource.springTask.service.TppProductRegisterService;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RepositoryTest {
    @Autowired
    private TppProductRepository tppProductRepository;
    @Autowired
    private AgreementRepository agreementRepository;
    @Autowired
    private AccountPoolRepository accountPoolRepository;
    @MockBean
    private AccountPoolService accountPoolService;
    @Autowired
    private TppRefProductRegisterTypeRepository tppRefProductRegisterTypeRepository;
    @Autowired
    private TppProductRegisterRepository tppProductRegisterRepository;
    @MockBean
    private TppProductRegisterService tppProductRegisterService;

    @Test
    @Transactional
    public void RepositoryTest(){
        // product add / get test
        tppProductRepository.save(Utils.createTppProduct());

        var tppProductMock = Utils.createTppProduct();
        var tppProductRepo = tppProductRepository.findByNumber(tppProductMock.getNumber());
        tppProductMock.setId(tppProductRepo.getId());
        Assertions.assertEquals(tppProductMock, tppProductRepo);

        // agreement add / get test
        agreementRepository.save(Utils.createAgreement(tppProductRepo, 1));
        var agreementMock = Utils.createAgreement(tppProductRepo, 1);
        var agreementRepo = agreementRepository.findByNumber(agreementMock.getNumber());
        agreementMock.setId(agreementRepo.getId());
        Assertions.assertEquals(agreementMock, agreementRepo);

        agreementRepository.saveAll(Utils.createAgreements(tppProductRepo));
        Assertions.assertEquals(3, agreementRepository.count());

        // account pool get test
        var accountPool = Utils.createAccountPool();
        accountPool.setRegistryType(tppRefProductRegisterTypeRepository.findByValue(accountPool.getRegistryType().getValue()));
        var accountRepo = accountPoolService.getUnusedAccountFromPool(accountPool);
        var accountMock = Utils.createAccountPool();
        accountMock.setId(accountRepo.getId());
        Assertions.assertEquals(accountMock, accountRepo);

        // product register add / get test
        var productRegisterRepo = Utils.createProductRegister(accountRepo);
        tppProductRegisterService.save(productRegisterRepo);
        var productRegisterMock = Utils.createProductRegister(accountRepo);
        productRegisterMock.setId(productRegisterRepo.getId());
        Assertions.assertEquals(productRegisterMock, productRegisterRepo);
    }
}
