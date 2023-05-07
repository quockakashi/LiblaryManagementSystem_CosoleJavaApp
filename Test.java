package libraryManagement;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
class MyClass {
    private int a;
    MyClass(int n) {
        a = n;
    }

    void increase1() {
        a += 1;
    }

    @Override
    public String toString() {
        return Integer.toString(a);
    }
}

public class Test {
    public static void main(String[] args) {
        LocalDateTime localDateTime = null;
        var formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm");
        System.out.println((localDateTime== null ? "No" : formatter.format(localDateTime)));
    }
}
