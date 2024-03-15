package ru.cource.springTask.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cource.springTask.model.TppProductRegister;
import ru.cource.springTask.model.TppRefProductRegisterType;
import ru.cource.springTask.repository.TppRefProductRegisterTypeRepository;
import ru.cource.springTask.service.TppRefProductRegisterTypeService;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TppRefProductRegisterTypeServiceImpl implements TppRefProductRegisterTypeService {
    private final TppRefProductRegisterTypeRepository tppRefProductRegisterTypeRepository;

    @Autowired
    public TppRefProductRegisterTypeServiceImpl(TppRefProductRegisterTypeRepository tppRefProductRegisterTypeRepository) {
        this.tppRefProductRegisterTypeRepository = tppRefProductRegisterTypeRepository;
    }

    @Override
    public TppRefProductRegisterType get(TppProductRegister tppProductRegister) {
        var tppRefProductRegisterType = tppRefProductRegisterTypeRepository.findByValue(
                tppProductRegister.getType().getValue());

        if (tppRefProductRegisterType == null)
            throw new NoSuchElementException("Product registry type not found");

        tppProductRegister.setType(tppRefProductRegisterType);

        return tppRefProductRegisterType;
    }

    @Override
    public List<TppRefProductRegisterType> findAllByProductClass_ValueAndAccountType_Value(TppRefProductRegisterType tppRefProductRegisterType) {
        var tppRefProductRegisterTypes = tppRefProductRegisterTypeRepository.findAllByProductClass_ValueAndAccountType_Value(
                tppRefProductRegisterType.getProductClass().getValue(),
                tppRefProductRegisterType.getAccountType().getValue()
        );

        if (tppRefProductRegisterTypes.isEmpty())
            throw new NoSuchElementException("Product registry type not found");

        return tppRefProductRegisterTypes;
    }
}
