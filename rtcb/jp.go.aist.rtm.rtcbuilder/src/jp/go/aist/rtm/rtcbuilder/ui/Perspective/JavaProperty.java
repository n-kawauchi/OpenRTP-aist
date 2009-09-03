package jp.go.aist.rtm.rtcbuilder.ui.Perspective;

import java.util.ArrayList;
import java.util.List;

public class JavaProperty extends LanguageProperty {
	private String PerspectiveId = "org.eclipse.jdt.ui.JavaPerspective";
	private String PerspectiveName = "Java";
	private String PluginId = "org.eclipse.jdt";

	public String getPerspectiveId() {
		return PerspectiveId;
	}

	public String getPerspectiveName() {
		return PerspectiveName;
	}

	public String getPluginId() {
		return PluginId;
	}

	@Override
	public List<String> getNatures() {
		List<String> natures = new ArrayList<String>();
		natures.add("org.eclipse.jdt.core.javanature");
		return natures;
	}
}
