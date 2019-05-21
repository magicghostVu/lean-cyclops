package learnlombok.run;

import learnlombok.someadataclass.Person;
import lombok.extern.java.Log;

@Log
public class Run {
    public static void main(String[] args) {
        var p = Person.builder()
                .age(24)
                .name("Phuvh")
                .build();
        log.info(p.getName());
    }
}
