package shadingblank;

import shadingblank.workspace.NodeManager;
import shadingblank.workspace.ResourceManager;
import shadingblank.workspace.Scene;

public interface CurrentInstance {

	public Launcher instance = Launcher.instance;
	public Window window = instance.window;
	public Time time = instance.time;

	public Scene scene = instance.scene;
	public NodeManager nodes = scene.nodes;
	public EventsManager events = instance.events;
	public RenderManager render = instance.render;
	public ResourceManager resources = scene.resources;

	default public void update(float delta) {}

}