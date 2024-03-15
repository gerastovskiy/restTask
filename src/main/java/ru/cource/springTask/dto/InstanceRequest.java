package ru.cource.springTask.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.ToString;
import java.sql.Timestamp;
import java.util.List;

@Getter
@ToString
public class InstanceRequest {
    private Integer instanceId;
    @Pattern(regexp="^(НСО|СМО|ЕЖО|ДБДС|договор)$", message="productType is required, valid values is (НСО,СМО,ЕЖО,ДБДС,договор")
    private String productType;
    @NotBlank(message = "productCode is required")
    private String productCode;
    @NotBlank(message = "registerType is required")
    private String registerType;
    @NotBlank(message = "mdmCode is required")
    private String mdmCode;
    @NotBlank(message = "contractNumber is required")
    private String contractNumber;
    @NotNull(message = "contractDate is required")
    private Timestamp contractDate;
    @NotNull(message = "priority is required")
    private int priority;
    private float interestRatePenalty;
    private float minimalBalance;
    private float thresholdAmount;
    private String accountingDetails;
    private String rateType;
    private float taxPercentageRate;
    private float technicalOverdraftLimitAmount;
    @NotNull(message = "contractId is required")
    private int contractId;
    @NotBlank(message = "branchCode is required")
    private String branchCode;
    @Size(min = 3, max = 3)
    private String isoCurrencyCode;
    @NotBlank(message = "urgencyCode is required")
    private String urgencyCode;
    private String referenceCode;
    private List<Data> additionalPropertiesVip;
    private List<InstanceArrangement> instanceArrangement;
}
