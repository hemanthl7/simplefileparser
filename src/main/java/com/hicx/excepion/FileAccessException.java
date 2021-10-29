package com.hicx.excepion;

public class FileAccessException extends RuntimeException{
    public FileAccessException(String message){
        super(message);
    }
}
