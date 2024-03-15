package ru.cource.springTask.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import java.util.List;

@AllArgsConstructor
@Builder
@Data
public class InstanceResponse {
    Integer instanceId;
    List<Integer> registerId;
    List<Integer> supplementaryAgreementId;
}
