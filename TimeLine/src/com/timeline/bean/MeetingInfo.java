package com.timeline.bean;

public class MeetingInfo {
	private String id;
    private String subject;
    private String describe;
    private String address;
    private String start_time; //���鿪ʼʱ�䣨��ÿ��0��0��0�뿪ʼ���㣬��λ�롣��
    private String end_time; //�������ʱ�䣨��ÿ��0��0��0�뿪ʼ���㣬��λ�롣��
    private String servey_url;//���黥����ҳ����
    private String servey_st; //���黥����ҳ��������״̬  1 ����  2 �ر�
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	public String getServey_url() {
		return servey_url;
	}
	public void setServey_url(String servey_url) {
		this.servey_url = servey_url;
	}
	public String getServey_st() {
		return servey_st;
	}
	public void setServey_st(String servey_st) {
		this.servey_st = servey_st;
	}
}
