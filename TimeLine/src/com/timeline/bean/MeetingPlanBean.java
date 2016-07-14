package com.timeline.bean;

import java.util.ArrayList;
import java.util.List;

public class MeetingPlanBean {
	public String getTimeString() {
		return timeString;
	}

	public void setTimeString(String timeString) {
		this.timeString = timeString;
	}

	public List<MeetingDetailPlanBean> getDetails() {
		return details;
	}

	public void setDetails(List<MeetingDetailPlanBean> details) {
		this.details = details;
	}

	private String timeString;
	
	private List<MeetingDetailPlanBean> details = new ArrayList<MeetingDetailPlanBean>();
}
