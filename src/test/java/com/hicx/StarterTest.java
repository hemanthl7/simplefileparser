package com.hicx;

import com.hicx.handler.FileHandler;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.WatchService;

public class StarterTest
{

    private static final String TEST_FILES_PATH = "src/test/resources/testfiles/";

    private File processedFile = new File(TEST_FILES_PATH+"processed","sample.txt");

    private File testFile = new File(TEST_FILES_PATH,"sample.txt");
    private WatchService watchService;

    @After
    public void teardown() throws IOException {

        moveFileToWatch();
    }



    @Test
    public void testWhenSuccess() throws Exception {
        Thread thread = new Thread(this::testThread);
        thread.start();
        Starter.main(new String[]{testFile.getParent()});
        watchService = Starter.getWatchService();
        Assert.assertTrue("File processed successfully",processedFile.exists());

    }

    private void testThread() {
        try{
            Thread.sleep(100);
            moveFileToWatch();
            Thread.sleep(1000);
            watchService = Starter.getWatchService();
            watchService.close();
        }catch (Exception ignored){

        }
    }

    private void moveFileToWatch(){

        if(!processedFile.getParentFile().exists()){
            processedFile.getParentFile().mkdir();
        }

        if(testFile.exists()){
            FileHandler.moveFile(processedFile.getParentFile(), testFile);
        }
        if(processedFile.exists()){
            FileHandler.moveFile(testFile.getParentFile(),processedFile);
        }
    }

    @Test
    public void testWhenNoArgumentsPassed(){
        Starter.main(new String[0]);
    }
}
