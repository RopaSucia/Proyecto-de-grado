package shadingblank;

import shadingblank.rendering.*;
import shadingblank.workspace.NodeManager;
import shadingblank.workspace.Scene;
import shadingblank.workspace.ui.*;
import shadingblank.nodes.Node;

import java.util.ArrayList;
import java.util.List;

import shadingblank.nodes.CameraNode;
import shadingblank.nodes.MeshNode;

public class Launcher extends Motor{

	public static Launcher instance;

	List<Node> nodes = new ArrayList<>();

	Mesh mesh;

	FrameBuffer frame;

	Shader vertexShader;
	Shader fragmentShader;
	ShaderProgram program;

	MeshNode meshN;
	CameraNode camera;

	Texture tex, texFer;

	double[] mousePos = new double[2];
	double[] lastMousePos = new double[2];

	String vertexSrc = ResourceFiles.getString("resources/basicVertex.vert");
	String fragmentScr = ResourceFiles.getString("resources/basicFragment.frag");

	String imagen = "C:\\Users\\USER\\Desktop\\ShadingBlank\\app\\resources\\Captura.PNG";

	@Override
	public void init() {
		mesh = new Mesh("mesh.obj", new float[]{
			-0.5f, 0.5f, 0,
			-0.5f, -0.5f, 0,
			0.5f, -0.5f, 0,
			0.5f, 0.5f, 0
		}, new int[]{
			0, 1, 2, 2, 3, 0
		});
		backgroundColor(0, 0, 1, 1);

		frame = new FrameBuffer(100, 100);

		vertexShader = new Shader("basicVertex.vert", vertexSrc, Shader.VERTEX_SHADER);

		fragmentShader = new Shader("basicFragment.frag", fragmentScr, Shader.FRAGMENT_SHADER);

		program = new ShaderProgram(vertexShader, fragmentShader, null);
		program.use();

		Scene scene = new Scene();

		MainSpace space = new MainSpace(frame, scene.nodes, scene.resources);

		Node node = new Node("Nuevo nodo");

		meshN = new MeshNode("malla", mesh);
		camera = new CameraNode("camara", space.getViewportSize());

		tex = new Texture("Advance-War", imagen);
		texFer = new Texture("Uyama-Hiroto", "C:\\Users\\USER\\Desktop\\J\\500x500.jpg");

		resourceManager.add(tex);
		resourceManager.add(texFer);
		resourceManager.add(vertexShader);
		resourceManager.add(fragmentShader);
		resourceManager.add(mesh);

		scene.nodes.add(node);
		scene.nodes.add(meshN);
		scene.nodes.add(camera);

		window.addLayer(space);
	}

	@Override
	public void close() {
		
	}

	@Override
	public void loop() {
		frame.begin();
		frame.backgroundColor(0, 1, 1, 1);

		double [] ms = eventsManager.mousePos();

		mousePos[0] = ms[0];
		mousePos[1] = ms[1];

		if(eventsManager.isKeyPressed('W')) {
			camera.moveZ(0.1f);
		}

		if(eventsManager.isKeyPressed('S')) {
			camera.moveZ(-0.1f);
		}

		if(eventsManager.isKeyPressed('A')) {
			camera.moveX(0.1f);
		}

		if(eventsManager.isKeyPressed('D')) {
			camera.moveX(-0.1f);
		}

		if(eventsManager.mouseLeftClick()) {

		}

		if(eventsManager.mouseRightClick()) {
			camera.rotateX((float)(mousePos[1] - lastMousePos[1]) / -5f);
			camera.rotateY((float)(mousePos[0] - lastMousePos[0]) / 5f);
		}

		program.uniformMat4("model", meshN.getModelMatrix());
		program.uniformMat4("view", camera.getViewMat());
		program.uniformMat4("projection", camera.getProjectionMat());

		mesh.draw();
		frame.close();
		window.backgroundColor(0, 0, 1, 1);

		lastMousePos[0] = mousePos[0];
		lastMousePos[1] = mousePos[1];
	}

	public static void main(String [] args) {

		instance = new Launcher();
		instance.title = "ShadingBlank";
		instance.start();
	}
}