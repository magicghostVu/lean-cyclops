package learnlombok.someadataclass;

import lombok.Builder;
import lombok.Getter;

@Builder(toBuilder = true)
@Getter
public class Person {
    private int age;
    private String name;

}
