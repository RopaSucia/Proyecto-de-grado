package shadingblank.imguiconfig;

import shadingblank.Window;

import java.util.List;

import static org.lwjgl.glfw.GLFW.*;

import imgui.ImGui;
import imgui.flag.ImGuiConfigFlags;
import imgui.glfw.ImGuiImplGlfw;
import imgui.internal.ImGuiContext;
import imgui.gl3.ImGuiImplGl3;

public class GuiContext {

	public final ImGuiImplGlfw imGuiGLFW;
	public final ImGuiImplGl3 imGuigl3;
	public final ImGuiContext context;

	private long window;

	public GuiContext() {

		imGuiGLFW = new ImGuiImplGlfw();
		imGuigl3 = new ImGuiImplGl3();
		context = ImGui.createContext();
	}
 
	public void init(long window) {
		this.window = window;

		imGuiGLFW.init(window, true);
		imGuigl3.init("#version 330 core");
	}

	//keyboard

	public void keyCallbacks (int keycode, int scancode, int action, int mods) {
		imGuiGLFW.keyCallback(window, keycode, scancode, action, mods);
	}

	public void charCallback (int c) {
		imGuiGLFW.charCallback(window, c);
	}

	//mouse

	public void mouseButtonCallback (int button, int action, int mods) {
		imGuiGLFW.mouseButtonCallback(window, button, action, mods);
	}

	public void mousePosCallback (double x, double y) {
		imGuiGLFW.cursorPosCallback(window, x, y);
	}

	public void mouseEnterCallback (boolean entered) {
		imGuiGLFW.cursorEnterCallback(window, entered);
	}

	//monitor

	public void monitoCallback (int event) {
		imGuiGLFW.monitorCallback(window, event);
	}
	//general 

	public void installCallback () {
		imGuiGLFW.installCallbacks(window);
	}


	public void render(List<GuiLayer> layers) {

		imGuiGLFW.newFrame();
		imGuigl3.newFrame();
		ImGui.newFrame();

		for(GuiLayer layer: layers) {
			layer.update();
		}

		ImGui.render();
		imGuigl3.renderDrawData(ImGui.getDrawData());

		if (ImGui.getIO().hasConfigFlags(ImGuiConfigFlags.ViewportsEnable)) {
			final long backupWindowPtr = glfwGetCurrentContext();
			ImGui.updatePlatformWindows();
			ImGui.renderPlatformWindowsDefault();
			glfwMakeContextCurrent(backupWindowPtr);
		}
	}

	public void dispose() {
		imGuiGLFW.shutdown();
		imGuigl3.shutdown();
		ImGui.destroyContext();
	}

}