package shadingblank.workspace;

public class Scene {

	public final ResourceManager resources;
	public final NodeManager nodes;

	public Scene() {

		resources = new ResourceManager();
		nodes = new NodeManager();
	}
}