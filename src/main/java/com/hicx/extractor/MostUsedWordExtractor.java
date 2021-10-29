package com.hicx.extractor;

import com.hicx.reader.FileReader;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class MostUsedWordExtractor implements Extractor{

    @Override
    public String extract(FileReader reader) {
        Map<String, Long> wordsCountMap =
                reader.read()
                        .flatMap(this::words)
                        .collect(groupingBy(Function.identity(), counting()));
        long value = 0L;
        String word = null;
        for (Map.Entry<String,Long> wordsEntry:wordsCountMap.entrySet()){
            long count = wordsEntry.getValue();
            if(value<count){
                value = count;
                word = wordsEntry.getKey();
            }
        }
        return "Most Used word is:"+word;
    }

    private Stream<String> words(String line){
        return Stream.of(line.split("\\s"))
                .flatMap(s->Stream.of(s.split("\\.")));
    }
}
