package shadingblank;

import static org.lwjgl.glfw.GLFW.*;

import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWMouseButtonCallback;
import org.lwjgl.glfw.GLFWCursorPosCallback;

public class EventsManager {

	GLFWKeyCallback keyCallback;
	GLFWMouseButtonCallback mouseButtonCallback;
	GLFWCursorPosCallback cursorCallback;

	// GLFW_KEY_LAST cuenta la cantidad total de unicodes disponibles o no c

	private final boolean[] keysPressed = new boolean[GLFW_KEY_LAST + 1];
	private final double[] mousePos = new double[2];

	private boolean leftClick, rightClick;

	public EventsManager(Window windowInstance) {
		begin(windowInstance);
	}

	public void begin(Window windowInstance) {

		keyCallback = new GLFWKeyCallback() {
			@Override
			public void invoke(long window, int keycode, int scancode, int action, int mods) {
				if (!keysPressed[keycode] && action == GLFW_PRESS) {
					keysPressed[keycode] = true;
				}
				if (keysPressed[keycode] && action == GLFW_RELEASE) {
					keysPressed[keycode] = false;
				}

				windowInstance.guiContext.keyCallbacks(keycode, scancode, action, mods);
			}
		};

		mouseButtonCallback = new GLFWMouseButtonCallback() {
			@Override
			public void invoke(long window, int button, int action, int mods) {
				if (action == GLFW_PRESS) {

					switch (button) {
						case GLFW_MOUSE_BUTTON_LEFT:
							leftClick = true;
							break;
						case GLFW_MOUSE_BUTTON_RIGHT:
							rightClick = true;
							break;
					}

				} else if (action == GLFW_RELEASE) {

					switch (button) {
						case GLFW_MOUSE_BUTTON_LEFT:
							leftClick = false;
							break;
						case GLFW_MOUSE_BUTTON_RIGHT:
							rightClick = false;
							break;
					}
				}
				windowInstance.guiContext.mouseButtonCallback(button, action, mods);
			}
		};

		cursorCallback = new GLFWCursorPosCallback() {
			@Override
			public void invoke(long window, double xPos, double yPos) {
				mousePos[0] = xPos;
				mousePos[1] = yPos;

				windowInstance.guiContext.mousePosCallback(xPos, yPos);
			}
		};

		glfwSetKeyCallback(windowInstance.getID(), keyCallback);
		glfwSetMouseButtonCallback(windowInstance.getID(), mouseButtonCallback);
		glfwSetCursorPosCallback(windowInstance.getID(), cursorCallback);

	}

	public boolean isKeyPressed(char key) {
		return keysPressed[(int)key];
	}

	public boolean mouseLeftClick() {
		return leftClick;
	}

	public boolean mouseRightClick() {
		return rightClick;
	}

	public double[] mousePos() {
		return new double[] {mousePos[0], mousePos[1]};
	}
}