package ru.cource.springTask;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.cource.springTask.service.AccountService;
import ru.cource.springTask.service.InstanceService;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//Как минимум можно проверить, что нужный метод вызывается из сервиса с помощью Mokito.verify() -
//А вообще интеграционные тесты не для тестирования конкретного метода, а для тестирования взаимодействия между классами и на соответствие
//кода некоей бизнес-логике.
@WebMvcTest
public class MvcAccountTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    AccountService accountService;
    @MockBean
    InstanceService instanceService;

    @Test
    public void accountCreateIncorrectRequestTest() throws Exception{
        Mockito.when(this.accountService.processRequest(Mockito.any()))
                .thenReturn(Utils.correctAccountResponse());

        mockMvc.perform(get("/corporate-settlement-account/create")
                        .contentType("application/json")
                        .content(Utils.incorrectAccountRequest()))
                .andExpect(status().isNotFound());
    }

    @Test
    public void accountCreateCorrectRequestTest() throws Exception {
        Mockito.when(this.accountService.processRequest(Mockito.any()))
                .thenReturn(Utils.correctAccountResponse());

        mockMvc.perform(get("/corporate-settlement-account/create")
                        .contentType("application/json")
                        .content(Utils.correctAccountRequest()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.accountId").value(1));
    }

}
