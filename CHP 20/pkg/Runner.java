package pkg;

import java.nio.file.*;
import java.net.*;
import java.io.*;
import java.nio.file.attribute.*;

class Runner {
	public static void main(String... args) {
		// pathCreation();
		
		// pathMethods();
		
		// filesMethods();
		
		// fileAttributes();
		
		functionalProgramming();
	}
	
	public static void pathCreation(){
		/* Does not matter if these paths exist. */ 
		print(Path.of("/home")); // Absolute path
		print(Path.of("F:\\OCP 1Z0-819\\Code\\CHP 20\\pkg\\files")); // Absolute path
		print(Path.of("F:\\OCP 1Z0-819", "Code")); // F:\OCP 1Z0-819\Code
		print(Paths.get("F:\\OCP 1Z0-819", "Code")); // F:\OCP 1Z0-819\Code
		
		print(Paths.get("F:\\OCP 1Z0-819", "Code").toUri()); // file:///F:/OCP%201Z0-819/Code/
		print(Paths.get("F:\\OCP 1Z0-819", "Code").toUri().isAbsolute()); //true
		
		/* try {
			print(Path.of(new URI("http://google.com"))); // Exception in thread "main" java.nio.file.FileSystemNotFoundException: Provider "http" not installed
			print(Paths.get(new URI("http://google.com"))); // Exception in thread "main" java.nio.file.FileSystemNotFoundException: Provider "http" not installed
		} catch(URISyntaxException e){
			e.printStackTrace();			
		} */
		
		print(FileSystems.getDefault().getPath("pandas/darkSide.png")); //pandas\darkSide.png
		print(Path.of("F:\\OCP 1Z0-819", "Code").toFile().getClass()); // class java.io.File
		print(new File("F:\\OCP 1Z0-819", "Code").toPath().getClass()); // class sun.nio.fs.WindowsPath
		
		/* Files is a helper class for working with Paths. */
		print(Files.exists(Path.of("F:\\OCP 1Z0-819\\Code\\CHP 20\\pkg\\files"), LinkOption.NOFOLLOW_LINKS)); //true
		print(Files.exists(Path.of("F:\\OCP 1Z0-819\\Code\\CHP 20\\pkg\\f"), LinkOption.NOFOLLOW_LINKS)); //false
		
		/* Move operation - Deletes Source.txt and moves the contents to Destination.txt. */
		/* try{
			Files.move(
				Path.of("F:\\OCP 1Z0-819\\Code\\CHP 20\\pkg\\files\\Source.txt"),
				Path.of("F:\\OCP 1Z0-819\\Code\\CHP 20\\pkg\\files\\Destination.txt"),
				LinkOption.NOFOLLOW_LINKS, 
				StandardCopyOption.ATOMIC_MOVE
			);
		} catch(IOException e){
			e.printStackTrace();
		} */
	}
	
	public static void pathMethods(){
		print(Path.of("\\home\\namek").toString()); // \home\namek
		print(Path.of("\\home\\namek").getNameCount()); //2
		print(Path.of("\\home\\namek").getName(1)); // namek Throws IllegalArgumentException with incorrect indexes.
		print(Path.of("\\home\\namek").subpath(0, 1));// home endIndex is exclusive
		print(Path.of("\\home\\namek").getFileName()); //namek
		print(Path.of("\\home\\namek").getParent()); //home
		print(Path.of("\\home\\namek").getRoot()); // Just a backslash symbol.
		print(Path.of("\\home\\namek").isAbsolute()); //false
		print(Path.of("\\home\\namek").toAbsolutePath()); //F:\home\namek (joining is done with the current working directory)
		
		/* Does not resolve path symbols and returns the absolute path if one is passed to the resolve method. */
		print((Path.of("\\home\\namek").resolve(Path.of("vegeta")))); //\home\namek\vegeta
		
		/* Requires both paths to be absolute or relative. Throws IllegalArgumentException if type is mixed. */
		print(Path.of("homie.txt").relativize(Path.of("friends/homie.txt"))); //..\friends\homie.txt
		
		/* Helps remove redundancies in a path and allows us to compare. */
		print(Path.of("./armadillo/../shells.txt").normalize()); // shells.txt
		
		/* 
		   Calls normalize() then toRealPath() and throws an exception if the path does not exist.
		   Also throws java.nio.file.NoSuchFileException in case the file does not exist.
		*/
		try {
			print(Path.of("F:\\OCP 1Z0-819\\Code\\CHP 20\\pkg\\files").toRealPath()); // F:\OCP 1Z0-819\Code\CHP 20\pkg\files
		} catch (IOException e){
			e.printStackTrace();
		}
	}
	
