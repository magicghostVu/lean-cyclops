package splittextfile;

import cyclops.control.Either;
import cyclops.control.Try;
import cyclops.function.FluentFunctions;
import cyclops.futurestream.LazyReact;
import cyclops.reactive.IO;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class RunSplit {

    public static Stream<String> getStreamString(String path) throws IOException {
        var f = new File(path);
        var fr = new FileReader(f);
        var bf = new BufferedReader(fr);
        return bf.lines();
    }

    private static Either<Exception, Stream<String>> getStreamStringFromFile(String path) {
        try {
            var f = new File(path);
            var fr = new FileReader(f);
            var bf = new BufferedReader(fr);
            return Either.right(bf.lines());
        } catch (Exception e) {
            //e.printStackTrace();
            return Either.left(e);
        }
    }


    public static int addOne(int a) {
        return a + 1;
    }

    public static void useTryWithResource(String path) {
        var f = FluentFunctions.of(RunSplit::addOne);
        f = f.name("muFunc").memoize();

        int y = f.apply(9);


        //Try.withResources()
    }

    static void do1() {
        var h = Try.withCatch(() -> getStreamString("kjndksfn"), IOException.class);

        if (h.isFailure()) {
            h.failureGet().orElse(new IOException()).printStackTrace();
        } else {
            h.get().orElse(Stream.empty());
        }
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        int splitParts = 10;
        var path = "/Users/phuvh/Desktop/CCN.log.2019-06-01-08";
        var eitherStreamEx = getStreamStringFromFile(path);

        var numLine = 0L;
        if (eitherStreamEx.isRight()) {
            numLine = eitherStreamEx.orElse(Stream.empty()).count();
        } else {
            eitherStreamEx
                    .getLeft()
                    .orElseGet(Exception::new)
                    .printStackTrace();

            System.out.println("done with err");
            return;
        }


        var numLineAPart = numLine / splitParts;

        var listIndex = new ArrayList<Pair<Long, Long>>();

        var n = numLine;

        var l = IntStream.range(0, splitParts).boxed().map(i -> {
            long start = i * n;
            long end = (i + 1) * n - 1;
            // phần tử cuối
            if (i == splitParts - 1) {
                end = n - 1;
            }
            return new ImmutablePair<>(start, end);
        }).collect(Collectors.toList());


        var ex = Executors.newFixedThreadPool(4);
        var lz = new LazyReact(ex);


    }
}
