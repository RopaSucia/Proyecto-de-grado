package shadingblank;

import static org.lwjgl.opengl.GL33.*;
import shadingblank.workspace.ResourceManager;
import shadingblank.workspace.Scene;
import shadingblank.workspace.NodeManager;

public abstract class Motor {

	public String title = "-";
	public int height = 800, width = 1400;

	public final Window window;
	public final EventsManager events;
	public final RenderManager render;
	public final Time time;

	public Scene scene;

	public abstract void init();
	public abstract void loop(float delta);
	public abstract void close();

	public Motor () {
		window = new Window(height, width, title);
		events = new EventsManager(window);
		render = new RenderManager();
		time = new Time();
		scene = new Scene();

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
			time.update();

			loop((float)time.getDeltaTime());
			render.draw();

			window.update();
			glClear(GL_COLOR_BUFFER_BIT);
		}	
	}
}