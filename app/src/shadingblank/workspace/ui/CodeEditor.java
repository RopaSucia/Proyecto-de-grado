package shadingblank.workspace.ui;

import imgui.ImGui;
import imgui.flag.ImGuiInputTextFlags;
import imgui.type.ImString;

import java.nio.ByteBuffer;

public class CodeEditor extends Panel{

	private ImString code;

	public CodeEditor() {
		code = new ImString();
		code.resize(1024);
	}

	@Override
	public void update(){
		ImGui.begin("Code editor");
		ImGui.inputTextMultiline("##hiddenInput", code,
		ImGui.getWindowWidth() - 16f, ImGui.getWindowHeight() - 16f,
		ImGuiInputTextFlags.AllowTabInput);

		ImGui.end();
	}

	public CodeEditor add() {
		window.addLayer(this);
		return this;
	}

}