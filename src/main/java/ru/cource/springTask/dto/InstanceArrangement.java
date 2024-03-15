package ru.cource.springTask.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.ToString;
import java.sql.Timestamp;

@Getter
@ToString
public class InstanceArrangement {
    private String generalAgreementId;
    private String supplementaryAgreementId;
    private String arrangementType;
    private Integer shedulerJobId;
    @NotBlank(message = "number is required")
    private String number;
    @NotNull(message = "openingDate is required")
    private Timestamp openingDate;
    private Timestamp closingDate;
    private Timestamp cancelDate;
    private Integer validityDuration;
    private String cancellationReason;
    private String status;
    private Timestamp interestCalculationDate;
    private Float interestRate;
    private Float coefficient;
    private String coefficientAction;
    private Float minimumInterestRate;
    private Float minimumInterestRateCoefficient;
    private String minimumInterestRateCoefficientAction;
    private Float maximalnterestRate;
    private Float maximalnterestRateCoefficient;
    private String maximalnterestRateCoefficientAction;

}
