package shadingblank.workspace.ui.modules;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import imgui.ImGui;
import imgui.flag.ImGuiTableFlags;
import imgui.type.ImString;
import shadingblank.FilesManager;
import shadingblank.workspace.ui.Panel;

public class FileExplorerDialog extends Panel {

	ImString path, lastPath, filename;

	List<Path> files;

	boolean[] type;

	Path currentDirectory;

	private int fileListFlags = 

	ImGuiTableFlags.RowBg | ImGuiTableFlags.ScrollY;

	private boolean open;

	public FileExplorerDialog() {
		path = new ImString();
		lastPath = new ImString();
		filename = new ImString();
	}

	public void directory(String path) {
		this.path.set(path);

		files = FilesManager.getDirectoryFiles(path);
		currentDirectory = Paths.get(path);

		type = new boolean[files.size()];
		for(int i = 0; i < files.size(); i++) {
			type[i] = Files.isDirectory(files.get(i));
		}
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

				if(ImGui.button("return-directory")) {
					directory(FilesManager.parent(path.get()));
				}
				ImGui.sameLine();

				ImGui.inputText("direcction", path);
				if(!path.get().equals(lastPath.get())) {
					directory(path.get());
				}

				ImGui.beginChild("Directories-panel", ImGui.getContentRegionAvailX(), 450);
				childPadding(10f);

				// -------- LEFT --------
				ImGui.beginChild("User-directories",  140, ImGui.getContentRegionAvailY());
				childPadding(10f); // user-drs ^ 

				ImGui.button("Desktop");
				ImGui.button("Documents");
				ImGui.button("Music");
				ImGui.button("3D");

				ImGui.endChild(); // End user-drs

				ImGui.sameLine();

				// -------- RIGHT --------

				if (ImGui.beginTable("filess", 2, fileListFlags)) {

					ImGui.tableSetupColumn("files");
					ImGui.tableSetupColumn("type");
					ImGui.tableHeadersRow();

					if (files != null) {
						for (Path file : files) {
							ImGui.tableNextRow();
							ImGui.tableSetColumnIndex(0);

							// file/directory select button
							String fn = file.getFileName().toString(); // filename

							boolean isDirectory = Files.isDirectory(file);

							if(ImGui.button(fn)) {
								if(isDirectory) {
									directory(path + "\\" + fn);

								} else {
									filename.set(fn);
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

				ImGui.inputText("file-name", filename);
				ImGui.sameLine();

				if (ImGui.button("select"))
					open = false;
				ImGui.sameLine();

				if (ImGui.button("cancel"))
					open = false;
				ImGui.sameLine();

				ImGui.endChild();

				ImGui.endPopup();
			}
		}

		lastPath.set(path.get());
	}
}