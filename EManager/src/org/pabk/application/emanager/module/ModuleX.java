package org.pabk.application.emanager.module;

import java.util.ArrayList;

public class ModuleX {
	private String name;
	private String className;
	private int priority;
	private ArrayList<String> required;
	private String description;
	private boolean critical;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public ArrayList<String> getRequired() {
		return required;
	}
	public void setRequired(ArrayList<String> required) {
		this.required = required;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isCritical() {
		return critical;
	}
	public void setCritical(boolean critical) {
		this.critical = critical;
	}
}
