package shadingblank.workspace.ui;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import imgui.ImGui;
import imgui.ImVec2;
import imgui.type.ImFloat;
import imgui.type.ImString;
import shadingblank.imguiconfig.GuiLayer;
import shadingblank.nodes.Attribute1f;
import shadingblank.nodes.Attribute2f;
import shadingblank.nodes.Attribute3f;
import shadingblank.nodes.Node;
import shadingblank.nodes.NodeAttribute;
import shadingblank.nodes.SortAttributes;
import shadingblank.nodes.TextAttribute;

public class AttributesFrame implements GuiLayer{

	ImFloat float1;
	ImString string1;

	NodeAttribute [] atts;

	Attribute1f [] attribute1f;
	Attribute2f [] attribute2f;
	Attribute3f [] attribute3f;

	TextAttribute [] textAttributes;

	SortAttributes sorter;

	public AttributesFrame() {
		float1 = new ImFloat();
		string1 = new ImString();
	}

	public void getAttributeList(Node node) {
		sorter = new SortAttributes(node.attributes);

		atts = node.attributes;

		attribute1f = new Attribute1f[sorter.size1f];
		attribute2f = new Attribute2f[sorter.size2f];
		attribute3f = new Attribute3f[sorter.size3f];

		textAttributes = new TextAttribute[sorter.sizeTxt];

		sorter.getAttributes1f(attribute1f);
		sorter.getAttributes2f(attribute2f);
		sorter.getAttributes3f(attribute3f);

		sorter.getAttributesTxt(textAttributes);
	}

	public void update() {

		if(atts != null) {

			for(TextAttribute att: textAttributes) {
				string1.set(att.value);
				ImGui.text(att.name);
				ImGui.sameLine();
				ImGui.inputText("textZ", string1);
				att.value = string1.get();

			}

			for(Attribute3f att: attribute3f) {
				ImGui.text(att.name);
				ImGui.sameLine();
				ImGui.inputFloat3("XYZ", att.get());
			}

			for(Attribute2f att: attribute2f) {
				ImGui.text(att.name);
				ImGui.sameLine();
				ImGui.inputFloat2("XY", att.get());
			}

			for(Attribute1f att: attribute1f) {
				float1.set(att.value);
				ImGui.text(att.name);
				ImGui.sameLine();
				ImGui.inputFloat("X", float1);
			}
		}

	}

}