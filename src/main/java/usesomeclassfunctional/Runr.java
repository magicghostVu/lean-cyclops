package usesomeclassfunctional;

import cyclops.control.Future;
import cyclops.function.FluentFunctions;
import cyclops.futurestream.LazyReact;
import cyclops.reactive.Spouts;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.Executors;


public class Runr {

    static int q = 0;

    private static int tt() throws Exception {

        System.out.println("okoko");
        if (q == 0) {
            q++;
            throw new IOException();
        } else
            return 1;

    }


    static void do1() {
        var data = Arrays.asList(1, 2, 3, 5, 6, 5, 4, 3, 2, 7, 10);


        var c = Executors.newSingleThreadExecutor();
        // số 6 biểu thị cho sẽ có bao nhiêu thằng gọi trước và chạy xong
        // trước khi gọi map của các thằng phía sau
        var n = Spouts.fromIterable(data).mergeMap(6, integer -> {
            System.out.println("Thread map is " + Thread.currentThread().getName());
            return Future.of(() -> {
                System.out.println("Thread run map is " + Thread.currentThread().getName());
                return integer * 2;
            }, c);
        });

        //n.flatMap(Function.identity());

        n.peek(i -> {
            String msg = String.format("thread is %s, i is %s peek", Thread.currentThread().getName(), i);
            System.out.println(msg);
        }).forEachAsync(ii -> {
            String msg = String.format("thread is %s, i is %s foreach async", Thread.currentThread().getName(), ii);
            System.out.println(msg);
        });


        var lazyReact = new LazyReact(Executors.newCachedThreadPool());
        //MonadicValue

        //Modifier
        //lazyReact.

        lazyReact.fromIterable(data).map(i -> {

            System.out.println("thread map is " + Thread.currentThread().getName());
            return i * 2;
        }).peek(ii -> {
            System.out.println("thread peek is " + Thread.currentThread().getName() + " ii is " + ii);
        }).forEachAsync(iii -> {
            System.out.println("thread foreach is " + Thread.currentThread().getName() + " ii is " + iii);
        });

    }

    static void do2() {
        var gg = FluentFunctions.ofChecked(Runr::tt);
        var y = gg
                .retry(2, 1000)
                .recover(IOException.class, () -> 2)
                .apply();
        System.out.println("i is " + y);
    }

    public static void main(String[] args) {
        do2();
    }
}
