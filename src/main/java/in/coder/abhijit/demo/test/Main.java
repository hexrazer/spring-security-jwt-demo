package in.coder.abhijit.demo.test;

import java.util.stream.Stream;
import java.util.Arrays;

class Main {
    public static void main(String[] args) {
        String[] arrayOfWords = { "Goodbye", "World" };
        Stream<String> streamOfwords = Arrays.stream(arrayOfWords);
        System.out.println("hola");

        String role = "DA_REC";
        System.out.println(role.endsWith("REc"));
    }
}