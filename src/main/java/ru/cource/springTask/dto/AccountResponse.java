package ru.cource.springTask.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class AccountResponse {
    String accountId;
}
