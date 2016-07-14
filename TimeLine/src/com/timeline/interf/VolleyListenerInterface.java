package com.timeline.interf;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import android.content.Context;

public abstract class VolleyListenerInterface {
    public Context mContext;
    public static Response.Listener<String> mListener;
    public static Response.ErrorListener mErrorListener;

    public VolleyListenerInterface(Context context) {
        this.mContext = context;
    }

    // 请求成功时的回调函数
    public abstract void onMySuccess(String result);

    // 请求失败时的回调函数
    public abstract void onMyError(VolleyError error);

    // 创建请求的事件监听
    public Response.Listener<String> responseListener() {
        mListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                onMySuccess(s);
            }
        };
        return mListener;
    }

    // 创建请求失败的事件监听
    public Response.ErrorListener errorListener() {
        mErrorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                onMyError(volleyError);
            }
        };
        return mErrorListener;
    }
}
