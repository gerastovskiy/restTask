package ru.cource.springTask;

import ru.cource.springTask.dto.AccountRequest;
import ru.cource.springTask.dto.AccountResponse;
import ru.cource.springTask.dto.InstanceResponse;
import ru.cource.springTask.dto.Response;
import ru.cource.springTask.model.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Utils {

    public static TppProduct createTppProduct(){
        return TppProduct.builder()
                .type("НСО")
                .number("123456")
                .startDateTime(Timestamp.valueOf(LocalDateTime.of(2024,01,02,01,02,03)))
                .priority(1)
                .thresholdAmount(1000.11f)
                .interestRateType("0")
                .taxRate(13.00f)
                .build();
    }

    public static Agreement createAgreement(TppProduct tppProduct, int number) {
        return Agreement.builder()
                .productId(tppProduct)
                .generalAgreementId("12345")
                .supplementaryAgreementId("67890")
                .arrangementType("arrangementTypeValue")
                .shedulerJobId(999)
                .number("contract" + number)
                .openingDate(Timestamp.valueOf(LocalDateTime.of(2024,01,02,01,02,03)))
                .closingDate(Timestamp.valueOf(LocalDateTime.of(2025,01,02,01,02,03)))
                .cancelDate(Timestamp.valueOf(LocalDateTime.of(2026,01,02,01,02,03)))
                .validityDuration(5)
                .cancellationReason("cancellationReasonValue")
                .status("открыт")
                .interestCalculationDate(Timestamp.valueOf(LocalDateTime.of(2027,01,02,01,02,03)))
                .interestRate(9.55f)
                .coefficient(1.00f)
                .coefficientAction("+-")
                .minimumInterestRate(1.00f)
                .minimumInterestRateCoefficient(0.5f)
                .minimumInterestRateCoefficientAction("++-")
                .maximalInterestRate(2.00f)
                .maximalInterestRateCoefficient(0.75f)
                .maximalInterestRateCoefficientAction("--+")
                .build();
    }

    public static List<Agreement> createAgreements(TppProduct tppProduct) {
        var agreements = new ArrayList<Agreement>();

        agreements.add(createAgreement(tppProduct, 2));
        agreements.add(createAgreement(tppProduct, 3));

        return agreements;
    }

    public static AccountPool createAccountPool() {
        return AccountPool.builder()
                .branchCode("002")
                .currencyCode("USD")
                .mdmCode("02")
                .priorityCode("00")
                .registryType(TppRefProductRegisterType.builder().value("03.012.002_47533_ComSoLd").build())
                .build();
    }

    public static Account createAccount(AccountPool accountPool) {
        return Account.builder()
                .accountNumber("47533840000000000001")
                .accountPool(accountPool)
                .bussy(false)
                .build();
    }

    public static TppProductRegister createProductRegister(Account account) {
        return TppProductRegister.builder()
                .productId(1)
                .type(TppRefProductRegisterType.builder().value("03.012.002_47533_ComSoLd").build())
                .currencyCode("USD")
                .state("1")
                .account(account)
                .accountNumber(account.getAccountNumber())
                .build();
    }

    public static AccountResponse correctAccountResponse(){
        return new AccountResponse("1");
    }
    public static String incorrectAccountRequest(){
        return "";
    }
    public static String correctAccountRequest(){
        return "{\n" +
                "\t\"instanceId\": \"1\",\n" +
                "\t\"registryTypeCode\": \"03.012.002_47533_ComSoLd\",\n" +
                "\t\"currencyCode\": \"USD\",\n" +
                "\t\"branchCode\": \"002\",\n" +
                "\t\"priorityCode\": \"00\",\n" +
                "\t\"mdmCode\": \"02\"\n" +
                "}";
    }
    public static String incorrectInstanceRequest(){
        return "";
    }
    public static String correctInstanceRequest(){
        return "{\n" +
                "\"instanceId\": null,\n" +
                "\"productType\": \"НСО\",\n" +
                "\"productCode\": \"02.001.005\",\n" +
                "\"registerType\": \"registerTypeValue\",\n" +
                "\"mdmCode\": \"03\",\n" +
                "\"contractNumber\": \"123456\",\n" +
                "\"contractDate\": \"2024-01-01T01:02:03\",\n" +
                "\"priority\": 1,\n" +
                "\"thresholdAmount\": 1000.11,\n" +
                "\"rateType\": \"0\",\n" +
                "\"taxPercentageRate\": 13.00,\n" +
                "\"contractId\": 9999,\n" +
                "\"branchCode\": \"003\",\n" +
                "\"isoCurrencyCode\": \"RUB\",\n" +
                "\"urgencyCode\": \"00\",\n" +
                "\"instanceArrangement\": [\n" +
                "{\n" +
                "\"generalAgreementId\": \"12345\",\n" +
                "\"supplementaryAgreementId\": \"67890\",\n" +
                "\"arrangementType\": \"arrangementTypeValue\",\n" +
                "\"shedulerJobId\": \"999\",\n" +
                "\"number\": \"contract1\",\n" +
                "\"openingDate\": \"2024-01-01T00:00:00\",\n" +
                "\"closingDate\": \"2025-01-01T00:00:00\",\n" +
                "\"cancelDate\": null,\n" +
                "\"validityDuration\": 5,\n" +
                "\"cancellationReason\": \"cancellationReasonValue\",\n" +
                "\"status\": \"открыт\",\n" +
                "\"interestCalculationDate\": \"2027-01-01T00:00:00\",\n" +
                "\"interestRate\": 9.55,\n" +
                "\"coefficient\": 1.00,\n" +
                "\"coefficientAction\": \"+-\",\n" +
                "\"minimumInterestRate\": 1.00,\n" +
                "\"minimumInterestRateCoefficient\": 0.5,\n" +
                "\"minimumInterestRateCoefficientAction\": \"+-\",\n" +
                "\"maximalnterestRate\": 2.00,\n" +
                "\"maximalnterestRateCoefficient\": 0.7,\n" +
                "\"maximalnterestRateCoefficientAction\": \"-+\"\t\n" +
                "},\n" +
                "{\n" +
                "\"generalAgreementId\": \"12345\",\n" +
                "\"supplementaryAgreementId\": \"67890\",\n" +
                "\"arrangementType\": \"arrangementTypeValue\",\n" +
                "\"shedulerJobId\": \"999\",\n" +
                "\"number\": \"contract2\",\n" +
                "\"openingDate\": \"2024-01-01T00:00:00\",\n" +
                "\"closingDate\": \"2025-01-01T00:00:00\",\n" +
                "\"cancelDate\": null,\n" +
                "\"validityDuration\": 5,\n" +
                "\"cancellationReason\": \"cancellationReasonValue\",\n" +
                "\"status\": \"открыт\",\n" +
                "\"interestCalculationDate\": \"2027-01-01T00:00:00\",\n" +
                "\"interestRate\": 9.55,\n" +
                "\"coefficient\": 1.00,\n" +
                "\"coefficientAction\": \"+-\",\n" +
                "\"minimumInterestRate\": 1.00,\n" +
                "\"minimumInterestRateCoefficient\": 0.5,\n" +
                "\"minimumInterestRateCoefficientAction\": \"+-\",\n" +
                "\"maximalnterestRate\": 2.00,\n" +
                "\"maximalnterestRateCoefficient\": 0.7,\n" +
                "\"maximalnterestRateCoefficientAction\": \"-+\"\t\n" +
                "}\n" +
                "]\n" +
                "}";
    }
    public static InstanceResponse correctInstanceResponse(){
        return new InstanceResponse(1, Arrays.asList(2,3), Arrays.asList(4,5,6));
    }
}
