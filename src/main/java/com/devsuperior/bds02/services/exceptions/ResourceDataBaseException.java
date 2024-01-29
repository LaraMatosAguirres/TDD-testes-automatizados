package com.devsuperior.bds02.services.exceptions;

public class ResourceDataBaseException extends RuntimeException{
    private static final long serialVersion = 1L;

    public ResourceDataBaseException(String msg){
        super(msg);
    }
}
