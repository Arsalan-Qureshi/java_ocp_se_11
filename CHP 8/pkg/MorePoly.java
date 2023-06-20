package pkg;

public class MorePoly{
    public static void main(String... args){
        A a = new A();
        B b = new B(); 

        print(((I)b).getClass()); // Type remains B only the reference by which we can call B gets changed by I.
        a = (B)(I)b; // a = new B() basically.
        a = new A();

        // print(((B)(I)a).getClass()); // Throws an error as A cannot be cast to B.
    }

    private static void print(Object o){
        System.out.println(o);
    }
}

interface I{
}

class A implements I{
}

class B extends A {
}

class C extends B{
}
