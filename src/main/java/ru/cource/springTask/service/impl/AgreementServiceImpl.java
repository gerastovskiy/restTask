package ru.cource.springTask.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cource.springTask.model.Agreement;
import ru.cource.springTask.repository.AgreementRepository;
import ru.cource.springTask.service.AgreementService;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AgreementServiceImpl implements AgreementService {
    private final AgreementRepository agreementRepository;

    @Autowired
    public AgreementServiceImpl(AgreementRepository agreementRepository) {
        this.agreementRepository = agreementRepository;
    }

    @Override
    public void checkRecordsExist(List<Agreement> agreementsRequest) {
        agreementsRequest.forEach(agreementRequest -> {
            var agreement = agreementRepository.findByNumber(
                     agreementRequest.getNumber());

            if (!(agreement == null))
                throw new NoSuchElementException("Agreement already exist, id: " + agreement);
            });
    }

    @Override
    public void saveAll(List<Agreement> agreements) {
        agreementRepository.saveAll(agreements);
    }
}
