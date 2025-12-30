package shadingblank.workspace.ui.modules;

import imgui.ImGui;
import imgui.flag.ImGuiCol;
import imgui.type.ImFloat;
import imgui.type.ImString;
import shadingblank.imguiconfig.GuiLayer;
import shadingblank.nodes.Attribute1f;
import shadingblank.nodes.Attribute2f;
import shadingblank.nodes.Attribute3f;
import shadingblank.nodes.Attribute4f;
import shadingblank.nodes.ColorAttribute;
import shadingblank.nodes.Node;
import shadingblank.nodes.NodeAttribute;
import shadingblank.nodes.ShaderAttribute;
import shadingblank.nodes.SortAttributes;
import shadingblank.nodes.TextAttribute;

public class AttributesModule implements GuiLayer {

	ImFloat float1;
	ImString string1;

	Node currentNode, lastNode;

	NodeAttribute[] atts;

	Attribute1f[] attribute1f;
	Attribute2f[] attribute2f;
	Attribute3f[] attribute3f;
	Attribute4f[] attribute4f;

	TextAttribute[] textAttributes;
	ColorAttribute[] colAttributes;
	ShaderAttribute[] shaAttributes;

	SortAttributes sorter;

	public AttributesModule() {
		float1 = new ImFloat();
		string1 = new ImString();
	}

	public void getAttributeList(Node node) {

		if (node != lastNode) {

			currentNode = node;

			sorter = new SortAttributes(node.attributes);

			atts = node.attributes;

			attribute1f = new Attribute1f[sorter.size1f];
			attribute2f = new Attribute2f[sorter.size2f];
			attribute3f = new Attribute3f[sorter.size3f];
			attribute4f = new Attribute4f[sorter.size4f];

			textAttributes = new TextAttribute[sorter.sizeTxt];
			colAttributes = new ColorAttribute[sorter.sizeCol];
			shaAttributes = new ShaderAttribute[sorter.sizeSha];

			sorter.getAttributes1f(attribute1f);
			sorter.getAttributes2f(attribute2f);
			sorter.getAttributes3f(attribute3f);
			sorter.getAttributes4f(attribute4f);

			sorter.getAttributesTxt(textAttributes);
			sorter.getAttributesCol(colAttributes);
			sorter.getAttributesShader(shaAttributes);
		}
	}

	public void update() {

		ImGui.pushStyleColor(ImGuiCol.ChildBg, 0.1f, 0.1f, 0.1f, 1f);
		ImGui.beginChild("attributes-node-wrap");
		if (atts != null) {

			int index = 0;

			ImGui.text(currentNode.name.value);

			for (TextAttribute att : textAttributes) {
				string1.set(att.value);
				ImGui.text(att.name);
				ImGui.sameLine();
				ImGui.inputText("textZ" + index++, string1);
				att.value = string1.get();
			}

			for (ColorAttribute att : colAttributes) {
				ImGui.text(att.name);
				ImGui.sameLine();
				ImGui.colorEdit4("col" + index++, att.get());
			}

			for (Attribute4f att : attribute4f) {
				ImGui.text(att.name);
				ImGui.sameLine();
				ImGui.inputFloat4("XYZW" + index++, att.get());
			}

			for (Attribute3f att : attribute3f) {
				ImGui.text(att.name);
				ImGui.sameLine();
				ImGui.inputFloat3("XYZ" + index++, att.get());
			}

			for (Attribute2f att : attribute2f) {
				ImGui.text(att.name);
				ImGui.sameLine();
				ImGui.inputFloat2("XY" + index++, att.get());
			}

			for (Attribute1f att : attribute1f) {
				float1.set(att.value);
				ImGui.text(att.name);
				ImGui.sameLine();
				ImGui.inputFloat("X" + index++, float1);
				att.value = float1.get();
			}

			for (ShaderAttribute att : shaAttributes) {
				ImGui.text(att.name);
				ImGui.image(1, 100f, 100f);
			}
		}

		ImGui.endChild();
		ImGui.popStyleColor();
	}

}