package shadingblank;

import java.io.IOException;
import java.nio.file.*;

public class ResourceFiles {

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
}
