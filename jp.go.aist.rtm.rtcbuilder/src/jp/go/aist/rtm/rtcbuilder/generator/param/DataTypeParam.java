package jp.go.aist.rtm.rtcbuilder.generator.param;

import java.util.ArrayList;
import java.util.List;

public class DataTypeParam {
	private boolean addition;
	private String fullPath;
	private String content;
	private List<String> definedTypes;
	
	public DataTypeParam() {
		addition = false;
		fullPath = "";
		content = "";
		definedTypes = new ArrayList<String>();
	}

	public DataTypeParam(String idlPath) {
		addition = false;
		fullPath = idlPath;
		content = "";
		definedTypes = new ArrayList<String>();
	}
	
	public boolean isAddition() {
		return addition;
	}

	public void setAddition(boolean addition) {
		this.addition = addition;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getFullPath() {
		return fullPath;
	}

	public void setFullPath(String fullPath) {
		this.fullPath = fullPath;
	}

	public List<String> getDefinedTypes() {
		return definedTypes;
	}
}
