package ru.cource.springTask.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cource.springTask.model.TppProduct;
import ru.cource.springTask.repository.TppProductRepository;
import ru.cource.springTask.service.TppProductService;
import java.util.NoSuchElementException;

@Service
public class TppProductServiceImpl implements TppProductService {
    private final TppProductRepository tppProductRepository;

    @Autowired
    public TppProductServiceImpl(TppProductRepository tppProductRepository) {
        this.tppProductRepository = tppProductRepository;
    }

    @Override
    public void checkRecordExist(TppProduct tppProductRequest) {
        final TppProduct tppProduct;

        if (tppProductRequest.getId() == null) {
            tppProduct = tppProductRepository.findByNumber(
                    tppProductRequest.getNumber());

            if (!(tppProduct == null))
                throw new NoSuchElementException("Product already exist, id: " + tppProduct);
        } else {
            tppProduct = tppProductRepository.findById(
                    tppProductRequest.getId()).get();

            if (tppProduct == null)
                throw new NoSuchElementException("Product with given instanceId not found");
        }
    }

    @Override
    public void save(TppProduct tppProduct) {
        tppProductRepository.save(tppProduct);
    }
}
