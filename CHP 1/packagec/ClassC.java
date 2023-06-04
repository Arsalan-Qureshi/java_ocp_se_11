package packagec;

import packagea.ClassA;

public class ClassC extends ClassA {
    public static void main(String... args){
        ClassA obj = new ClassA();
        // variable a can not be directly accessed here via a ref of type ClassA.
        // obj.a = 10; // error: a has protected access in ClassA

        ClassC objC = new ClassC();
        objC.a = 10; //compiles
    }
}