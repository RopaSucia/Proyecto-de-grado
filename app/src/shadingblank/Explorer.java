package shadingblank;

import java.util.List;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import shadingblank.workspace.ui.modules.FileExplorerDialog;

public class Explorer implements CurrentInstance{

	private List<Path> paths;

	public static final int SHADER = 1;
	public static final int TEXTURE = 2;
	public static final int PROYECT = 3;

	private FileExplorerDialog diag;
	private String currentDir;

	public Explorer() {
		diag = new FileExplorerDialog();
	}

	public Explorer load(String dir, int type) {

		paths = files.getDirectoryFiles(dir);
		
		diag.directory(dir);

		return this;
	}

	public Explorer sub() {

		return this;
	}

}