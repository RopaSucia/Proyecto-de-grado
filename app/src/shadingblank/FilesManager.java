package shadingblank;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

import shadingblank.workspace.ui.modules.ExplorerReturnCallback;

public class FilesManager {

	private Path lastPath;

	public final String USERHOME;

	public final ExplorerReturnCallback returnCallback;

	public FilesManager() {
		USERHOME = System.getProperty("user.home");
		
		returnCallback = new ExplorerReturnCallback() {
			@Override
			public void call(ByteBuffer bytes) {
				
			}	
		};
	}

	public String getString(String path) {
		try{
			Path pathFile = Paths.get(path);
			return Files.readString(pathFile);
		}
		catch(IOException e) {
			System.err.println("Inaccesible: " + path);
			return null;
		}
	}

	public byte[] getBytes(String path) {
		try{
			Path pathFile = Paths.get(path);
			return Files.readAllBytes(pathFile);
		}
		catch(IOException e) {
			System.err.println("Inaccesible: " + path);
			return null;
		}
	}

	// -------- DIRECTORIES --------

	public List<Path> getDirectoryFiles(String path) {

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

	public String parent(String path) {

		String parent = path;

		Path pathParent = Paths.get(path).getParent();

		if(pathParent != null) {
			parent = pathParent.toString();
		}

		return parent;

	}

	public void explorer() {

	}
}
