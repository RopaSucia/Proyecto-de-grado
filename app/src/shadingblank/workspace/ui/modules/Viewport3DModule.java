package shadingblank.workspace.ui.modules;

import java.util.List;

import imgui.ImGui;
import imgui.flag.ImGuiChildFlags;
import imgui.flag.ImGuiWindowFlags;
import shadingblank.CurrentInstance;
import shadingblank.nodes.CameraNode;
import shadingblank.nodes.ViewportNode;
import shadingblank.rendering.FrameBuffer;
import shadingblank.workspace.ui.Panel;

public class Viewport3DModule extends Panel implements CurrentInstance{

	private static int instances;

	public ViewportNode currentViewport;
	public CameraNode currentCamera;

	public float[] viewportSize;

	private List<ViewportNode> viewports;
	private List<CameraNode> cameras;


	private int windowFlags =

			ImGuiWindowFlags.NoScrollbar;

	private int childFlags =

			ImGuiChildFlags.Border |
					ImGuiChildFlags.ResizeX |
					ImGuiChildFlags.ResizeY;


	public Viewport3DModule(List<ViewportNode> viewports, List<CameraNode> cameras) {
		viewportSize = new float[] { 800, 600 };

		this.viewports = viewports;
		this.cameras = cameras;

		instances ++;
	}

	@Override
	public void update() {

		ImGui.beginChild("viewport-" + instances, childFlags, windowFlags);

		viewportSize[0] = ImGui.getWindowWidth();
		viewportSize[1] = ImGui.getWindowHeight();

		if (viewports.size() > 0) {

			String current = (currentViewport != null) ? currentViewport.name.value: "select";

			if (ImGui.beginCombo("###buffer", current)) {

				for (ViewportNode viewportNode : viewports) {

					boolean isActive = true;

					if(currentViewport != null) {
						isActive = viewportNode.name.value.equals(currentViewport.name.value);
					}

					if (ImGui.selectable(viewportNode.name.value, isActive)) {
						currentViewport = viewportNode;
						instance.workspace.viewport = currentViewport;
					}
				}

				ImGui.endCombo();
			}

		}
		ImGui.sameLine();

		if (cameras.size() > 0) {

			String current = (currentCamera != null) ? currentCamera.name.value: "select";

			if (ImGui.beginCombo("camera", current)) {

				for (CameraNode cameraNode : cameras) {

					boolean isActive = true;

					if(currentCamera != null) {
						isActive = cameraNode.name.value.equals(currentCamera.name.value);
					}

					if (ImGui.selectable(cameraNode.name.value, isActive)) {
						currentCamera = cameraNode;
						instance.workspace.camera = currentCamera;
					}
				}

				ImGui.endCombo();
			}
		}



		// -------- SHOW BUFFER --------

		if (currentViewport != null) {
			if (currentViewport.closed == false)
				ImGui.image(currentViewport.buffer.getTexture(), viewportSize[0], viewportSize[1]);

		} else {
			ImGui.text("Sin buffer seleccionado");
		}

		if (currentCamera != null) {
			if (currentCamera.closed == false)
				ImGui.text("Sin Camara seleccionada");

		} else {
			ImGui.text("Sin Camara seleccionada");
		}

		ImGui.endChild();

	}

}