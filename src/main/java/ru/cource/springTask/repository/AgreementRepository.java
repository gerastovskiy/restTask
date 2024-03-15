package ru.cource.springTask.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.cource.springTask.model.Agreement;

@Repository
public interface AgreementRepository extends CrudRepository<Agreement, Integer> {
    Agreement findByNumber(String value);
}
