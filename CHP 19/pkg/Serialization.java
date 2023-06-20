package pkg;

import java.io.*;
import java.nio.file.Path;

public class Serialization {
    public static void main(String... args) throws IOException {
        var f = new File(System.getProperty("user.dir"), "student.ser");
        saveToFile(new Student(), f);
        System.out.println(readFromFile(f));
    }

    private static void saveToFile(Student s, File file) throws IOException {
        try(var out = new ObjectOutputStream(
            new BufferedOutputStream(
                new FileOutputStream(
                    file
                )
            )
        )){
            out.writeObject(s);
        }
    }

    private static Student readFromFile(File file) throws IOException {
        Student s = null;
        try(var in = new ObjectInputStream(
            new BufferedInputStream(
                new FileInputStream(
                    file
                )
            )
        )){
            s = (Student) in.readObject();
        } catch (EOFException | ClassNotFoundException e){
            
        }

        return s;
    }
}

class Student implements Serializable {
    public static final long serialVersionUID = 1;
    public String name = "Bruce";
    public String grade = "A";

    public String toString(){ return "["+name+", "+grade+"]"; }
}