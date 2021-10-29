package com.hicx.processor;

import com.hicx.FileHandler;
import com.hicx.excepion.FileAccessException;
import com.hicx.excepion.UnsupportedFileException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.nio.file.Paths;

public class FileProcessorTest {

    private FileProcessor fileProcessor;

    private static final String TEST_FILES_PATH = "src/test/resources/testfiles/";

    @Before
    public void setup(){
        fileProcessor = new FileProcessor("src/test/resources/testfiles/");
    }

    @After
    public void teardown(){
        File processedDir = new File(TEST_FILES_PATH,"processed");
        File[] filePresentInDir = processedDir.listFiles();
        if(filePresentInDir!=null){
            for (File file: filePresentInDir){
                FileHandler.moveFile(new File(TEST_FILES_PATH),file);
            }
        }

    }
    @Test
    public void testWhenFileIsNotAvailable() {
        Assert.assertThrows(FileAccessException.class,()->fileProcessor.process(Paths.get(TEST_FILES_PATH+"ext.txt")));

    }

    @Test
    public void testWhenFileTypeNotSupported() {
        Assert.assertThrows(UnsupportedFileException.class,()->fileProcessor.process(Paths.get(TEST_FILES_PATH+"sample.pdf")));
    }

    @Test
    public void testWhenSuccess(){
        fileProcessor.process(Paths.get(TEST_FILES_PATH+"sample.txt"));
    }


}