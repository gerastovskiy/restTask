package ru.cource.springTask.service;

import ru.cource.springTask.dto.InstanceRequest;
import ru.cource.springTask.dto.InstanceResponse;

public interface InstanceService {
    InstanceResponse processRequest(InstanceRequest request);
}
