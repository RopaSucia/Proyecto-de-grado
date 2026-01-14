package shadingblank;

import java.util.List;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import shadingblank.rendering.Shader;
import shadingblank.rendering.Texture;
import shadingblank.workspace.ui.modules.ExplorerReturnCallback;
import shadingblank.workspace.ui.modules.FileExplorerDialog;

public class Explorer implements CurrentInstance {

	private List<Path> paths;

	public static final int SHADER = 1;
	public static final int TEXTURE = 2;
	public static final int PROJECT = 3;

	private int currentConfiguration = PROJECT;

	private FileExplorerDialog diag;
	private ExplorerReturnCallback returnCallback;

	public Explorer() {

		returnCallback = new ExplorerReturnCallback() {
			@Override
			public void call(String dir) {
				if (dir != null) {
					Path file = Paths.get(dir).toAbsolutePath();

					switch (currentConfiguration) {

						case SHADER:

							toShader(file);
							break;

						case TEXTURE:

							toTexture(file);
							break;

						case PROJECT:
							break;
					}
				}

			}
		};

		diag = new FileExplorerDialog(returnCallback);
		window.addLayer(diag);
	}

	public Explorer load(String dir, int type) {

		paths = files.getDirectoryFiles(dir);
		currentConfiguration = type;
		diag.init(dir);

		return this;
	}

	private void toShader(Path path) {
		try {

			System.out.println("ruta del shader: " + path.toString());

			String source = Files.readString(path);

			System.out.println("el shader resource: " + source);

			Shader shader = new Shader(
					path.getFileName().toString(),
					source,
					Shader.VERTEX_SHADER);

			resources.add(shader);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void toTexture(Path path) {
		System.out.println("en la textura: " + path.toString());
		Texture texture = new Texture(
				path.getFileName().toString(),
				path.toString());

		resources.add(texture);
	}
}