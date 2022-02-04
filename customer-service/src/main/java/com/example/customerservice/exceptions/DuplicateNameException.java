package com.example.customerservice.exceptions;

import java.sql.SQLIntegrityConstraintViolationException;

public class DuplicateNameException extends SQLIntegrityConstraintViolationException {

    public DuplicateNameException(String name) {
        super("Customer name is duplicated: " + name);
    }
}
