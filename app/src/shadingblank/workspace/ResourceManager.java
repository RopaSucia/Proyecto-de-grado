package shadingblank.workspace;

import java.util.ArrayList;
import java.util.List;

import shadingblank.rendering.*;

public final class ResourceManager {

	private List<Resource> resources = new ArrayList<>();

	public void add(Resource res) {
		resources.add(res);
	}

	public List<Resource> getResources() {
		return resources;
	}
}