	public static void filesMethods() {
		
		try {
			// Will return true with the same Path objects and returns exception if path does not exist.
			print(Files.isSameFile(Path.of("F:\\OCP 1Z0-819\\Code\\CHP 20\\pkg\\files"), Paths.get("F:\\OCP 1Z0-819\\Code\\CHP 20\\pkg\\files"))); //true
			
			// Throws an exception (java.nio.file.FileAlreadyExistsException) if directory already exists, or the path leading up to the directory does not exist.
			// Files.createDirectory(Path.of("F:\\OCP 1Z0-819\\Code\\CHP 20\\pkg\\files\\myDirectory"));
			
			// Does nothing if all the directories already exist. Btw it only creates directories, even if there is a .txt extension below.
			// Files.createDirectories(Path.of("F:\\OCP 1Z0-819\\Code\\CHP 20\\pkg\\files\\myDirectory\\myFile.txt"));
			
			// If the target file already exists an exception is thrown. StandardCopyOption.REPLACE_EXISTING takes care of that.
			// Files.copy(Path.of("F:\\OCP 1Z0-819\\Code\\CHP 20\\pkg\\files\\Source.txt"), Path.of("F:\\OCP 1Z0-819\\Code\\CHP 20\\pkg\\files\\Destination\\Source.txt"));
		
			// The same works for OutputStream.
			try(var is = new FileInputStream("F:\\OCP 1Z0-819\\Code\\CHP 20\\pkg\\files\\Source.txt")) {
				Files.copy(is, Path.of("F:\\OCP 1Z0-819\\Code\\CHP 20\\pkg\\files\\Destination\\Source.txt"), StandardCopyOption.REPLACE_EXISTING);
			}
			
			// Prints file content to the console.
			Files.copy(Path.of("F:\\OCP 1Z0-819\\Code\\CHP 20\\pkg\\files\\Destination\\Source.txt"), System.out);
			
			// Files.move can also be used for renaming. File system can throw AtomicMoveNotSupportedException if this operation is not supported.
			Files.move(Path.of("F:\\OCP 1Z0-819\\Code\\CHP 20\\pkg\\files\\Destination\\Source.txt"),
				Path.of("F:\\OCP 1Z0-819\\Code\\CHP 20\\pkg\\files\\Destination\\SourceRenamed.txt"),
				StandardCopyOption.REPLACE_EXISTING);
			
			// Will throw exception if the path does not exist.
			Files.delete(Path.of("F:\\OCP 1Z0-819\\Code\\CHP 20\\pkg\\files\\Destination\\SourceRenamed.txt"));
			Files.deleteIfExists(Path.of("F:\\OCP 1Z0-819\\Code\\CHP 20\\pkg\\files\\Destination\\Source.txt"));
			
			// newBufferedReader: Reads a file with a BufferedReader, newBufferedWriter returns a BufferedWriter object to write.
			/* try (var reader = Files.newBufferedReader(Path.of("F:\\OCP 1Z0-819\\Code\\CHP 20\\pkg\\files\\Story.txt"))){
				String currentLine = null;
				while((currentLine = reader.readLine()) != null)
					print(currentLine);
			} */
			
			// returns a List<String> object. A significantly large file can trigger an OutOfMemoryError.
			Files.readAllLines(Path.of("F:\\OCP 1Z0-819\\Code\\CHP 20\\pkg\\files\\Story.txt")).forEach(System.out::println);
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void fileAttributes(){
		print(Files.isDirectory(Path.of("F:\\OCP 1Z0-819\\Code\\CHP 20\\pkg\\files\\Story.txt"))); //false
		print(Files.isSymbolicLink(Path.of("F:\\OCP 1Z0-819\\Code\\CHP 20\\pkg\\files\\Story.txt"))); //false
		print(Files.isRegularFile(Path.of("F:\\OCP 1Z0-819\\Code\\CHP 20\\pkg\\files\\Story.txt"))); //true
		
		try {
			print(Files.isHidden(Path.of("F:\\OCP 1Z0-819\\Code\\CHP 20\\pkg\\files\\Story.txt"))); //false
		} catch (IOException e) { e.printStackTrace(); }
		
		print(Files.isReadable(Path.of("F:\\OCP 1Z0-819\\Code\\CHP 20\\pkg\\files\\Story.txt"))); //true
		print(Files.isWritable(Path.of("F:\\OCP 1Z0-819\\Code\\CHP 20\\pkg\\files\\Story.txt"))); //true
		print(Files.isExecutable(Path.of("F:\\OCP 1Z0-819\\Code\\CHP 20\\pkg\\files\\Story.txt"))); //true
		try {
			print(Files.size(Path.of("F:\\OCP 1Z0-819\\Code\\CHP 20\\pkg\\files\\Story.txt"))); //56 (in bytes)
		} catch (IOException e) { e.printStackTrace(); }
			
		try {
			// to Millis will return epocch time.
			print(Files.getLastModifiedTime(Path.of("F:\\OCP 1Z0-819\\Code\\CHP 20\\pkg\\files\\Story.txt"))); //2023-04-25T15:17:43.122683Z (FileTime object)
		} catch (IOException e) { e.printStackTrace(); }
		
		/* It's more efficient to read all these attributes in a single trip to the file system. */
		BasicFileAttributes data = null;
		try {
			data = Files.readAttributes(Path.of("F:\\OCP 1Z0-819\\Code\\CHP 20\\pkg\\files\\Story.txt"), BasicFileAttributes.class);
		} catch (IOException e) { e.printStackTrace(); }
		print("------");
		print(data.isDirectory()); //false
		print(data.isRegularFile()); //true
		print(data.isSymbolicLink()); //false
		print(data.size()); //56
		print(data.lastModifiedTime()); //2023-04-25T15:17:43.122683Z
		
		/* Modifying attributes. */
		// BasicFileAttributeView view = Files.getFileAttributeView(path, BasicFileAttributeView.class);
		// BasicFileAttributes attributes = view.readAttributes(); //Returns all the same attributes we printed above.
		// public void setTimes(FileTime lastModifiedTime, FileTime lastAccessTime, FileTime createTime)
		/* Last modified time is incremented by 10 seconds.
		view.setTimes(
			FileTime.fromMillis(
				attributes.lastModifiedTime().toMillis + 10_000
			),
			null,
			null
		); */
	}
	
	public static void functionalProgramming() {
		/* try {
			// List all the directories in C drive.
			// JVM will not follow symbolic links by default for this method.
			// returns Stream<Path> object.
			Files.list(Paths.get("C:\\")).forEach(System.out::println);
		} catch(IOException e) { e.printStackTrace(); } */
		
		//Traverse a directory.
		/* try {
			// maxDepth is an optional parameter in the depth-first search applied.
			// Can throw a FileSystemLoopException when FOLLOW_LINKS option is enabled in case of a circular dependency.
			Files.walk(Paths.get("F:\\OCP 1Z0-819\\Code")).forEach(System.out::println);
		} catch(IOException e) { e.printStackTrace(); } */
		
		// Find something
		/* try {
			// Just find the Story.txt file in this  directory.
			// F:\OCP 1Z0-819\Code\CHP 20\pkg\files\Story.txt
			Files.find(Path.of("F:\\OCP 1Z0-819\\Code"), 10, 
				(p, a) -> a.isRegularFile() && p.toString().endsWith(".txt") && p.toString().contains("Story")
			).forEach(System.out::println);
		} catch(IOException e) { e.printStackTrace(); } */
		
		// Read a file completely
		// Returns a Stream<String> object. Better than readAllLines().
		try {
			Files.lines(Paths.get("F:\\OCP 1Z0-819\\Code\\CHP 20\\pkg\\files\\Story.txt")).forEach(System.out::println);
		} catch(IOException e) { e.printStackTrace(); }
	}
	
	public static void print(Object o){
		System.out.println(o);
	}
}