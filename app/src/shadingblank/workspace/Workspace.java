package shadingblank.workspace;

import shadingblank.CurrentInstance;
import shadingblank.nodes.CameraNode;
import shadingblank.nodes.ViewportNode;
import shadingblank.rendering.FrameBuffer;

public class Workspace implements CurrentInstance {

	public CameraNode camera;
	public ViewportNode viewport;

	private double[] mousePos, lastMousePos;

	public Workspace() {
		mousePos = new double[2];
		lastMousePos = new double[2];
	}

	@Override
	public void update(float delta) {

		double[] ms = events.mousePos();

		mousePos[0] = ms[0];
		mousePos[1] = ms[1];

		if (viewport != null) {

			viewport.update();
			FrameBuffer buffer = viewport.buffer;

			buffer.begin();

			if (camera != null) {

				camera.height = instance.UIroot.toolsModule1.viewportSize()[0];
				camera.width = instance.UIroot.toolsModule1.viewportSize()[1];

				if (events.isKeyPressed('W')) {
					camera.moveZ(1f * delta);
				}

				if (events.isKeyPressed('S')) {
					camera.moveZ(-1f * delta);
				}

				if (events.isKeyPressed('A')) {
					camera.moveX(1f * delta);
				}

				if (events.isKeyPressed('D')) {
					camera.moveX(-1f * delta);
				}

				if (events.mouseLeftClick()) {

				}

				if (events.mouseRightClick()) {
					camera.rotateX((float) (mousePos[1] - lastMousePos[1]) / -5f);
					camera.rotateY((float) (mousePos[0] - lastMousePos[0]) / 5f);
				}
				if(render.currentShaderProgram != null) {
					render.currentShaderProgram.uniformMat4("view", camera.getViewMat());
					render.currentShaderProgram.uniformMat4("projection", camera.getProjectionMat());
				}
			}

			render.draw();
			buffer.close();

		}

		lastMousePos[0] = mousePos[0];
		lastMousePos[1] = mousePos[1];
	}

}