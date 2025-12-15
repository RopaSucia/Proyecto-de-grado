package shadingblank.workspace.ui.modules;

import imgui.ImGui;
import imgui.flag.ImGuiCol;
import imgui.flag.ImGuiStyleVar;
import imgui.flag.ImGuiTableFlags;
import shadingblank.nodes.Node;
import shadingblank.workspace.NodeManager;
import shadingblank.workspace.ui.Panel;

public class NodeListModule extends Panel {

	private NodeManager nodeManager;

	private Node currentNode;

	private NodeCreatorDialog nodeCreator;

	private int tableFlags = ImGuiTableFlags.RowBg;

	public NodeListModule(NodeManager nodeManager) {

		this.nodeManager = nodeManager;

		nodeCreator = new NodeCreatorDialog(nodeManager);
	}

	@Override
	public void update() {

		nodeCreator.update();

		ImGui.pushStyleColor(ImGuiCol.ChildBg, 0.1f, 0.1f, 0.1f, 1f);
		ImGui.beginChild("node-list-wrap");
		if (ImGui.button("new")) {
			nodeCreator.open = true;
		}

		if (ImGui.beginTable("treeNodes", 2, tableFlags)) {
			ImGui.tableSetupColumn("Name");
			ImGui.tableSetupColumn("Type");
			ImGui.tableHeadersRow();

			if (nodeManager.nodes != null) {
				for (Node node : nodeManager.nodes) {
					ImGui.tableNextRow();
					ImGui.tableSetColumnIndex(0);

					ImGui.pushStyleColor(ImGuiCol.Button, 0, 0, 0, 0);
					ImGui.pushStyleVar(ImGuiStyleVar.FramePadding, 4f, 3f);

					if (ImGui.button(node.name.value, ImGui.getContentRegionAvailX() - 50, 20)) { // node name
						currentNode = node;
					}

					ImGui.sameLine();

					if(ImGui.button("dlt")) {
						nodeManager.blackList(node);
					}

					ImGui.tableSetColumnIndex(1);
					ImGui.text(node.getClass().getName());
					ImGui.popStyleVar();
					ImGui.popStyleColor();
				}

			}

			ImGui.endTable();
			nodeManager.clearBlackList();

		}
		ImGui.endChild();
		ImGui.popStyleColor();
	}

	public Node getFocusedNode() {
		return currentNode;
	}
}