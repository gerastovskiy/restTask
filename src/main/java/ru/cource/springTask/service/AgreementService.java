package ru.cource.springTask.service;

import ru.cource.springTask.model.Agreement;
import java.util.List;

public interface AgreementService {
    void checkRecordsExist(List<Agreement> agreementsRequest);
    void saveAll(List<Agreement> agreements);
}
