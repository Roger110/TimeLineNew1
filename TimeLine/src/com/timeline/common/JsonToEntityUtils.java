package com.timeline.common;

import java.lang.reflect.Type;
import java.util.LinkedList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.timeline.bean.MeetingDescribe;
import com.timeline.bean.MeetingInfo;
import com.timeline.bean.ReturnInfo;
import com.timeline.bean.ReturnMsg;
import com.timeline.bean.SigninPerson;
import com.timeline.bean.User;

public class JsonToEntityUtils {
	
	public static ReturnInfo jsontoReinfo(String json) {
		Gson gson = new Gson();  
		ReturnInfo infos = gson.fromJson(json, ReturnInfo.class); 
		return infos;
		
	}
	
	public static ReturnMsg jsontoRemsg(String json) {
		Gson gson = new Gson();  
		ReturnMsg info = gson.fromJson(json, ReturnMsg.class); 
		return info;
		
	}
	
	public static User jsontoUser(String json) {
		Gson gson = new Gson();  
		User info = gson.fromJson(json, User.class); 
		return info;
		
	}
	
	public static MeetingInfo[] jsontoMeetingInfo(String json) {
		Gson gson = new Gson();  
		Type listType = new TypeToken<LinkedList<MeetingInfo>>(){}.getType(); 
		LinkedList<MeetingInfo> list = gson.fromJson(json, listType); 
		MeetingInfo[] info = gson.fromJson(json, MeetingInfo[].class); 
		if (info.length == 0) {
			return null;
		}
		return info;
	}
	
	public static MeetingDescribe jsontoMeetingDes(String json) {
		Gson gson = new Gson();  
		MeetingDescribe info = gson.fromJson(json, MeetingDescribe.class); 
		return info;
		
	}
	
	public static SigninPerson[] jsontoSigninPerson(String json) {
		Gson gson = new Gson();  
		Type listType = new TypeToken<LinkedList<SigninPerson>>(){}.getType(); 
		LinkedList<SigninPerson> list = gson.fromJson(json, listType); 
		SigninPerson[] info = gson.fromJson(json, SigninPerson[].class); 
		if (info.length == 0) {
			return null;
		}
		return info;
	}
}
