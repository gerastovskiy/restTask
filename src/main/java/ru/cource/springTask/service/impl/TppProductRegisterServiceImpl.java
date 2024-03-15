package ru.cource.springTask.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cource.springTask.model.TppProductRegister;
import ru.cource.springTask.repository.TppProductRegisterRepository;
import ru.cource.springTask.service.TppProductRegisterService;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TppProductRegisterServiceImpl implements TppProductRegisterService {
    private final TppProductRegisterRepository tppProductRegisterRepository;

    @Autowired
    public TppProductRegisterServiceImpl(TppProductRegisterRepository tppProductRegisterRepository) {
        this.tppProductRegisterRepository = tppProductRegisterRepository;
    }

    @Override
    public void checkRecordExist(TppProductRegister tppProductRegisterRequest) {
        var tppProductRegister = tppProductRegisterRepository.find(
                tppProductRegisterRequest.getProductId(),
                tppProductRegisterRequest.getType().getValue());

        if (!(tppProductRegister == null))
            throw new NoSuchElementException("Product registry already exist, id: " + tppProductRegister);
    }

    @Override
    public void save(TppProductRegister tppProductRegister) {
        // TODO: после того как счёт из пула заиспользован его необходимо, наверное, как-то пометить
        // в задании про это ничего не сказано
        tppProductRegisterRepository.save(tppProductRegister);
    }

    @Override
    public void saveAll(List<TppProductRegister> tppProductRegisters) {
        // TODO: после того как счёт из пула заиспользован его необходимо, наверное, как-то пометить
        // в задании про это ничего не сказано
        tppProductRegisterRepository.saveAll(tppProductRegisters);
    }
}
