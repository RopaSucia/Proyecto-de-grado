package shadingblank.workspace.ui;

import imgui.ImGui;
import shadingblank.workspace.NodeManager;
import shadingblank.workspace.ResourceManager;
import shadingblank.workspace.ui.modules.AttributesModule;
import shadingblank.workspace.ui.modules.NodeListModule;
import shadingblank.workspace.ui.modules.ShaderListModule;
import shadingblank.workspace.ui.modules.Viewport3DModule;

public class ToolsModule extends Panel {

	public final AttributesModule attributesModule;
	public final NodeListModule nodeListModule;
	public final Viewport3DModule viewport;
	public final ShaderListModule shaderListModule;

	public ToolsModule() {

		nodeListModule = new NodeListModule();
		attributesModule = new AttributesModule();
		viewport = new Viewport3DModule();
		shaderListModule = new ShaderListModule();
	}

	@Override
	public void update() {

		if (ImGui.beginTabBar("menu-tools")) { // -------- BEGIN TAB-BAR --------


			if (ImGui.beginTabItem("nodes")) { // -------- NODES TAB --------

				nodeListModule.update();

				ImGui.endTabItem();
			}

			if (ImGui.beginTabItem("attributes")) { // -------- ATTRIBUTES TAB --------

				attributesModule.getAttributeList(nodeListModule.getFocusedNode());
				attributesModule.update();

				ImGui.endTabItem();
			}

			if (ImGui.beginTabItem("code")) { // -------- CODE TAB --------
				ImGui.text("Code editor here");

				ImGui.endTabItem();
			}

			if (ImGui.beginTabItem("shaders")) { // -------- SHADERS TAB --------

				shaderListModule.update();

				ImGui.endTabItem();
			}

			if (ImGui.beginTabItem("viewport")) { // -------- VIEWPORT TAB --------

				viewport.update();

				ImGui.endTabItem();
			}

			ImGui.endTabBar(); // -------- END TAB-BAR --------

		}
	}

	public void viewport() {
		viewport.update();
	}

	public float[] viewportSize() {
		return viewport.viewportSize;
	}
}