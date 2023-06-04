package pkg;

import java.io.*;
import java.nio.Buffer;

/*
 * Root directory in Windows = c:\ in Linux = /
 */
public class Runner {
    public static void main(String... args){
        // new Runner().file();

        new Runner().streams();
    }

    /*
     * Used to read information about existing files and directories, list directory contents, and create/delete files and directories.
     * Relative path is from pwd to specified file, whereas absolte path is from the root directory.
     */
    private void file(){
        print(System.getProperty("file.separator")); // \
        print(java.io.File.separator); // \

        // File Creation
        print(new File("c:\\")); // c:\
        print(new File(new File("c:\\"), "home")); // c:\home
        print(new File("d:\\", "play")); // d:\play

        // Usage
        var f = new File(System.getProperty("user.dir"), "sample");
        print(f.mkdir()); // true - Creates specified directory.
        print(f.exists()); // true
        print(f.getAbsolutePath()); // F:\OCP 1Z0-819\java_ocp_se_11\CHP 19\sample
        print(f.getName()); // sample
        print(f.getParent()); // F:\OCP 1Z0-819\java_ocp_se_11\CHP 19
        print(f.isDirectory()); //true
        print(f.isFile()); //false
        print(f.lastModified()); // 1685888340192
        print(f.length()); // 0 - Number of bytes in file.
        print(f.listFiles()); //Return File[] within the directory.
        print(f.renameTo(new File(System.getProperty("user.dir"), "mySample"))); // true
        // print(f.delete());
    }

    private void streams(){
        var f = new File(System.getProperty("user.dir"), "sample.txt");
        // try(var br = new BufferedReader(new FileReader(f))){
        //     print(br.readLine());
        // } catch(IOException e){
        //     print(e.getMessage());
        // } 

        // Copying.
        // One byte at a time.
        var out = new File(System.getProperty("user.dir"), "output.txt");
        // try(var i = new FileInputStream(f); var o = new FileOutputStream(out)){
        //     copy(i, o);
        // } catch(IOException e){
        //     print(e.getMessage());
        // }
        
        // Copying with a buffer.
        // Output file will need to exist before being copied to.
        try(var i = new BufferedInputStream(new FileInputStream(f)); var o = new BufferedOutputStream(new FileOutputStream(out))){
            copyWithBuffer(i, o);
        } catch(IOException e){
            print(e.getMessage());
        }
    }

    // Covariant parameter got accepted.
    private void copy(InputStream in, OutputStream out) throws IOException {
        int b;
        while((b = in.read()) != -1){
            out.write(b);
        }
    }

    private void copyWithBuffer(BufferedInputStream bi, BufferedOutputStream bo) throws IOException{
        var buffer = new byte[1024];
        int lenRead;
        while((lenRead = bi.read(buffer)) > 0){
            bo.write(buffer, 0, lenRead);
            bo.flush();
        }
    }

    /*
     * PrintStream and PrintWriter classes do not have corresponding input stream classes.
     * PrintStream(OutputStream out)
     *  - format({Locale loc}, String format, Object args...)
     * PrintWriter(OutputStream out)
     *  - write()/print() pretty much do the same thing.
     */

    private void print(Object o){
        System.out.println(o);
    }
}