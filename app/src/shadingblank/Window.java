package shadingblank;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL33.*;

import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GLCapabilities;

import shadingblank.imguiconfig.*;


public class Window {

	private final long window;
	private GLCapabilities caps;
	private int height, width;
	public GuiContext guiContext;

	private List<GuiLayer>layers = new ArrayList<>();

	private float r, g, b, a;

	public Window (int height, int width, String title) {
		if(!glfwInit()) {
			System.err.println("No se ha podido iniciar GLFW");
		}

		glfwDefaultWindowHints();
		glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);

		window = glfwCreateWindow(width, height, title, 0, 0);
		glfwMakeContextCurrent(window);
		caps = GL.createCapabilities();
		glfwSwapInterval(1);
		glfwShowWindow(window);

		guiContext = new GuiContext();
		guiContext.init(window);
	}

	public void update() {
		glClearColor(r, g, b, a);
		IntBuffer h = BufferUtils.createIntBuffer(1);
		IntBuffer w = BufferUtils.createIntBuffer(1);

		glfwGetWindowSize(window, w, h);

		this.height = h.get();
		this.width = w.get();

		guiContext.render(layers);
		glfwPollEvents();
		glfwSwapBuffers(window);
	}

	public void backgroundColor(float r, float g, float b, float a) {
		this.r = r; this.g = g; this.b = b; this.a = a;
	}

	public void addLayer(GuiLayer layer) {
		layers.add(layer);
	}

	public int height() {
		return height;
	}

	public int width() {
		return width;
	}

	public boolean isWindowClosed() {
		return glfwWindowShouldClose(window);
	} 

	public void close() {
		glfwTerminate();
	}

	public long getID() {
		return window;
	}
}