package ru.cource.springTask.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.cource.springTask.model.TppProduct;

@Repository
public interface TppProductRepository extends CrudRepository<TppProduct, Integer> {
    TppProduct findByNumber(String value);
}
