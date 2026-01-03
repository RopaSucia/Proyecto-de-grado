package shadingblank.workspace.ui.modules;

import java.util.List;

import imgui.ImGui;
import imgui.flag.ImGuiChildFlags;
import imgui.flag.ImGuiWindowFlags;
import shadingblank.nodes.CameraNode;
import shadingblank.nodes.ViewportNode;
import shadingblank.workspace.ui.Panel;

public class Viewport3DModule extends Panel{

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


	public Viewport3DModule() {
		viewportSize = new float[] { 800, 600 };

		this.viewports = nodes.viewportNodes;
		this.cameras = nodes.cameraNodes;

		instances ++;
	}

	@Override
	public void update() {

		ImGui.beginChild("viewport-" + instances, childFlags, windowFlags);

		viewportSize[0] = ImGui.getWindowWidth();
		viewportSize[1] = ImGui.getWindowHeight();

		boolean goodCamera = true, goodViewport = true;

		if(currentCamera == null || currentCamera.closed) {
			goodCamera = false;
		}


		if(currentViewport == null || currentViewport.closed) {
			goodViewport = false;
		}

		if (viewports.size() > 0) {

			String current = (goodViewport) ? currentViewport.name.value: "select";

			if (ImGui.beginCombo("###buffer", current)) {

				for (ViewportNode viewportNode : viewports) {

					boolean isActive = true;

					if(goodViewport) {
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

			String current = (goodCamera) ? currentCamera.name.value: "select";

			if (ImGui.beginCombo("camera", current)) {

				for (CameraNode cameraNode : cameras) {

					boolean isActive = true;

					if(goodCamera) {
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

		if (goodViewport) {
			if (currentViewport.closed == false)
				ImGui.image(currentViewport.buffer.getTexture(), viewportSize[0], viewportSize[1]);

		} else {
			ImGui.text("Sin buffer seleccionado");
		}

		if (goodCamera) {
			if (currentCamera.closed == false)
				ImGui.text("Sin Camara seleccionada");

		} else {
			ImGui.text("Sin Camara seleccionada");
		}

		ImGui.endChild();

	}

}