package com.timeline.bean;

public class CalendarTagBean {
	public long id;
	public String status;
	public long start;
	public long end;
	public String name;
	public String type;

	@Override
	public String toString() {
		return "CalendarTagBean [id=" + id + ", status=" + status + ", start="
				+ start + ", end=" + end + ", name=" + name + ", type=" + type
				+ "]";
	}

}
