package com.lucashelionel.dscommerce.services.exceptions;
// usa-se esse extends pois esse RuntimeException não exige o bloco try catch
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String msg) {

        super(msg);
    }
}
