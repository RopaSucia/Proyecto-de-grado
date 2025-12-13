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

public class ToolsModule extends Panel{

	private NodeManager nodeManager;
	private ResourceManager resourceManager;

	private AttributesModule attributesModule;

	private Node currentAttributeNode;

	private NodeCreatorDialog panel;

	private boolean newCurrentAttribute = false;

	int tableFlags =
		ImGuiTableFlags.RowBg;

	public ToolsModule(NodeManager nodeManager, ResourceManager resourceManager) {

		this.nodeManager = nodeManager;
		this.resourceManager = resourceManager;

		attributesModule = new AttributesModule();

		panel = new NodeCreatorDialog(nodeManager);
	}

	private void begin() {
		if(ImGui.beginMenuBar()) {
			ImGui.pushStyleVar(ImGuiStyleVar.FramePadding, 6f, 4f);

			ImGui.pushStyleColor(ImGuiCol.Button, .03f, .03f, .03f, 1f);
			ImGui.setCursorPosY(2f);

			ImGui.button("nodes");

			ImGui.button("code");

			ImGui.button("resources");

			ImGui.popStyleColor();
			ImGui.popStyleVar();
			ImGui.endMenuBar();
		}
	}

	public void codeEditor() {
		begin();
	}

	public void treeNode(String treeNodeName) {

		begin();
		panel.update();

		if(ImGui.button("new")) {
			panel.open = true;
		}

		if(ImGui.beginTable("treeNodes", 2, tableFlags)) {
			ImGui.tableSetupColumn("Name");
			ImGui.tableSetupColumn("Type");
			ImGui.tableHeadersRow();

			if(nodeManager.nodes != null) {
				for(Node node: nodeManager.nodes) {
					ImGui.tableNextRow();
					ImGui.tableSetColumnIndex(0);

					ImGui.pushStyleColor(ImGuiCol.Button, 0, 0, 0, 0);
					ImGui.pushStyleVar(ImGuiStyleVar.FramePadding, 4f, 3f);

					if(ImGui.button(node.name.value)) {
					currentAttributeNode = node;
					newCurrentAttribute = true;
					}

					ImGui.tableSetColumnIndex(1);
					ImGui.text(node.getClass().getName());
					ImGui.popStyleVar();
					ImGui.popStyleColor();
				}
			}

			ImGui.endTable();
		}
		
	}

	public void Attributes() {
		begin();
		attributesModule.update();
		if(currentAttributeNode != null && newCurrentAttribute) {
			attributesModule.getAttributeList(currentAttributeNode);
			newCurrentAttribute = false;
		}
	}

	public void newNode() {
		
	}
}