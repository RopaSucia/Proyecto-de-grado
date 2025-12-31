package shadingblank.workspace.ui;

import java.util.List;

import imgui.ImGui;
import imgui.ImVec2;
import imgui.flag.ImGuiCol;
import shadingblank.rendering.Resource;

public class ResourceVisor extends Panel{

	private List<Resource> srcs;

	public ResourceVisor() {
		this.srcs = resources.getResources();
	}

	public void resourceVisor() {

		if(ImGui.button("load")) {
			instance.explorer.directory("");
		}

		if (resources != null) {
			ImGui.pushStyleColor(ImGuiCol.ChildBg, .2f, .2f, .2f, 1f);
			for (Resource resource : srcs) {
				ImGui.beginChild("resourcesName" + resource.name, new ImVec2(100, 135));
				ImGui.image(resource.getReference(), 100, 100);
				ImGui.text(resource.name);
				ImGui.endChild();
				ImGui.sameLine();
			}
			ImGui.popStyleColor();
		}
	}

	@Override
	public void update() {

	}

}