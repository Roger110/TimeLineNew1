package com.timeline.common;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.timeline.bean.ReturnInfo;
import com.timeline.bean.ReturnMsg;
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
}
