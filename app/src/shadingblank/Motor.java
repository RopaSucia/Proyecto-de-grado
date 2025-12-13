package shadingblank;

import static org.lwjgl.opengl.GL33.*;
import shadingblank.workspace.ResourceManager;
import shadingblank.workspace.NodeManager;

public abstract class Motor {

	public String title = "-";
	public int height = 800, width = 1400;

	public Window window;
	public EventsManager eventsManager;

	public ResourceManager resourceManager;

	public abstract void init();
	public abstract void loop();
	public abstract void close();

	public void start() {
		window = new Window(height, width, title);
		eventsManager = new EventsManager(window);
		resourceManager = new ResourceManager();

		init();
		update();
		close();
		window.guiContext.dispose();
		window.close();
	}

	public void backgroundColor(float r, float g, float b, float a) {
		glClearColor(r, g, b, a);
	}

	public void update() {
			while(!window.isWindowClosed()) {

			loop();

			window.update();
			glClear(GL_COLOR_BUFFER_BIT);
		}	
	}
}