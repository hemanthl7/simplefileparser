package com.hicx.extractor;

import com.hicx.reader.FileReader;

import java.util.List;
import java.util.stream.Stream;

public class WordsCountExtractor implements Extractor{

    @Override
    public String extract(FileReader reader) {
        long wordsCount = reader.read()
                .map(this::countWords)
                .mapToLong(Long::longValue)
                .sum();
        return "Numbers of words present is:"+wordsCount;
    }

    private long countWords(String line){
        return Stream.of(line.split("\\s"))
                .flatMap(s->Stream.of(s.split("\\.")))
                .count();
    }
}
