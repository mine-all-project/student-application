package com.entity;

import java.util.List;

public class Button {
	private String id;
	private String name;
	private List<Buttons> buttons;
	public class Buttons{
		private String name;
		private String link;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getLink() {
			return link;
		}
		public void setLink(String link) {
			this.link = link;
		}
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Buttons> getButtons() {
		return buttons;
	}
	public void setButtons(List<Buttons> buttons) {
		this.buttons = buttons;
	}
}