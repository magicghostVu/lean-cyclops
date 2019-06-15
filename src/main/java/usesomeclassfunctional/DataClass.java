package usesomeclassfunctional;

import cyclops.control.Eval;
import cyclops.control.Maybe;
import cyclops.control.Option;
import lombok.Getter;
import lombok.NonNull;


@Getter
public class DataClass {
    @NonNull
    private Integer age;
    @NonNull
    private String name;

    private Maybe<String> address;

    private Eval<String> evalAddress;

    public DataClass(int age, String name) {
        this.age = age;
        this.name = name;
        address = Maybe.fromEval(Eval.later(this::supplyAddress));



        //address.fold()
        evalAddress = Eval.later(this::supplyAddress);

        //System.out.println();
    }

    public String supplyAddress() {
        //todo: do something to get add here
        System.out.println("call get add");
        return "36 Van Cao, Lieu Giai, Ba Dinh, Ha Noi";
    }
}
