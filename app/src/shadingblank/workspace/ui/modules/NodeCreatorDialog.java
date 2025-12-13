package shadingblank.workspace.ui.modules;

import shadingblank.imguiconfig.GuiLayer;
import shadingblank.workspace.NodeManager;
import shadingblank.workspace.ui.Panel;
import imgui.type.ImBoolean;
import imgui.type.ImString;
import imgui.ImGui;
import imgui.ImVec2;
import imgui.flag.ImGuiComboFlags;
import imgui.flag.ImGuiWindowFlags;

public class NodeCreatorDialog extends Panel{

	private ImString newNodeName;

	private ImBoolean 
		entyNode;

	public boolean open;
	private NodeManager manager;

	private float h = 260, w = 300;

	private int flags = 
		ImGuiWindowFlags.NoResize;

	private static final String [] types = {
		"node", "mesh", "camera", "frame"
	};

	private String selected = types[0];

	public NodeCreatorDialog(NodeManager manager) {
		this.manager = manager;

		newNodeName = new ImString(10);
		entyNode = new ImBoolean();
	}


	@Override
	public void update() {

		if(open) {

			ImGui.setNextWindowPos((window.width() - w)/ 2f, (window.height() - h) / 2f);
			ImGui.setNextWindowSize(w, h);

   if (!ImGui.isPopupOpen("Create node")) {
            ImGui.openPopup("Create node");
        }

			ImGui.beginPopupModal("Create node", flags);

			ImGui.text("new node name:");
			ImGui.sameLine();
			ImGui.inputText(" ", newNodeName);
			
			ImGui.setNextWindowFocus();
			if(ImGui.beginCombo("###nodeType", selected)) {
				
				for(String type: types) {
					boolean isSelected = (type.equals(selected));
					if(ImGui.selectable(type, isSelected)) {
						selected = type;
					}

					if(isSelected) {
						ImGui.setItemDefaultFocus();
					}
				}

				ImGui.endCombo();
			}

			if(ImGui.button("create")) {
				manager.createNode(newNodeName.get());
				newNodeName.clear();
				open = false;
			}

			ImGui.endPopup();		
		}

	}	

}