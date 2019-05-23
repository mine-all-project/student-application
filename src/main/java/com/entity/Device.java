package com.entity;

public class Device {
	private String id;
	private String number;
	private String name;
	private String status;
	private String why;
	private String insert_time;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getWhy() {
		return why;
	}
	public void setWhy(String why) {
		this.why = why;
	}
	public String getInsert_time() {
		return insert_time;
	}
	public void setInsert_time(String insert_time) {
		this.insert_time = insert_time;
	}
	@Override
	public String toString() {
		return "Device [id=" + id + ", number=" + number + ", name=" + name + ", status=" + status + ", why=" + why
				+ ", insert_time=" + insert_time + "]";
	}
}
