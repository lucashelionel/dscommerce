package com.lucashelionel.dscommerce.services.exceptions;
// usa-se esse extends pois esse RuntimeException não exige o bloco try catch
public class DatabaseException extends RuntimeException {
    public DatabaseException(String msg) {

        super(msg);
    }
}
