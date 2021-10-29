package com.hicx.reader;

import com.hicx.excepion.FileAccessException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public class TextFileReader implements FileReader{
    private Path path;
    public TextFileReader(Path path){
        this.path = path;
    }
    @Override
    public Stream<String> read()  {
        try {
            return Files.lines(path);
        } catch (IOException e) {
            throw new FileAccessException("Unable to Read the File or File Not Found");
        }
    }
}
