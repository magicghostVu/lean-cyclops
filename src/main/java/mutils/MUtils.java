package mutils;

import org.apache.commons.lang3.mutable.MutableInt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.stream.Stream;

public class MUtils {
    public static Stream<String> streamAllStringInFile(String path) {
        try {
            var f = new File(path);
            var reader = new FileReader(f);
            var bufferedReader = new BufferedReader(reader);
            return bufferedReader.lines();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<String>().stream();
        }
    }

    public static int countLineInStream(Stream<String> s) {
        var count = new MutableInt();
        s.forEach(str -> count.increment());
        return count.intValue();
    }
}
