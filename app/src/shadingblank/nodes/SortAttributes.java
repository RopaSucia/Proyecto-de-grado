package shadingblank.nodes;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SortAttributes {

	private final NodeAttribute[] attributes;

	private final List<Attribute1f> att1f;
	private final List<Attribute2f> att2f;
	private final List<Attribute3f> att3f;
	private final List<Attribute4f> att4f;

	private final List<TextAttribute> attTxt;
	private final List<ColorAttribute> attCol;

	public int size1f, size2f, size3f, size4f, sizeTxt, sizeCol;

	public SortAttributes(NodeAttribute[] attributes) {
		this.attributes = attributes;

		att1f = new ArrayList<>();
		att2f = new ArrayList<>();
		att3f = new ArrayList<>();
		att4f = new ArrayList<>();

		attTxt = new ArrayList<>();
		attCol = new ArrayList<>();

		// son número

		for (NodeAttribute att : this.attributes) {
			Pattern pattern = Pattern.compile("^\\d{2}");
			Matcher matcher = pattern.matcher(att.name);
			Integer size;

			if (matcher.find()) { 
				size = Integer.parseInt(matcher.group());

				switch (size) {
					case 1:
						att1f.add((Attribute1f) att);
						break;
					case 2:
						att2f.add((Attribute2f) att);
						break;
					case 3:
						att3f.add((Attribute3f) att);
						break;
					case 4:
						att4f.add((Attribute4f) att);
						break;
					default:
						att1f.add((Attribute1f) att);
						break;
				}
			}

			//no son número

			pattern = Pattern.compile("^[a-zA-Z]+(?=-)");
			matcher = pattern.matcher(att.name);

			if(matcher.find()) {
				if(matcher.group().equals("txt")) {
					attTxt.add((TextAttribute)att);
				}
				if(matcher.group().equals("col")) {
					attCol.add((ColorAttribute)att);
				}
			}
		}

		size1f = att1f.size();
		size2f = att2f.size();
		size3f = att3f.size();
		size4f = att4f.size();

		sizeTxt = attTxt.size();
		sizeCol = attCol.size();

	}

	public void getAttributes1f(Attribute1f[] attributes) {
		att1f.toArray(attributes);
	}

	public void getAttributes2f(Attribute2f[] attributes) {
		att2f.toArray(attributes);
	}

	public void getAttributes3f(Attribute3f[] attributes) {
		att3f.toArray(attributes);
	}

	public void getAttributes4f(Attribute4f[] attributes) {
		att4f.toArray(attributes);
	}

	public void getAttributesTxt(TextAttribute[] attributes) {
		attTxt.toArray(attributes);
	}

	public void getAttributesCol(ColorAttribute[] attributes) {
		attCol.toArray(attributes);
	}

	public List<Attribute1f> getAttributes1f() {
		return att1f;
	}

	public List<Attribute2f> getAttributes2f() {
		return att2f;
	}

	public List<Attribute3f> getAttributes3f() {
		return att3f;
	}

	public List<Attribute4f> getAttributes4f() {
		return att4f;
	}

	public List<TextAttribute> getAttributesTxt() {
		return attTxt;
	}

	public List<ColorAttribute> getAttributesCol() {
		return attCol;
	}
}