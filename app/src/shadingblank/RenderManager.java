package shadingblank;

import java.util.List;

import shadingblank.workspace.nodes.MeshNode;
import shadingblank.rendering.*;
import shadingblank.workspace.NodeManager;

import java.util.ArrayList;

public class RenderManager{

	public final List<MeshNode> meshes;
	public final List<ShaderProgram> shaders; 

	public ShaderProgram currentShaderProgram;

	public FrameBuffer currentFrameBuffer;

	public RenderManager(NodeManager nodeManager) {
		meshes = nodeManager.meshNodes;
		shaders = new ArrayList<>();
	}

	public void draw() {

		if(currentFrameBuffer != null) 
			currentFrameBuffer.begin();


		for(MeshNode mesh: meshes) {

			ShaderProgram program = mesh.shader.value;
			if(currentShaderProgram != program && program != null) {
				program.use();
				currentShaderProgram = program;
			} else if (program != null){
				program.uniformMat4("model", mesh.getModelMatrix());
			}

			mesh.getMesh().draw();

		}

		if(currentFrameBuffer != null) 
			currentFrameBuffer.close();
	}

}