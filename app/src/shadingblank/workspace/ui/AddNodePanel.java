package shadingblank.workspace.ui;

import shadingblank.imguiconfig.GuiLayer;
import shadingblank.workspace.NodeManager;
import imgui.type.ImBoolean;
import imgui.type.ImString;
import imgui.ImGui;
import imgui.ImVec2;
import imgui.flag.ImGuiWindowFlags;

public class AddNodePanel extends Panel implements GuiLayer{

	private ImString newNodeName;

	private ImBoolean 
		entyNode;

	public boolean open;
	private NodeManager manager;

	private float h = 200, w = 250;

	private int flags = 
		ImGuiWindowFlags.NoCollapse |
		ImGuiWindowFlags.NoResize;

	public AddNodePanel(NodeManager manager) {
		this.manager = manager;

		newNodeName = new ImString(30);
		entyNode = new ImBoolean();
	}


	@Override
	public void update() {

		if(open) { // el problema está por aqui :p

			ImGui.setNextWindowPos((window.width() - w)/ 2f, (window.height() - h) / 2f);
			ImGui.setNextWindowSize(w, h);
			ImGui.setNextWindowFocus();
			ImGui.begin("Create node", flags);

			ImGui.text("new node name:");
			ImGui.sameLine();
			ImGui.inputText(" ", newNodeName);

			if(ImGui.beginCombo("tydpe", "selectype")) {
				ImGui.checkbox("node", entyNode);
				ImGui.checkbox("meshnode", entyNode);
				ImGui.checkbox("cameranode", entyNode);
				ImGui.endCombo();
			}

			if(ImGui.button("create")) {
				manager.createNode(newNodeName.get());
				newNodeName.clear();
				open = false;
			}

			ImGui.end();		
		}

	}	

}