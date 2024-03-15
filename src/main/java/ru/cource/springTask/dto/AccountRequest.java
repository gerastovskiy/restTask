package ru.cource.springTask.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter @Setter
@ToString
public class AccountRequest {
    @NotNull(message = "instanceId is required")
    private Integer instanceId;
    private String registryTypeCode;
    private String accountType;
    @Size(min = 3, max = 3)
    private String currencyCode;
    private String branchCode;
    private String priorityCode;
    private String mdmCode;
    private String clientCode;
    private String trainRegion;
    private String counter;
    private String salesCode;
}
