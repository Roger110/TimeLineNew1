package com.timeline.bean;

public class MeetingDescribe {
	  private String id;
	  private String subject;// 标题
	  private String describe; // 描述
	  private String content; // 详细内容
	  private String address; // 地址
	  private String max_holder;
	  private String subject_img;
	  private String is_join; // 1报名  2待定  3拒绝  4无
	  private String sponsor; // 主办方
	  private String times; // 日期

	  public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMax_holder() {
		return max_holder;
	}
	public void setMax_holder(String max_holder) {
		this.max_holder = max_holder;
	}
	public String getSubject_img() {
		return subject_img;
	}
	public void setSubject_img(String subject_img) {
		this.subject_img = subject_img;
	}
	public String getIs_join() {
		return is_join;
	}
	public void setIs_join(String is_join) {
		this.is_join = is_join;
	}
	public String getSponsor() {
		return sponsor;
	}
	public void setSponsor(String sponsor) {
		this.sponsor = sponsor;
	}
	public String getTimes() {
		return times;
	}
	public void setTimes(String times) {
		this.times = times;
	}

}
