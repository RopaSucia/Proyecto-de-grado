package shadingblank.workspace.ui;

import imgui.ImGui;
import imgui.flag.ImGuiCol;
import imgui.flag.ImGuiStyleVar;
import imgui.flag.ImGuiTableFlags;
import shadingblank.nodes.Node;
import shadingblank.workspace.NodeManager;
import shadingblank.workspace.ResourceManager;
import shadingblank.workspace.ui.modules.AttributesModule;
import shadingblank.workspace.ui.modules.NodeCreatorDialog;
import shadingblank.workspace.ui.modules.NodeListModule;

public class ToolsModule extends Panel {

	// private ResourceManager resourceManager;

	private AttributesModule attributesModule;
	private NodeListModule nodeListModule;

	public ToolsModule(NodeManager nodeManager, ResourceManager resourceManager) {

		// this.resourceManager = resourceManager;
		nodeListModule = new NodeListModule(nodeManager);
		attributesModule = new AttributesModule();
	}

	@Override
	public void update() {

		if (ImGui.beginTabBar("menu-tools")) { // -------- BEGIN TAB-BAR --------

			if (ImGui.beginTabItem("code")) { // -------- CODE TAB --------
				ImGui.text("Code editor here");

				ImGui.endTabItem();
			}

			if (ImGui.beginTabItem("nodes")) { // -------- NODES TAB --------

				nodeListModule.update();

				ImGui.endTabItem();
			}

			if (ImGui.beginTabItem("attributes")) { // -------- ATTRIBUTES TAB --------

				attributesModule.getAttributeList(nodeListModule.getFocusedNode());
				attributesModule.update();

				ImGui.endTabItem();
			}
			ImGui.endTabBar(); // -------- END TAB-BAR --------

		}
	}
}