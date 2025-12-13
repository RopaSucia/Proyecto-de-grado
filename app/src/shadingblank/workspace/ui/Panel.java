package shadingblank.workspace.ui;

import shadingblank.*;
import shadingblank.imguiconfig.GuiLayer;
import imgui.ImGui;

public abstract class Panel implements GuiLayer{

	protected Window window = Launcher.instance.window;

	@Override
	public void update() {

	}

	public void inputBegin() {

	}

	public void inputEnd() {
		
	}

}