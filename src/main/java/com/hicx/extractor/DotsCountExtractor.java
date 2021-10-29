package com.hicx.extractor;

import com.hicx.reader.FileReader;

public class DotsCountExtractor implements Extractor{

    @Override
    public String extract(FileReader reader) {
        long numberOfDots = reader.read()
                .map(this::countDotsInLine)
                .mapToLong(Long::intValue).sum();
        return "Number of Dots present is:"+numberOfDots;
    }

    private long countDotsInLine(String line){
        return line.chars().filter(c->c == '.').count();
    }
}
