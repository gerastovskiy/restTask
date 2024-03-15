package ru.cource.springTask.service;

import ru.cource.springTask.model.TppProduct;

public interface TppProductService {
    void checkRecordExist(TppProduct tppProductRequest);
    void save(TppProduct tppProduct);
}
