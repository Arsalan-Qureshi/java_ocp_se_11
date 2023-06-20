import java.util.*;
import java.util.stream.Stream;
import java.nio.*;
import java.nio.file.*;
import java.sql.PreparedStatement;
import java.sql.Types;
import java.util.function.*;
import java.io.*;

public class Runner {
  public static void main(String[] args){
    var c  = new Child();
    System.out.println(c.x);
  }
 }

 class Parent{
  int x = 10;
 }

 class Child extends Parent{

 }