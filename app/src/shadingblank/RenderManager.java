package shadingblank;

import java.util.List;

import shadingblank.rendering.*;

import java.util.ArrayList;

public class RenderManager {

	public FrameBuffer currentFrameBuffer;

	public final List<Mesh> models;

	public RenderManager() {
		models = new ArrayList<>();
	}

	public void draw() {

		if(currentFrameBuffer != null) 
			currentFrameBuffer.begin();

		for(Mesh model: models) {

			model.draw();

		}

		if(currentFrameBuffer != null) 
			currentFrameBuffer.close();
	}

	public void addMesh(Mesh mesh) {
		models.add(mesh);
	}
}