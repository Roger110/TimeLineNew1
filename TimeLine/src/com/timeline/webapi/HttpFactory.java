package com.timeline.webapi;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request.Method;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.timeline.app.AppConfig;
import com.timeline.app.AppContext;
import com.timeline.bean.ReturnInfo;
import com.timeline.common.JsonToEntityUtils;
import com.timeline.common.StringUtils;
import com.timeline.common.UIHelper;
import com.timeline.interf.VolleyListenerInterface;

public class HttpFactory {

	/**
	 * 获取验证码htp
	 * @param number
	 * @param type
	 */
	public static void getSend_SMS(final Context context,final String number,final String type, VolleyListenerInterface volleyListenerInterface) {
		String url = "http://event.gooddr.com/api/user/send_sms";
		StringRequest request = new StringRequest(Method.POST, url,
				volleyListenerInterface.responseListener(), 
				volleyListenerInterface.errorListener()) {  
			  @Override  
			  protected Map<String, String> getParams() throws AuthFailureError {  
			    Map<String, String> map = new HashMap<String, String>();  
			    map.put("mobile",number);  
			    map.put("type", type);  
			    return map;  
			  }  
			};  

		request.setRetryPolicy(new DefaultRetryPolicy(50000,
				DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
				DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
		AppContext.getInstance().mQueue.add(request);	
	}
	
	/**
	 * 注册http
	 * @param psw
	 * @param verify
	 * @param volleyListenerInterface
	 */
	public static void Register(final String psw,final String verify, VolleyListenerInterface volleyListenerInterface){
		String url = "http://event.gooddr.com/api/user/register";
		StringRequest request = new StringRequest(Method.POST, url,volleyListenerInterface.responseListener(), 
			volleyListenerInterface.errorListener())
			{  
			  @Override  
			  protected Map<String, String> getParams() throws AuthFailureError {  
			    Map<String, String> map = new HashMap<String, String>();  
			    map.put("mobile",AppContext.phoneNum);  
			    map.put("password", psw);  
			    map.put("uuid", AppContext.GetIMEI()); 
			    map.put("verify_code", verify); 
			    map.put("device_type", "3"); 
			    return map;  
			  }  
			};  

		request.setRetryPolicy(new DefaultRetryPolicy(50000,
				DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
				DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
		AppContext.getInstance().mQueue.add(request);
	}
	
	/**
	 * 登陆http
	 * @param psw
	 * @param verify
	 * @param volleyListenerInterface
	 */
	public static void Login(final String account ,final String psw ,VolleyListenerInterface volleyListenerInterface){
		String url = "http://event.gooddr.com/api/user/login";
		StringRequest request = new StringRequest(Method.POST, url,volleyListenerInterface.responseListener(), 
			volleyListenerInterface.errorListener())
			{  
			  @Override  
			  protected Map<String, String> getParams() throws AuthFailureError {  
			    Map<String, String> map = new HashMap<String, String>();  
			    map.put("mobile",account);  
			    map.put("password", psw);  
			    map.put("uuid", AppContext.GetIMEI()); ; 
			    map.put("device_type", "3"); 
			    return map;  
			  }  
			};  

		request.setRetryPolicy(new DefaultRetryPolicy(50000,
				DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
				DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
		AppContext.getInstance().mQueue.add(request);
	}
	/**
	 * 意见反馈http
	 * @param uid
	 * @param content
	 * @param volleyListenerInterface
	 */
	public static void FeedBack(final String content,VolleyListenerInterface volleyListenerInterface){
		String url = "http://event.gooddr.com/api/user/feedback";
		StringRequest request = new StringRequest(Method.POST, url,volleyListenerInterface.responseListener(), 
			volleyListenerInterface.errorListener())
			{  
			  @Override  
			  protected Map<String, String> getParams() throws AuthFailureError {  
			    Map<String, String> map = new HashMap<String, String>();  
			    map.put("user_id",AppContext.getUser().getId());  
			    map.put("login_token", AppContext.getUser().getLogin_token());  
			    map.put("content",content); 
			    return map;  
			  }  
			};  

		request.setRetryPolicy(new DefaultRetryPolicy(50000,
				DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
				DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
		AppContext.getInstance().mQueue.add(request);
	}
	
	/**
	 * 意见反馈http
	 * @param volleyListenerInterface
	 */
	public static void Service_Phone(VolleyListenerInterface volleyListenerInterface){
		String url = "http://event.gooddr.com/api/user/servicephone";
		StringRequest request = new StringRequest(Method.GET, url,volleyListenerInterface.responseListener(), 
			volleyListenerInterface.errorListener());  

		request.setRetryPolicy(new DefaultRetryPolicy(50000,
				DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
				DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
		AppContext.getInstance().mQueue.add(request);
	}
}
