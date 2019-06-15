package notnullassign;

import cyclops.control.Maybe;

import java.util.ArrayList;

public class RunTest {
    public static void main(String[] args) {
        var t = new ArrayList<Integer>();

        // hãy nhớ cú pháp này khi dùng 1 hàm cần có tham số kiểu trong ngữ cảnh static
        var uu = Maybe.<Integer>maybe();

    }
}
