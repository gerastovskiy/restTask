package ru.cource.springTask.service;

import ru.cource.springTask.model.TppProductRegister;
import java.util.List;

public interface TppProductRegisterService {
    void checkRecordExist(TppProductRegister tppProductRegister);
    void save(TppProductRegister tppProductRegister);
    void saveAll(List<TppProductRegister> tppProductRegisters);
}
