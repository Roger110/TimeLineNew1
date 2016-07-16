package com.timeline.common;

import java.util.regex.Pattern;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.timeline.app.AppContext;
import com.timeline.app.AppManager;
import com.timeline.main.R;
import com.timeline.ui.AdviceAc;
import com.timeline.ui.EventAddAc;
import com.timeline.ui.GuestSigninAc;
import com.timeline.ui.InfoEditAc;
import com.timeline.ui.InteractionAc;
import com.timeline.ui.LoginInAc;
import com.timeline.ui.Main;
import com.timeline.ui.MeetingDetailAc;
import com.timeline.ui.MyInfoAc;
import com.timeline.ui.MySigninAc;
import com.timeline.ui.Register1Ac;
import com.timeline.ui.Register2Ac;
import com.timeline.ui.SettingAc;


public class UIHelper {
private final static String TAG = "UIHelper";
	
	public final static int LISTVIEW_ACTION_INIT = 0x01;
	public final static int LISTVIEW_ACTION_REFRESH = 0x02;
	public final static int LISTVIEW_ACTION_SCROLL = 0x03;
	public final static int LISTVIEW_ACTION_CHANGE_CATALOG = 0x04;

	public final static int LISTVIEW_DATA_MORE = 0x01;
	public final static int LISTVIEW_DATA_LOADING = 0x02;
	public final static int LISTVIEW_DATA_FULL = 0x03;
	public final static int LISTVIEW_DATA_EMPTY = 0x04;


	public final static int REQUEST_CODE_FOR_RESULT = 0x01;
	public final static int REQUEST_CODE_FOR_REPLY = 0x02;

	/** 表情图片匹配 */
	private static Pattern facePattern = Pattern
			.compile("\\[{1}([0-9]\\d*)\\]{1}");

	/** 全局web样式 */
	// 链接样式文件，代码块高亮的处理
	public final static String linkCss = "<script type=\"text/javascript\" src=\"file:///android_asset/shCore.js\"></script>"
			+ "<script type=\"text/javascript\" src=\"file:///android_asset/brush.js\"></script>"
			+ "<link rel=\"stylesheet\" type=\"text/css\" href=\"file:///android_asset/shThemeDefault.css\">"
			+ "<link rel=\"stylesheet\" type=\"text/css\" href=\"file:///android_asset/shCore.css\">"
			+ "<script type=\"text/javascript\">SyntaxHighlighter.all();</script>";
	public final static String WEB_STYLE = linkCss + "<style>* {font-size:14px;line-height:20px;} p {color:#333;} a {color:#3E62A6;} img {max-width:310px;} "
			+ "img.alignleft {float:left;max-width:120px;margin:0 10px 5px 0;border:1px solid #ccc;background:#fff;padding:2px;} "
			+ "pre {font-size:9pt;line-height:12pt;font-family:Courier New,Arial;border:1px solid #ddd;border-left:5px solid #6CE26C;background:#f6f6f6;padding:5px;overflow: auto;} "
			+ "a.tag {font-size:15px;text-decoration:none;background-color:#bbd6f3;border-bottom:2px solid #3E6D8E;border-right:2px solid #7F9FB6;color:#284a7b;margin:2px 2px 2px 0;padding:2px 4px;white-space:nowrap;}</style>";
	/**
	 * 显示主页面
	 * 
	 * @param activity
	 */
	public static void showMain(Activity activity) {
		Intent intent = new Intent(activity, Main.class);
		activity.startActivity(intent);
		activity.finish();
	}


	/**
	 * 显示h会议详情
	 * 
	 * @param activity
	 */
	public static void showMeetingDetail(Activity activity) {
		Intent intent = new Intent(activity, MeetingDetailAc.class);
		activity.startActivity(intent);
	}
	
	/**
	 * 显示我的登录
	 * 
	 * @param activity
	 */
	public static void showMySign(Activity activity) {
		Intent intent = new Intent(activity, MySigninAc.class);
		activity.startActivity(intent);
	}
	
