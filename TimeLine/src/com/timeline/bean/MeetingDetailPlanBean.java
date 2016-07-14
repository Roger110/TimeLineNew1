package com.timeline.bean;

import java.util.List;

public class MeetingDetailPlanBean {
	private String detime;
	private List<String> plans;
	public String getDetime() {
		return detime;
	}
	public void setDetime(String detime) {
		this.detime = detime;
	}
	public List<String> getPlans() {
		return plans;
	}
	public void setPlans(List<String> plans) {
		this.plans = plans;
	}

}
