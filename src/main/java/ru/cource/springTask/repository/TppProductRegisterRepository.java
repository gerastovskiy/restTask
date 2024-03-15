package ru.cource.springTask.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.cource.springTask.model.TppProductRegister;

@Repository
public interface TppProductRegisterRepository extends CrudRepository<TppProductRegister, Integer> {
    @Query("select t from TppProductRegister t where t.productId = ?1 and t.type.value = ?2")
    TppProductRegister find(Integer productId, String type);
}