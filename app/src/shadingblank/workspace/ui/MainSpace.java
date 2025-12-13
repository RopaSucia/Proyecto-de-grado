package shadingblank.workspace.ui;

import java.util.List;

import imgui.ImGui;
import imgui.ImGuiStyle;
import imgui.ImVec2;
import shadingblank.Launcher;
import shadingblank.nodes.ViewportNode;
import shadingblank.nodes.Node;
import shadingblank.rendering.FrameBuffer;
import shadingblank.workspace.NodeManager;
import shadingblank.workspace.ResourceManager;
import imgui.flag.ImGuiWindowFlags;
import imgui.flag.ImGuiChildFlags;
import imgui.flag.ImGuiCol;

public class MainSpace extends Panel implements shadingblank.imguiconfig.GuiLayer {

	private int windowFlags =

			ImGuiWindowFlags.NoCollapse |
			ImGuiWindowFlags.NoResize |
			ImGuiWindowFlags.NoMove | 
			ImGuiWindowFlags.NoScrollbar |
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

	private int subchildFlags = 
			ImGuiChildFlags.Border |
			ImGuiChildFlags.ResizeY;

	private ViewportNode buffer;

	private ImVec2 pos;
	private ImVec2 modelViewSize;
	private float [] viewportSize;

	private ToolsModule moduleVisor1;
	private ResourceVisor resourceVisor;

	private ImGuiStyle style;

	public MainSpace(ViewportNode buffer, NodeManager nm, ResourceManager rm) {
		this.buffer = buffer;
		pos = new ImVec2();
		modelViewSize = new ImVec2();

		viewportSize = new float[2];
		style = ImGui.getStyle();

		style.setChildRounding(5f);
		style.setColor(ImGuiCol.WindowBg, 0.05f, 0.05f, 0.05f, 1f);
		style.setColor(ImGuiCol.ChildBg, 0.01f, 0.01f, 0.01f, 1f);
		style.setColor(ImGuiCol.Button, .45f, .45f, .45f, .45f);
		style.setCellPadding(40f, 4f);
		style.setTabBorderSize(10f);
		style.setButtonTextAlign(0f, 9f);
		style.setFrameRounding(5f);
		style.setFramePadding(4f, 3f);
		style.setChildBorderSize(0);

		moduleVisor1 = new ToolsModule(nm, rm);

		resourceVisor = new ResourceVisor(Launcher.instance.resourceManager.getResources());
	}

	@Override
	public void update() {
		ImGui.setNextWindowSize(window.width(), window.height());
		ImGui.setNextWindowPos(pos);

		ImGui.begin(" ", windowFlags);

		//Menu bar

		ImVec2 available = ImGui.getContentRegionAvail();

		ImGui.beginChild("Menu", new ImVec2(available.x, 0), passiveChildFlags, childWindowFlags);
			ImGui.text("el menu :D");
		ImGui.endChild();

		//workspace

		if(ImGui.getWindowWidth() >= modelViewSize.x) {
			ImGui.beginChild("modelName", childFlags, childWindowFlags);			
		}else{
			ImGui.beginChild("modelName", passiveChildFlags, childWindowFlags);
		}
			buffer.update();
			modelViewSize = ImGui.getWindowSize();
			viewportSize[0] = modelViewSize.x;
			viewportSize[1] = modelViewSize.y;
			ImGui.image(buffer.buffer.getTexture(), modelViewSize);

		ImGui.endChild();

		// ----------

		ImGui.sameLine();

		//code and nodetree

		ImGui.beginChild("NodeView",new ImVec2(0, modelViewSize.y), passiveChildFlags, childWindowFlags);
			ImGui.pushStyleColor(ImGuiCol.ChildBg, .2f, .2f, .2f, 1f);
			ImGui.beginChild("NodeView-Visor1", subchildFlags, childWindowFlags | ImGuiWindowFlags.MenuBar);

			moduleVisor1.Attributes();

			ImGui.endChild();
			ImGui.popStyleColor();

			available = ImGui.getContentRegionAvail();
			if(available.y > 0) {
				ImGui.pushStyleColor(ImGuiCol.ChildBg, .2f, .2f, .2f, 1f);
				ImGui.beginChild("NodeView-Visor2", new ImVec2(0, available.y), passiveChildFlags, childWindowFlags | ImGuiWindowFlags.MenuBar);

				moduleVisor1.treeNode("modelName");

				ImGui.endChild();
				ImGui.popStyleColor();				
			}


		ImGui.endChild();

		//Resource

		available = ImGui.getContentRegionAvail();
		if(available.y > 0) {
			ImGui.beginChild("materiales", available, passiveChildFlags, childWindowFlags);

			resourceVisor.resourceVisor();

			ImGui.endChild();
		}
		ImGui.end();

		//configInterfaceColor();
	}

	public float [] getViewportSize() {
		return viewportSize;
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
		style.setColor(ImGuiCol.Button, buttonCol[0],  buttonCol[1],  buttonCol[2],  buttonCol[3]);

		ImGui.colorEdit4("hover button color", hoverButtonCol);
		style.setColor(ImGuiCol.ButtonHovered,
		 hoverButtonCol[0], hoverButtonCol[1], hoverButtonCol[2], hoverButtonCol[3]);

		ImGui.colorEdit4("select button color", selectButtonCol);
		style.setColor(ImGuiCol.ButtonActive,
		 selectButtonCol[0], selectButtonCol[1], selectButtonCol[2], selectButtonCol[3]);

		ImGui.end();

	}

}