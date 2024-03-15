package ru.cource.springTask.mapper;

import ru.cource.springTask.dto.AccountRequest;
import ru.cource.springTask.dto.AccountResponse;
import ru.cource.springTask.dto.InstanceRequest;
import ru.cource.springTask.dto.InstanceResponse;
import ru.cource.springTask.model.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// TODO: изучить детали маппера
// https://rukovodstvo.net/posts/id_799/
// https://ru.hexlet.io/courses/java-spring/lessons/dto-mapper/theory_unit
//@Mapper(
//        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
//        componentModel = MappingConstants.ComponentModel.SPRING,
//        unmappedTargetPolicy = ReportingPolicy.IGNORE
//)
public class PostMapper {
    public static AccountPool mapToAccountPool(AccountRequest accountRequest) {
        return AccountPool.builder()
                .branchCode(accountRequest.getBranchCode())
                .currencyCode(accountRequest.getCurrencyCode())
                .mdmCode(accountRequest.getMdmCode())
                .priorityCode(accountRequest.getPriorityCode())
                .registryType(TppRefProductRegisterType.builder().value(accountRequest.getRegistryTypeCode()).build())
                .build();
    }

    public static List<AccountPool> mapToAccountPool(InstanceRequest instanceRequest, List<TppRefProductRegisterType> tppProductRegisters) {
        var accountPool = new ArrayList<AccountPool>();

        tppProductRegisters.forEach(productRegisterType -> accountPool.add(
                AccountPool.builder()
                        .branchCode(instanceRequest.getBranchCode())
                        .currencyCode(instanceRequest.getIsoCurrencyCode())
                        .mdmCode(instanceRequest.getMdmCode())
                        .priorityCode(instanceRequest.getUrgencyCode())
                        .registryType(productRegisterType)
                        .build()
        ));

        return accountPool;
    }

    public static TppProductRegister mapToTppProductRegister(AccountRequest accountRequest) {
        var tppProductRegister = TppProductRegister.builder()
                .productId(accountRequest.getInstanceId())
                .type(new TppRefProductRegisterType())
                .currencyCode(accountRequest.getCurrencyCode())
                .state("1")
                .build();

        tppProductRegister.getType().setValue(accountRequest.getRegistryTypeCode());

        return tppProductRegister;
    }

    public static TppProductRegister mapToTppProductRegister(TppProductRegister tppProductRegister, Account account) {
        tppProductRegister.setAccount(account);
        tppProductRegister.setAccountNumber(account.getAccountNumber());

        return tppProductRegister;
    }

    public static List<TppProductRegister> mapToTppProductRegisters(TppProduct tppProduct, List<Account> accounts) {
        var productRegisters = new ArrayList<TppProductRegister>();

        accounts.forEach(account -> productRegisters.add(TppProductRegister.builder()
                        .productId(tppProduct.getId())
                        .type(account.getAccountPool().getRegistryType())
                        .currencyCode(account.getAccountPool().getCurrencyCode())
                        .state("1")
                        .build()));

        return productRegisters;
    }

    public static AccountResponse mapToAccountResponse(TppProductRegister tppProductRegister) {
        return new AccountResponse(String.valueOf(tppProductRegister.getId()));
    }

    public static TppProduct mapToTppProduct(InstanceRequest instanceRequest) {
        return TppProduct.builder()
                .id(instanceRequest.getInstanceId())
                .type(instanceRequest.getProductType())
                .number(instanceRequest.getContractNumber())
                .startDateTime(instanceRequest.getContractDate())
                .priority(instanceRequest.getPriority())
                .thresholdAmount(instanceRequest.getThresholdAmount())
                .interestRateType(instanceRequest.getRateType())
                .taxRate(instanceRequest.getTaxPercentageRate())
                .build();
    }

    public static List<Agreement> mapToAgreement(InstanceRequest instanceRequest, TppProduct tppProduct) {
        var agreements = new ArrayList<Agreement>();

        instanceRequest.getInstanceArrangement().forEach(agreement -> agreements.add(
                Agreement.builder()
                        .productId(tppProduct)
                        .generalAgreementId(agreement.getGeneralAgreementId())
                        .supplementaryAgreementId(agreement.getSupplementaryAgreementId())
                        .arrangementType(agreement.getArrangementType())
                        .shedulerJobId(agreement.getShedulerJobId())
                        .number(agreement.getNumber())
                        .openingDate(agreement.getOpeningDate())
                        .closingDate(agreement.getClosingDate())
                        .cancelDate(agreement.getCancelDate())
                        .validityDuration(agreement.getValidityDuration())
                        .cancellationReason(agreement.getCancellationReason())
                        .status(agreement.getStatus())
                        .interestCalculationDate(agreement.getInterestCalculationDate())
                        .interestRate(agreement.getInterestRate())
                        .coefficient(agreement.getCoefficient())
                        .coefficientAction(agreement.getCoefficientAction())
                        .minimumInterestRate(agreement.getMinimumInterestRate())
                        .minimumInterestRateCoefficient(agreement.getMinimumInterestRateCoefficient())
                        .minimumInterestRateCoefficientAction(agreement.getMinimumInterestRateCoefficientAction())
                        .maximalInterestRate(agreement.getMaximalnterestRate())
                        .maximalInterestRateCoefficient(agreement.getMaximalnterestRateCoefficient())
                        .maximalInterestRateCoefficientAction(agreement.getMaximalnterestRateCoefficientAction())
                        .build()
                        ));

        return agreements;
    }

    public static InstanceResponse mapToInstanceResponse(TppProduct tppProduct, List<TppProductRegister> tppProductRegisters, List<Agreement> agreements) {
        return new InstanceResponse(tppProduct.getId(),
                tppProductRegisters.stream().map(TppProductRegister::getId).collect(Collectors.toList()),
                agreements.stream().map(Agreement::getId).collect(Collectors.toList()));
    }

    public static TppRefProductRegisterType mapToTppRefProductRegisterType(InstanceRequest instanceRequest){
        return TppRefProductRegisterType.builder().
                productClass(TppRefProductClass.builder().value(instanceRequest.getProductCode()).build()).
                accountType(TppRefAccountType.builder().value("Клиентский").build())
                .build();
    }
}
