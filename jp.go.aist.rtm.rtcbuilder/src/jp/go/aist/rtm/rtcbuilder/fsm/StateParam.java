package jp.go.aist.rtm.rtcbuilder.fsm;

import java.util.ArrayList;
import java.util.List;

public class StateParam {
	private String initialState; //SubStateの開始ノード
	private int history;
	private String historyNodeName;
	private String name;
	private String parentName;
	private boolean hasEntry;
	private boolean hasExit;
	private String dataName;
	private boolean initial;	//対象ノードがinitialノードか？
	
	private List<StateParam> stateList = new ArrayList<StateParam>(); 
	private List<TransitionParam> transList = new ArrayList<TransitionParam>(); 

	private List<StateParam> allStateList = new ArrayList<StateParam>(); 
	private List<TransitionParam> allTransList = new ArrayList<TransitionParam>(); 
	
	public String getInitialState() {
		return initialState;
	}
	public void setInitialState(String initialState) {
		this.initialState = initialState;
	}
	
	public int getHistory() {
		return history;
	}
	public void setHistory(int history) {
		this.history = history;
	}
	
	public String getHistoryNodeName() {
		return historyNodeName;
	}
	public void setHistoryNodeName(String historyNodeName) {
		this.historyNodeName = historyNodeName;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	
	public boolean isHasEntry() {
		return hasEntry;
	}
	public void setHasEntry(boolean hasEntry) {
		this.hasEntry = hasEntry;
	}
	
	public boolean isHasExit() {
		return hasExit;
	}
	public void setHasExit(boolean hasExit) {
		this.hasExit = hasExit;
	}
	
	public String getDataName() {
		return dataName;
	}
	public void setDataName(String dataName) {
		this.dataName = dataName;
	}
	
	public boolean isInitial() {
		return initial;
	}
	public void setInitial(boolean initial) {
		this.initial = initial;
	}
	
	public List<StateParam> getStateList() {
		return stateList;
	}
	public List<TransitionParam> getTransList() {
		return transList;
	}
	
	public List<StateParam> getAllStateList() {
		return allStateList;
	}
	
	public List<StateParam> getAllValidStateList() {
		List<StateParam> result = new ArrayList<StateParam>();
		for(StateParam each : allStateList) {
			if(each.isInitial()) continue;
			result.add(each);
		}
		return result;
	}
	public List<TransitionParam> getAllTransList() {
		return allTransList;
	}
}
