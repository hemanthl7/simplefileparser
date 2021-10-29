package com.hicx.processor;

import com.hicx.handler.FileHandler;
import com.hicx.extractor.DotsCountExtractor;
import com.hicx.extractor.Extractor;
import com.hicx.extractor.MostUsedWordExtractor;
import com.hicx.extractor.WordsCountExtractor;
import com.hicx.reader.FileReader;
import com.hicx.reader.FileReaderSupport;

import java.io.File;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class FileProcessor {

    private final File processedDir;

    private final List<Extractor> extractors = Arrays.asList(new WordsCountExtractor(),
            new DotsCountExtractor(),
            new MostUsedWordExtractor());

    public FileProcessor(String path){
        processedDir = new File(path,"processed");
        if(!processedDir.exists()){
            processedDir.mkdir();
        }
    }
    public void process(Path path) {
            FileReaderSupport fileReaderSupport = new FileReaderSupport(path);
            FileReader fileReader = fileReaderSupport.getFileReader();
            extractStatistics(fileReader);
            moveFile(path.toFile());
    }

    private void moveFile(File file)  {
        FileHandler.moveFile(processedDir,file);
    }

    private void extractStatistics(FileReader fileReader){
        extractors.stream().map(extractor->extractor.extract(fileReader))
                .forEach(System.out::println);
    }
}
