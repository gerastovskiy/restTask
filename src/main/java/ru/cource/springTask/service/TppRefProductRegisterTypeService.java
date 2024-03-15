package ru.cource.springTask.service;

import ru.cource.springTask.model.TppProductRegister;
import ru.cource.springTask.model.TppRefProductRegisterType;
import java.util.List;

public interface TppRefProductRegisterTypeService {
    TppRefProductRegisterType get(TppProductRegister tppProductRegister);
    List<TppRefProductRegisterType> findAllByProductClass_ValueAndAccountType_Value(TppRefProductRegisterType tppRefProductRegisterType);
}
