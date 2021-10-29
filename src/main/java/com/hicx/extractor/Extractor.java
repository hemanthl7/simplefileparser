package com.hicx.extractor;

import com.hicx.reader.FileReader;

public interface Extractor {

    String extract(FileReader reader);
}