	/**
	 * 显示我的登录信息
	 * 
	 * @param activity
	 */
	public static void showMyInfo(Activity activity,String type,String name) {
		Intent intent = new Intent(activity, MyInfoAc.class);
		intent.putExtra("type", type);
		intent.putExtra("name", name);
		activity.startActivity(intent);
	}
	
	/**
	 * 显示新添事件
	 * 
	 * @param activity
	 */
	public static void showNewEvent(Activity activity) {
		Intent intent = new Intent(activity, EventAddAc.class);
		activity.startActivity(intent);
	}
	
	/**
	 * 显示设置帮助
	 * 
	 * @param activity
	 */
	public static void showSetting(Activity activity) {
		Intent intent = new Intent(activity, SettingAc.class);
		activity.startActivity(intent);
	}
	
	/**
	 * 显示现场互动界面
	 * 
	 * @param activity
	 */
	public static void showInterac(Activity activity) {
		Intent intent = new Intent(activity, InteractionAc.class);
		activity.startActivity(intent);
	}
	
	/**
	 * 显示登陆界面
	 * 
	 * @param activity
	 */
	public static void showLogin(Activity activity) {
		Intent intent = new Intent(activity, LoginInAc.class);
		activity.startActivity(intent);
	}
	
	/**
	 * 显示注册界面1
	 * 
	 * @param activity
	 */
	public static void showRegister1(Activity activity) {
		Intent intent = new Intent(activity, Register1Ac.class);
		activity.startActivity(intent);
	}
	/**
	 * 显示注册界面2
	 * 
	 * @param activity
	 */
	public static void showRegister2(Activity activity) {
		Intent intent = new Intent(activity, Register2Ac.class);
		activity.startActivity(intent);
	}
	
	/**
	 * 显示意见反馈界面
	 * 
	 * @param activity
	 */
	public static void showAdvice(Activity activity) {
		Intent intent = new Intent(activity, AdviceAc.class);
		activity.startActivity(intent);
	}
	
	/**
	 * 显示现场签到界面
	 * 
	 * @param activity
	 */
	public static void showGuSign(String meetingID,Activity activity) {
		Intent intent = new Intent(activity, GuestSigninAc.class);
		intent.putExtra("meetingid", meetingID);
		activity.startActivity(intent);
	}
	
	/**
	 * 显示信息修改界面
	 * 
	 * @param activity
	 */
	public static void showInfoEdit(Activity activity,String type,String name) {
		Intent intent = new Intent(activity, InfoEditAc.class);
		intent.putExtra("type", type);
		intent.putExtra("name", name);
		activity.startActivity(intent);
	}
	/**
	 * 打开浏览器
	 * 
	 * @param context
	 * @param url
	 */
	public static void openBrowser(Context context, String url) {
		try {
			Uri uri = Uri.parse(url);
			Intent it = new Intent(Intent.ACTION_VIEW, uri);
			context.startActivity(it);
		} catch (Exception e) {
			e.printStackTrace();
			ToastMessage(context, "无法浏览此网页", 500);
		}
	}

