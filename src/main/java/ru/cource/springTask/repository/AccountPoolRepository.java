package ru.cource.springTask.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.cource.springTask.model.AccountPool;
import ru.cource.springTask.model.TppRefProductRegisterType;

@Repository
public interface AccountPoolRepository extends CrudRepository<AccountPool, Integer> {
    @Query("select a from AccountPool a where a.branchCode = ?1 and a.currencyCode = ?2 and a.mdmCode = ?3 and a.priorityCode = ?4 and a.registryType = ?5")
    AccountPool find(String branchCode, String currencyCode, String mdmCode, String priorityCode, TppRefProductRegisterType registryTypeCode);
}