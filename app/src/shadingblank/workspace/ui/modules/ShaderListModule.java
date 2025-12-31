package shadingblank.workspace.ui.modules;

import imgui.ImGui;
import imgui.flag.ImGuiCol;
import shadingblank.workspace.ui.Panel;

public class ShaderListModule extends Panel{

	public ShaderListModule() {

	}

	@Override
	public void update() {

		ImGui.pushStyleColor(ImGuiCol.ChildBg, 0.1f, 0.1f, 0.1f, 1f);
		ImGui.beginChild("shaders-list");

		if(ImGui.beginTable("shaders", 1)) {


			ImGui.endTable();
		}
		
		ImGui.endChild();
		ImGui.popStyleColor();
	}

}