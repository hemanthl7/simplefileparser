package com.hicx.reader;

import com.hicx.excepion.FileAccessException;
import com.hicx.excepion.UnsupportedFileException;

import javax.activation.MimeType;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileReaderSupport {
    private final Path path;

    public FileReaderSupport(Path path){
        this.path = path;
    }

    public FileReader getFileReader()  {
        String mimeType = findMimeType(path);
        if("text/plain".equals(mimeType)){
            return new TextFileReader(path);
        }else {
            throw new UnsupportedFileException("File Not supported");
        }
    }
    private String findMimeType(Path path){
        try{
            return Files.probeContentType(path);
        }catch (IOException exception){
            throw new FileAccessException("unable to read the file");
        }
    }
}
