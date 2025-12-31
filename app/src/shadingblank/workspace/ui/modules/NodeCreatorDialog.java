package shadingblank.workspace.ui.modules;

import shadingblank.workspace.NodeManager;
import shadingblank.workspace.ui.Panel;
import imgui.type.ImBoolean;
import imgui.type.ImString;
import imgui.ImGui;
import imgui.flag.ImGuiWindowFlags;

public class NodeCreatorDialog extends Panel{

	private ImString newNodeName;

	public boolean open;
	private NodeManager manager;

	private float h = 260, w = 300;

	private int flags = 
		ImGuiWindowFlags.NoResize;

	private static final String [] types = {
		"node", "mesh", "camera", "viewport"
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

				switch (selected) {
					case "node":
							manager.createNode(newNodeName.get());
						break;
					case "mesh":
							manager.createMeshNode(newNodeName.get());
						break;
					case "camera":
							manager.createCameraNode(newNodeName.get());
						break;
					case "viewport":
							manager.createViewportNode(newNodeName.get());
						break;		
					default:
						break;
				}
				newNodeName.clear();
				open = false;
			}

			ImGui.endPopup();		
		}

	}	

}