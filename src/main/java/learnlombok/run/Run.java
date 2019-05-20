package learnlombok.run;

import learnlombok.someadataclass.Person;

public class Run {
    public static void main(String[] args) {
        var p = Person.builder()
                .name("phuvh")
                .age(24)
                .build();
        System.out.println(p.getAge());
    }
}
