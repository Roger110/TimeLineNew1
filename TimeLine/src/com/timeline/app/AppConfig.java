package com.timeline.app;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

import com.timeline.common.StringUtils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.preference.PreferenceManager;

/**
 * Ӧ�ó��������ࣺ���ڱ����û������Ϣ������
 * 
 * @author gaojun
 * @version 1.0
 * @created 2014-10-16
 */
@SuppressLint("NewApi")
public class AppConfig {

	private final static String APP_CONFIG = "config1111";


	public final static String CONF_APP_UNIQUEID = "APP_UNIQUEID";
	public final static String CONF_COOKIE = "cookie";
	public final static String CONF_ACCESSTOKEN = "accessToken";
	public final static String CONF_ACCESSSECRET = "accessSecret";
	public final static String CONF_EXPIRESIN = "expiresIn";
	public final static String CONF_CHECKUP = "perf_checkup";

	public final static String WEBSITE_PATH = "website";
	@SuppressLint("NewApi")
	public final static String DEFAULT_WEBSITE_PATH = "msaccrm.moons.com.cn";
			
	private Context mContext;
	private static AppConfig appConfig;

	public static AppConfig getAppConfig(Context context) {
		if (appConfig == null) {
			appConfig = new AppConfig();
			appConfig.mContext = context;
		}
		return appConfig;
	}

	/**
	 * ��ȡPreference����
	 */
	public static SharedPreferences getSharedPreferences(Context context) {
		return PreferenceManager.getDefaultSharedPreferences(context);
	}

	/**
	 * �Ƿ������ʾ����ͼƬ
	 */
	/*
	public static boolean isLoadImage(Context context) {
		return getSharedPreferences(context).getBoolean(CONF_LOAD_IMAGE, true);
	}
	 */
	public String getCookie() {
		return get(CONF_COOKIE);
	}

	public void setAccessToken(String accessToken) {
		set(CONF_ACCESSTOKEN, accessToken);
	}

	public String getAccessToken() {
		return get(CONF_ACCESSTOKEN);
	}

	public void setAccessSecret(String accessSecret) {
		set(CONF_ACCESSSECRET, accessSecret);
	}

	public String getAccessSecret() {
		return get(CONF_ACCESSSECRET);
	}

	public void setExpiresIn(long expiresIn) {
		set(CONF_EXPIRESIN, String.valueOf(expiresIn));
	}

	public long getExpiresIn() {
		return StringUtils.toLong(get(CONF_EXPIRESIN));
	}


	public String get(String key) {
		Properties props = get();
		return (props != null) ? props.getProperty(key) : null;
	}

	public Properties get() {
		FileInputStream fis = null;
		Properties props = new Properties();
		try {
			// ��ȡfilesĿ¼�µ�config
			// fis = activity.openFileInput(APP_CONFIG);

			// ��ȡapp_configĿ¼�µ�config
			File dirConf = mContext.getDir(APP_CONFIG, Context.MODE_PRIVATE);
			fis = new FileInputStream(dirConf.getPath() + File.separator
					+ APP_CONFIG);

			props.load(fis);
		} catch (Exception e) {
		} finally {
			try {
				fis.close();
			} catch (Exception e) {
			}
		}
		return props;
	}

	private void setProps(Properties p) {
		FileOutputStream fos = null;
		try {
			// ��config����filesĿ¼��
			// fos = activity.openFileOutput(APP_CONFIG, Context.MODE_PRIVATE);

			// ��config����(�Զ���)app_config��Ŀ¼��
			File dirConf = mContext.getDir(APP_CONFIG, Context.MODE_PRIVATE);
			File conf = new File(dirConf, APP_CONFIG);
			fos = new FileOutputStream(conf);

			p.store(fos, null);
			fos.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fos.close();
			} catch (Exception e) {
			}
		}
	}

	public void set(Properties ps) {
		Properties props = get();
		props.putAll(ps);
		setProps(props);
	}

	public void set(String key, String value) {
		Properties props = get();
		props.setProperty(key, value);
		setProps(props);
	}

	public void remove(String... key) {
		Properties props = get();
		for (String k : key)
			props.remove(k);
		setProps(props);
	}
}
