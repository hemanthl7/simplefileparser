package com.hicx;

import com.hicx.processor.FileProcessor;

import java.nio.file.*;


public class Starter
{
    private static WatchService watchService;

    public static void main( String[] args )
    {
        String path;
        try {
            path = args[0];
            start(path);
        }catch (IndexOutOfBoundsException ex){
            System.out.println("no arguments,please specify the directory to process files");
        }catch (Exception ex){
            System.out.println("Unable Watch the directory");
        }
    }
    public static void start(String path) throws Exception {
        watchService = FileSystems.getDefault().newWatchService();
        Path parentPath = Paths.get(path);
        FileProcessor fileProcessor = new FileProcessor(path);
        parentPath.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);
        WatchKey key;
        while(( key = watchService.take())!=null){
            for (WatchEvent<?> event:key.pollEvents()){
                Path changed = ((Path)event.context());
                Path absolutePath = parentPath.resolve(changed);
                if(!Files.isDirectory(absolutePath)){
                   processFile(fileProcessor,absolutePath);
                }

            }
            key.reset();
        }
    }

    private static void processFile(FileProcessor fileProcessor,Path path){
        try{
            fileProcessor.process(path);
        }catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static WatchService getWatchService(){
        return watchService;
    }

}
