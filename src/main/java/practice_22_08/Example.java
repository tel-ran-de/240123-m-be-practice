package practice_22_08;

import java.util.ArrayList;
import java.util.List;

public class Example {

    public static void main(String[] args) {
        List<String> a = new ArrayList<>();
        a.add("123");
        foo(a);

        System.out.println(a);
    }

    private static void foo(List<String> a) {
        a.set(0, "foo");
    }
}
