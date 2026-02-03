package shadingblank.workspace.ui;

import imgui.ImGui;
import imgui.ImGuiStyle;
import imgui.flag.ImGuiCol;

public class UIColor {

	private ImGuiStyle style;

	public float [] base = {.65f, .35f, .0f, 1f};

	public float [] BGColorA = {base[0] *0.2f, base[1] *0.2f, base[2] *0.2f, 1f};
	public float [] BGColorB = {base[0] *0.2f, base[0] *0.2f, base[0] *0.2f, 1f};

	public float [] themeColorA = {base[0], base[1], base[2], 1f};
	public float [] themeColorB = {base[0] + .05f, base[1] + .05f, base[2], 1f};
	public float [] themeColorC = {base[0] + .1f, base[1] + .1f, base[2], 1f};

	public void setup() {

		style = ImGui.getStyle();

		style.setChildRounding(5f);
		style.setColor(ImGuiCol.WindowBg, 0.05f, 0.05f, 0.05f, 1f);
		style.setColor(ImGuiCol.ChildBg, 0.01f, 0.01f, 0.01f, 1f);
		style.setColor(ImGuiCol.Button, themeColorA[0], themeColorA[1], themeColorA[2], themeColorA[3]);
		style.setColor(ImGuiCol.ButtonActive,themeColorB[0], themeColorB[1], themeColorB[2], themeColorB[3]);
		style.setColor(ImGuiCol.Tab,  	themeColorA[0], themeColorA[1], themeColorA[2], themeColorA[3]);
		style.setColor(ImGuiCol.TabSelected, themeColorB[0], themeColorB[1], themeColorB[2], themeColorB[3]);
		style.setColor(ImGuiCol.TabActive,  themeColorC[0], themeColorC[1], themeColorC[2], themeColorC[3]);
		style.setColor(ImGuiCol.FrameBg,  themeColorA[0], themeColorA[1], themeColorA[2], themeColorA[3]);
		style.setColor(ImGuiCol.FrameBgActive,  themeColorB[0], themeColorB[1], themeColorB[2], themeColorB[3]);
		style.setColor(ImGuiCol.FrameBgHovered,  themeColorC[0], themeColorC[1], themeColorC[2], themeColorC[3]);

		style.setFrameRounding(5f);
		style.setChildBorderSize(0);

	}

}