package com.hicx.handler;

import com.hicx.excepion.FileAccessException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileHandler {

    public static void moveFile(File processedDir, File file)  {
        try{
            Path processedFile = processedDir.toPath().resolve(file.getName());
            Files.move(file.toPath(),processedFile);
        }catch (IOException exception){
            throw new FileAccessException("unable to move file");
        }
    }
}
