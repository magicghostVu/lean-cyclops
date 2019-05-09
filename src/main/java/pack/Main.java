package pack;


import cyclops.control.Future;
import mutils.MUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.function.Function;
import java.util.stream.Stream;


public class Main {


    public static void main(String[] args) {
        var t = 9;
        var executor = Executors.newCachedThreadPool();
        /*Future<String> tt = Future.of(() -> {
            return "dfhfg";
        }, executor);*/


        var folderLog = new File("D:\\log_files");

        var allChild = folderLog.listFiles();


        List<Future<Integer>> allTask = new ArrayList<>();

        for (var tmpF : allChild) {
            var fi = Future.of(() -> {
                Stream<String> streamAll = MUtils.streamAllStringInFile(tmpF.getAbsolutePath());
                return MUtils.countLineInStream(streamAll);
            }, executor);

            allTask.add(fi);
        }

        var r= Future.sequence(allTask).concatMap(Function.identity());

    }
}
