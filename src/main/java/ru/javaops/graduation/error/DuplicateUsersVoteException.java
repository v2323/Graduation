package ru.javaops.graduation.error;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.http.HttpStatus;

import static org.springframework.boot.web.error.ErrorAttributeOptions.Include.MESSAGE;

public class DuplicateUsersVoteException extends AppException {
    public DuplicateUsersVoteException(String msg) {
        super(HttpStatus.UNPROCESSABLE_ENTITY, msg, ErrorAttributeOptions.of(MESSAGE));
    }
}
