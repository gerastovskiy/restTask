package ru.cource.springTask.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.cource.springTask.model.TppRefProductRegisterType;
import java.util.List;

@Repository
public interface TppRefProductRegisterTypeRepository extends CrudRepository<TppRefProductRegisterType, Integer> {
    TppRefProductRegisterType findByValue(String value);
    List<TppRefProductRegisterType> findAllByProductClass_ValueAndAccountType_Value(String productClassValue, String accountTypeValue);
}