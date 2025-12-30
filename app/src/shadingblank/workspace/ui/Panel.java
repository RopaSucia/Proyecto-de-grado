package shadingblank.workspace.ui;

import shadingblank.*;
import shadingblank.imguiconfig.GuiLayer;
import imgui.ImGui;

public abstract class Panel implements GuiLayer, CurrentInstance{

	protected void childPadding(float padding) {
    	ImGui.setCursorPos(ImGui.getCursorPosX() + padding, ImGui.getCursorPosY() + padding);		
	}

}