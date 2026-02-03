package shadingblank.workspace.ui.modules;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import imgui.ImGui;
import imgui.flag.ImGuiTableFlags;
import imgui.type.ImString;
import shadingblank.workspace.ui.Panel;

public class FileExplorerDialog extends Panel {

	private final ImString 
		directoryPath, // current Dir
		lastDirectoryPath, // last dir
		filePath;	// current selected file

	private List<Path> directoryElements;

	private int fileListFlags = 

	ImGuiTableFlags.RowBg | ImGuiTableFlags.ScrollY;

	private ExplorerReturnCallback callback;
	private String type = "shaders";
	private boolean open;

	public FileExplorerDialog(ExplorerReturnCallback callback) {
		directoryPath = new ImString(1024);
		lastDirectoryPath = new ImString(1024);
		filePath = new ImString(1024);

		this.callback = callback;
	}

	public void init(String dir) {
		directoryElements = files.getDirectoryFiles(dir);
		lastDirectoryPath.set(directoryPath.get());
		directoryPath.set(dir);
		open = true;
	}

	@Override
	public void update() {
		if (open) {
			if (!ImGui.isPopupOpen("choose one file")) {
				ImGui.openPopup("choose one file");
			}

			ImGui.setNextWindowSize(800, 600);
			if (ImGui.beginPopupModal("choose one file")) {


				// -------- FILE DIRECTION BAR --------



				if(ImGui.button("return-directory")) { // this button return to last directory
					init(lastDirectoryPath.get());
				}

				ImGui.sameLine();

				// manual directory browser

				ImGui.inputText("direction", directoryPath); 
				if(!directoryPath.get().equals(lastDirectoryPath.get())) {
					init(directoryPath.get());

				}

				ImGui.beginChild("Directories-panel", ImGui.getContentRegionAvailX(), 450);
				childPadding(10f);



				// -------- LEFT --------
				ImGui.beginChild("User-directories",  140, ImGui.getContentRegionAvailY());
				childPadding(10f); // user-drs ^ 

				directAccess("HOME-DESKTOP", files.USERHOME);

				ImGui.endChild(); // End user-drs

				ImGui.sameLine();

				// -------- RIGHT --------

				if (ImGui.beginTable("filess", 2, fileListFlags)) {

					ImGui.tableSetupColumn("files");
					ImGui.tableSetupColumn("type");
					ImGui.tableHeadersRow();

					if (directoryElements != null) {
						for (Path file : directoryElements) {
							ImGui.tableNextRow();
							ImGui.tableSetColumnIndex(0);

							// file/directory select button
							String elementName = file.getFileName().toString(); // filename

							boolean isDirectory = Files.isDirectory(file);

							// element button
							if(ImGui.button(elementName)) {

								String elementPath = directoryPath.get() + "/" + elementName;

								if(isDirectory) {
									init(elementPath);

								} else {
									filePath.set(elementPath);
								}
							}

							ImGui.tableSetColumnIndex(1);
							ImGui.text((isDirectory) ? "directory" : "file");
							
						}
					}

					ImGui.endTable();
				}

				ImGui.endChild();

				// -------- BOTTOM --------

				ImGui.beginChild("Config-user-file");
				childPadding(10f);

				ImGui.inputText("file-name", filePath);
				ImGui.sameLine();

				if (ImGui.button("select")){
					callback.call(filePath.get(), type);
					open = false;
				}

				ImGui.sameLine();

				if (ImGui.button("cancel")){
					callback.call(null, type);
					open = false;
				}

				ImGui.sameLine();

				ImGui.setNextItemWidth(60f);

				if(ImGui.beginCombo("###typesOfFiles", type)) {
				
					boolean isSelected = ("meshes".equals(type));
					if(ImGui.selectable("meshes", isSelected)) {
						type = "meshes";
					}

					if(isSelected) {
						ImGui.setItemDefaultFocus();
					}

					isSelected = ("textures".equals(type));
					if(ImGui.selectable("textures", isSelected)) {
						type = "textures";
					}

					if(isSelected) {
						ImGui.setItemDefaultFocus();
					}

					isSelected = ("shaders".equals(type));
					if(ImGui.selectable("shaders", isSelected)) {
						type = "shaders";
					}

					if(isSelected) {
						ImGui.setItemDefaultFocus();
					}

				ImGui.endCombo();
			}

				ImGui.endChild();
				ImGui.endPopup();
			}
		}

		lastDirectoryPath.set(directoryPath.get());
	}

	private void directAccess(String accessName, String dir) {
		if(ImGui.button(accessName)) {
			init(dir);
		}
	}
}