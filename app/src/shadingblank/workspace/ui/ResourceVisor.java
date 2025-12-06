package shadingblank.workspace.ui;

import java.util.List;

import imgui.ImGui;
import imgui.ImVec2;
import imgui.flag.ImGuiCol;
import shadingblank.rendering.Resource;

public class ResourceVisor extends Panel {

	private List<Resource> resources;

	public ResourceVisor(List<Resource> resources) {
		this.resources = resources;
	}

	public void resourceVisor() {


		if (resources != null) {
			ImGui.pushStyleColor(ImGuiCol.ChildBg, .2f, .2f, .2f, 1f);
			for (Resource resource : resources) {
				ImGui.sameLine();
				ImGui.beginChild("resourcesName" + resource.name, new ImVec2(100, 135));
				ImGui.image(resource.getReference(), 100, 100);
				ImGui.text(resource.name);
				ImGui.endChild();
			}
			ImGui.popStyleColor();
		}
	}

}