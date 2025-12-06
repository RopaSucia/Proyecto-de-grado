package shadingblank.rendering;

public abstract class Resource {

	public final String name;

	public Resource(String name) {
		this.name = name;
	}

	public abstract int getReference();
}