package ru.cource.springTask;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.cource.springTask.service.AccountService;
import ru.cource.springTask.service.InstanceService;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class MvcInstanceTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    AccountService accountService;
    @MockBean
    InstanceService instanceService;

    @Test
    public void instanceCreateIncorrectRequestTest() throws Exception{
        Mockito.when(this.instanceService.processRequest(Mockito.any()))
                .thenReturn(Utils.correctInstanceResponse());

        mockMvc.perform(get("/corporate-settlement-instance/create")
                        .contentType("application/json")
                        .content(Utils.incorrectInstanceRequest()))
                .andExpect(status().isNotFound());
    }

    @Test
    public void instanceCreateCorrectRequestTest() throws Exception {
        Mockito.when(this.instanceService.processRequest(Mockito.any()))
                .thenReturn(Utils.correctInstanceResponse());

        mockMvc.perform(get("/corporate-settlement-instance/create")
                        .contentType("application/json")
                        .content(Utils.correctInstanceRequest()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.instanceId").value(1))
                .andExpect(jsonPath("$.data.registerId[0]").value(2))
                .andExpect(jsonPath("$.data.registerId[1]").value(3))
                .andExpect(jsonPath("$.data.supplementaryAgreementId[0]").value(4))
                .andExpect(jsonPath("$.data.supplementaryAgreementId[1]").value(5))
                .andExpect(jsonPath("$.data.supplementaryAgreementId[2]").value(6));
    }
}
