package com.hicx.excepion;

public class UnsupportedFileException extends RuntimeException {
    public UnsupportedFileException(String message){
        super(message);
    }
}
