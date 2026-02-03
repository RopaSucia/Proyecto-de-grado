package shadingblank.workspace;

import java.util.ArrayList;
import java.util.List;

import shadingblank.rendering.*;

public final class ResourceManager {

	private final List<Resource> resources;
	private final MeshLoader meshLoader;

	public ResourceManager () {

		resources = new ArrayList<>();
		meshLoader = new MeshLoader();

	}

	public void add(Resource res) {
		resources.add(res);
	}

	public void addModel(String resDir) {
		Mesh[] meshes = meshLoader.load(resDir);

		for(Mesh mesh: meshes) {
			resources.add(mesh);
		}
	}

	public List<Resource> getResources() {
		return resources;
	}
}