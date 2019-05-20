package learnlombok.someadataclass;

import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class Person {
    private int age;
    private String name;


}/*public static void main(String[] args) {
        Person p = new Person();
        p.getAge();
    }*/
