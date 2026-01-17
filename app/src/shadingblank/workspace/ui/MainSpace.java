package shadingblank.workspace.ui;

import imgui.ImGui;
import imgui.ImGuiStyle;
import imgui.ImVec2;
import shadingblank.workspace.nodes.ViewportNode;
import imgui.flag.ImGuiWindowFlags;
import imgui.flag.ImGuiChildFlags;
import imgui.flag.ImGuiCol;

public class MainSpace extends Panel {

	private int windowFlags =

			ImGuiWindowFlags.NoCollapse |
					ImGuiWindowFlags.NoResize |
					ImGuiWindowFlags.NoMove |
					ImGuiWindowFlags.NoScrollbar |
					ImGuiWindowFlags.MenuBar |
					ImGuiWindowFlags.NoTitleBar;

	private int childWindowFlags =

			ImGuiWindowFlags.NoScrollbar;

	private int childFlags =

			ImGuiChildFlags.Border |
					ImGuiChildFlags.ResizeX |
					ImGuiChildFlags.ResizeY;

	private int passiveChildFlags =

			ImGuiChildFlags.Border |
					ImGuiChildFlags.AutoResizeY;

	private int subchildFlags = ImGuiChildFlags.Border |
			ImGuiChildFlags.ResizeY;


	private ImVec2 pos;
	private ImVec2 modelViewSize;
	//private float[] viewportSize;

	public ToolsModule toolsModule1;
	public ResourceVisor resourceVisor;

	private ImGuiStyle style;

	public MainSpace() {
		pos = new ImVec2();
		modelViewSize = new ImVec2();

		//viewportSize = new float[2];
		style = ImGui.getStyle();

		style.setChildRounding(5f);
		style.setColor(ImGuiCol.WindowBg, 0.05f, 0.05f, 0.05f, 1f);
		style.setColor(ImGuiCol.ChildBg, 0.01f, 0.01f, 0.01f, 1f);
		style.setColor(ImGuiCol.Button, .45f, .45f, .45f, .45f);
		style.setFrameRounding(5f);
		style.setChildBorderSize(0);

		toolsModule1 = new ToolsModule();

		resourceVisor = new ResourceVisor();
	}

	@Override
	public void update() {
		ImGui.setNextWindowSize(window.width(), window.height());
		ImGui.setNextWindowPos(pos);

		ImGui.begin(" ", windowFlags);

		// -------- MENU BAR --------

		if(ImGui.beginMenuBar()) {
			ImGui.menuItem("files");
			ImGui.menuItem("edit");
			ImGui.menuItem("tools");
			ImGui.endMenuBar();
		}

		// -------- SECCTIONS --------

		ImVec2 available = ImGui.getContentRegionAvail();

		ImGui.beginChild("Menu", new ImVec2(available.x, 40f), passiveChildFlags, childWindowFlags | ImGuiWindowFlags.MenuBar);
		if (ImGui.beginMenuBar()) {
			if (ImGui.beginTabBar("menubar-tab")) {

				if (ImGui.beginTabItem("scene")) {
					ImGui.endTabItem();
				}

				if (ImGui.beginTabItem("nodes")) {
					ImGui.endTabItem();
				}

				if (ImGui.beginTabItem("render")) {
					ImGui.endTabItem();
				}

				ImGui.endTabBar();
			}
			ImGui.endMenuBar();
		}

		ImGui.endChild();

		// -------- workspace --------

		toolsModule1.viewport();
		modelViewSize.set(toolsModule1.viewportSize()[0],toolsModule1.viewportSize()[1]);

		// ----------

		ImGui.sameLine();

		// -------- TOOLS SLOTS 1 AND 2--------

		ImGui.beginChild("NodeView", new ImVec2(0, modelViewSize.y), passiveChildFlags, childWindowFlags);
		ImGui.beginChild("NodeView-Visor1", subchildFlags, childWindowFlags);

		toolsModule1.update();

		ImGui.endChild();

		available = ImGui.getContentRegionAvail();
		if (available.y > 0) {
			ImGui.beginChild("NodeView-Visor2", new ImVec2(0, available.y), passiveChildFlags,
					childWindowFlags);

			toolsModule1.update();

			ImGui.endChild();
		}

		ImGui.endChild();

		// -------- Resource --------

		available = ImGui.getContentRegionAvail();
		if (available.y > 0) {
			ImGui.beginChild("materiales", available, passiveChildFlags, childWindowFlags);

			resourceVisor.resourceVisor();

			ImGui.endChild();
		}
		ImGui.end();
	}

	public float[] getViewportSize() {
		return toolsModule1.viewportSize();
	}

	float[] windowBg = new float[4];
	float[] childBg = new float[4];
	float[] buttonCol = new float[4];
	float[] hoverButtonCol = new float[4];
	float[] selectButtonCol = new float[4];

	public void configInterfaceColor() {

		ImGui.begin("config Color");

		ImGui.colorEdit4("Window background color", windowBg);
		style.setColor(ImGuiCol.WindowBg, windowBg[0], windowBg[1], windowBg[2], windowBg[3]);

		ImGui.colorEdit4("child background color", childBg);
		style.setColor(ImGuiCol.ChildBg, childBg[0], childBg[1], childBg[2], childBg[3]);

		ImGui.colorEdit4("button color", buttonCol);
		style.setColor(ImGuiCol.Button, buttonCol[0], buttonCol[1], buttonCol[2], buttonCol[3]);

		ImGui.colorEdit4("hover button color", hoverButtonCol);
		style.setColor(ImGuiCol.ButtonHovered,
				hoverButtonCol[0], hoverButtonCol[1], hoverButtonCol[2], hoverButtonCol[3]);

		ImGui.colorEdit4("select button color", selectButtonCol);
		style.setColor(ImGuiCol.ButtonActive,
				selectButtonCol[0], selectButtonCol[1], selectButtonCol[2], selectButtonCol[3]);

		ImGui.end();

	}

}