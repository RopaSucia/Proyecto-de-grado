package shadingblank.workspace.ui;

import imgui.ImGui;
import imgui.ImGuiStyle;
import imgui.ImVec2;
import shadingblank.workspace.nodes.ViewportNode;
import imgui.flag.ImGuiWindowFlags;
import imgui.flag.ImGuiChildFlags;
import imgui.flag.ImGuiCol;

public class UIRoot extends Panel {

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

	private UIColor color;

	public UIRoot() {
		pos = new ImVec2();
		modelViewSize = new ImVec2();

		//viewportSize = new float[2];
		color = new UIColor();
		color.setup();

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
}