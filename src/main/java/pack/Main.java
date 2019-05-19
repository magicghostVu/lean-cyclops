package pack;


import cyclops.control.Future;
import mutils.MUtils;
import org.apache.commons.lang3.mutable.MutableInt;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.stream.Stream;


public class Main {


    public static void main(String[] args) {
        var t = 9;
        var executor = Executors.newCachedThreadPool();
        var folderLog = new File("D:\\log_files");

        var allChild = folderLog.listFiles();
        var allTask = new ArrayList<Future<Integer>>();

        for (var tmpF : allChild) {
            var fi = Future.of(() -> {
                Stream<String> streamAll = MUtils.streamAllStringInFile(tmpF.getAbsolutePath());
                return MUtils.countLineInStream(streamAll);
            }, executor);

            allTask.add(fi);
        }

        var r = Future.sequence(allTask);

        r.toCompletableFuture().thenAccept(tt -> {
            var countAll = new MutableInt();
            tt.forEach(numLine -> {
                System.out.println("numline is " + numLine);
                countAll.add(numLine);
            });
            System.out.println("all line is " + countAll.intValue());
        });

        System.out.println("done");

    }
}