	/**
	 * 获取webviewClient对象
	 * 
	 * @return
	 */
	public static WebViewClient getWebViewClient() {
		return new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				//showUrlRedirect(view.getContext(), url);
				return true;
			}
		};
	}

	/**
	 * 获取TextWatcher对象
	 * 
	 * @param context
	 * @param tmlKey
	 * @return
	 */
	public static TextWatcher getTextWatcher(final Activity context,
			final String temlKey) {
		return new TextWatcher() {
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// 保存当前EditText正在编辑的内容
				((AppContext) context.getApplication()).setProperty(temlKey,
						s.toString());
			}

			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			public void afterTextChanged(Editable s) {
			}
		};
	}

	/**
	 * 编辑器显示保存的草稿
	 * 
	 * @param context
	 * @param editer
	 * @param temlKey
	 */
	public static void showTempEditContent(Activity context, EditText editer,
			String temlKey) {
		String tempContent = ((AppContext) context.getApplication())
				.getProperty(temlKey);
		if (!StringUtils.isEmpty(tempContent)) {
			editer.setText(tempContent);
			editer.setSelection(tempContent.length());// 设置光标位置
		}
	}


	/**
	 * 清除文字
	 * 
	 * @param cont
	 * @param editer
	 */
	public static void showClearWordsDialog(final Context cont,
			final EditText editer, final TextView numwords) {
		AlertDialog.Builder builder = new AlertDialog.Builder(cont);
		builder.setTitle(R.string.clearwords);
		builder.setPositiveButton(R.string.sure,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
						// 清除文字
						editer.setText("");
						numwords.setText("160");
					}
				});
		builder.setNegativeButton(R.string.cancle,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});
		builder.show();
	}

	


	/**
	 * 弹出Toast消息
	 * 
	 * @param msg
	 */
	public static void ToastMessage(Context cont, String msg) {
		Toast.makeText(cont, msg, Toast.LENGTH_SHORT).show();
	}

	public static void ToastMessage(Context cont, int msg) {
		Toast.makeText(cont, msg, Toast.LENGTH_SHORT).show();
	}

	public static void ToastMessage(Context cont, String msg, int time) {
		Toast.makeText(cont, msg, time).show();
	}

	/**
	 * 点击返回监听事件
	 * 
	 * @param activity
	 * @return
	 */
	public static View.OnClickListener finish(final Activity activity) {
		return new View.OnClickListener() {
			public void onClick(View v) {
				activity.finish();
			}
		};
	}




	
	/**
	 * 清除app缓存
	 * 
	 * @param activity
	 */
	public static void clearAppCache(Activity activity) {
		final AppContext ac = (AppContext) activity.getApplication();
		final Handler handler = new Handler() {
			public void handleMessage(Message msg) {
				if (msg.what == 1) {
					ToastMessage(ac, "缓存清除成功");
				} else {
					ToastMessage(ac, "缓存清除失败");
				}
			}
		};
		new Thread() {
			public void run() {
				Message msg = new Message();
				try {
				//	ac.clearAppCache();
					msg.what = 1;
				} catch (Exception e) {
					e.printStackTrace();
					msg.what = -1;
				}
				handler.sendMessage(msg);
			}
		}.start();
	}

	/**
	 * 发送App异常崩溃报告
	 * 
	 * @param cont
	 * @param crashReport
	 */
	public static void sendAppCrashReport(final Context cont,
			final String crashReport) {
		AlertDialog.Builder builder = new AlertDialog.Builder(cont);
		builder.setIcon(android.R.drawable.ic_dialog_info);
		builder.setTitle(R.string.app_error);
		builder.setMessage(R.string.app_error_message);
		builder.setPositiveButton(R.string.submit_report,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
						// 发送异常报告
						Intent i = new Intent(Intent.ACTION_SEND);
						// i.setType("text/plain"); //模拟器
						i.setType("message/rfc822"); // 真机
						i.putExtra(Intent.EXTRA_EMAIL,
								new String[] { "gaojun@moons.com.cn" });
						i.putExtra(Intent.EXTRA_SUBJECT,
								"鸣志LED Android客户端 - 错误报告");
						i.putExtra(Intent.EXTRA_TEXT, crashReport);
						cont.startActivity(Intent.createChooser(i, "发送错误报告"));
						// 退出
						AppManager.getAppManager().AppExit(cont);
					}
				});
		builder.setNegativeButton(R.string.sure,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
						// 退出
						AppManager.getAppManager().AppExit(cont);
					}
				});
		builder.show();
	}

	/**
	 * 退出程序
	 * 
	 * @param cont
	 */
	public static void Exit(final Context cont) {
		AlertDialog.Builder builder = new AlertDialog.Builder(cont);
		builder.setIcon(android.R.drawable.ic_dialog_info);
		builder.setTitle(R.string.app_menu_surelogout);
		builder.setPositiveButton(R.string.sure,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
						// 退出
						AppManager.getAppManager().AppExit(cont);
					}
				});
		builder.setNegativeButton(R.string.cancle,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});
		builder.show();
	}



}
