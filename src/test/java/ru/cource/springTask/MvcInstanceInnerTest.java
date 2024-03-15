package ru.cource.springTask;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.test.web.servlet.MockMvc;
import ru.cource.springTask.controller.InstanceController;
import ru.cource.springTask.service.*;
import ru.cource.springTask.service.impl.InstanceServiceImpl;
import java.util.NoSuchElementException;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class MvcInstanceInnerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private InstanceService instanceService;
    @Autowired
    private InstanceController accountController;
    @MockBean
    private AccountService accountService;

    @Autowired
    ApplicationContext context;

    @TestConfiguration
    static class InstanceServiceConfiguration{
        @MockBean
        AgreementService agreementService;
        @MockBean
        TppProductService tppProductService;
        @MockBean
        TppProductRegisterService tppProductRegisterService;
        @MockBean
        TppRefProductRegisterTypeService tppRefProductRegisterTypeService;
        @MockBean
        AccountPoolService accountPoolService;

        @Bean
        public InstanceService instanceService(){
            return new InstanceServiceImpl(agreementService, tppProductService, tppProductRegisterService, tppRefProductRegisterTypeService, accountPoolService);
        }
    }

    @Test
    public void checkRecordExistTest() throws Exception{
        var tppProductServiceMock = context.getBean(TppProductService.class);
        var errorText = "Product already exist, id: 1";

        doThrow(new NoSuchElementException(errorText))
                .when(tppProductServiceMock).checkRecordExist(any());

        mockMvc.perform(get("/corporate-settlement-instance/create")
                        .contentType("application/json")
                        .content(Utils.correctInstanceRequest()))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value(errorText));;
    }

    @Test
    public void checkFindAllByProductClass_ValueAndAccountType_ValueTest() throws Exception{
        var tppRefProductRegisterTypeServiceMock = context.getBean(TppRefProductRegisterTypeService.class);
        var errorText = "Product registry type not found";

        doThrow(new NoSuchElementException(errorText))
                .when(tppRefProductRegisterTypeServiceMock).findAllByProductClass_ValueAndAccountType_Value(any());

        mockMvc.perform(get("/corporate-settlement-instance/create")
                        .contentType("application/json")
                        .content(Utils.correctInstanceRequest()))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value(errorText));;
    }

    @Test
    public void checkGetUnusedAccountFromPoolTest() throws Exception{
        var accountPoolServiceMock = context.getBean(AccountPoolService.class);
        var errorText = "No unbussy account in account pool";

        doThrow(new NoSuchElementException(errorText))
                .when(accountPoolServiceMock).getUnusedAccountsFromPool(any());

        mockMvc.perform(get("/corporate-settlement-instance/create")
                        .contentType("application/json")
                        .content(Utils.correctInstanceRequest()))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value(errorText));;
    }

    @Test
    public void checkRecordsExistTest() throws Exception{
        var agreementServiceMock = context.getBean(AgreementService.class);
        var errorText = "Agreement already exist, id: 1";

        doThrow(new NoSuchElementException(errorText))
                .when(agreementServiceMock).checkRecordsExist(any());

        mockMvc.perform(get("/corporate-settlement-instance/create")
                        .contentType("application/json")
                        .content(Utils.correctInstanceRequest()))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value(errorText));;
    }
}
