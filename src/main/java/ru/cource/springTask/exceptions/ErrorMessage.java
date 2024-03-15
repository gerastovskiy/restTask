package ru.cource.springTask.exceptions;

import lombok.*;
import java.sql.Timestamp;
import java.util.Date;

@Getter
public class ErrorMessage {
    private final Timestamp timestamp = new Timestamp(new Date().getTime());
    private final String error;
    public ErrorMessage(String error) {
        this.error = error;
    }
}
