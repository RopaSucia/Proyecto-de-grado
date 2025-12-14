package shadingblank;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class FilesManager {

	public static String getString(String path) {
		try{
			Path pathFile = Paths.get(path);
			return Files.readString(pathFile);
		}
		catch(IOException e) {
			System.err.println("Inaccesible: " + path);
			return null;
		}
	}

	public static byte[] getBytes(String path) {
		try{
			Path pathFile = Paths.get(path);
			return Files.readAllBytes(pathFile);
		}
		catch(IOException e) {
			System.err.println("Inaccesible: " + path);
			return null;
		}
	}

	public static List<Path> getDirectoryFiles(String path) {

		Path dir = Paths.get(path);

		List<Path> files = new ArrayList<>();

		try(DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {

			for(Path entry: stream) {
 				files.add(entry);
			}

		} catch(IOException e) {

		}

		return files;
	}
}
