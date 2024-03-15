package ru.cource.springTask;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.test.web.servlet.MockMvc;
import ru.cource.springTask.controller.AccountController;
import ru.cource.springTask.service.*;
import ru.cource.springTask.service.impl.AccountServiceImpl;
import java.util.NoSuchElementException;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.*;

@WebMvcTest
public class MvcAccountInnerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private AccountService accountService;
    @Autowired
    private AccountController accountController;
    @MockBean
    private InstanceService instanceService;

    @Autowired
    ApplicationContext context;

    @TestConfiguration
    static class AccountServiceConfiguration{
        @MockBean
        AccountPoolService accountPoolService;
        @MockBean
        TppProductRegisterService tppProductRegisterService;
        @MockBean
        TppRefProductRegisterTypeService tppRefProductRegisterTypeService;
        @Bean
        public AccountService accountService(){
            return new AccountServiceImpl(accountPoolService, tppProductRegisterService, tppRefProductRegisterTypeService);
        }
    }

    @Test
    public void checkRecordExistTest() throws Exception{
        var tppProductRegisterServiceMock = context.getBean(TppProductRegisterService.class);
        var errorText = "Product registry already exist, id: 1";

        doThrow(new NoSuchElementException(errorText))
                .when(tppProductRegisterServiceMock).checkRecordExist(any());

        mockMvc.perform(get("/corporate-settlement-account/create")
                        .contentType("application/json")
                        .content(Utils.correctAccountRequest()))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value(errorText));;
    }

    @Test
    public void checkTppRefProductRegisterTypeTest() throws Exception{
        var tppRefProductRegisterTypeServiceMock = context.getBean(TppRefProductRegisterTypeService.class);
        var errorText = "Product registry type not found";

        doThrow(new NoSuchElementException(errorText))
                .when(tppRefProductRegisterTypeServiceMock).get(any());

        mockMvc.perform(get("/corporate-settlement-account/create")
                        .contentType("application/json")
                        .content(Utils.correctAccountRequest()))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value(errorText));;
    }

    @Test
    public void checkGetUnusedAccountFromPoolTest() throws Exception{
        var accountPoolServiceMock = context.getBean(AccountPoolService.class);
        var errorText = "No suitable account pool for given parameters";

        doThrow(new NoSuchElementException(errorText))
                .when(accountPoolServiceMock).getUnusedAccountFromPool(any());

        mockMvc.perform(get("/corporate-settlement-account/create")
                        .contentType("application/json")
                        .content(Utils.correctAccountRequest()))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value(errorText));;
    }
}
