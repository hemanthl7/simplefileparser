package com.hicx.reader;

import java.util.stream.Stream;

public interface FileReader {

    Stream<String> read();
}
