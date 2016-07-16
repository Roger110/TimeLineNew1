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

	/** ����ͼƬƥ�� */
	private static Pattern facePattern = Pattern
			.compile("\\[{1}([0-9]\\d*)\\]{1}");

	/** ȫ��web��ʽ */
	// ������ʽ�ļ������������Ĵ���
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
	 * ��ʾ��ҳ��
	 * 
	 * @param activity
	 */
	public static void showMain(Activity activity) {
		Intent intent = new Intent(activity, Main.class);
		activity.startActivity(intent);
		activity.finish();
	}


	/**
	 * ��ʾh��������
	 * 
	 * @param activity
	 */
	public static void showMeetingDetail(Activity activity) {
		Intent intent = new Intent(activity, MeetingDetailAc.class);
		activity.startActivity(intent);
	}
	
	/**
	 * ��ʾ�ҵĵ�¼
	 * 
	 * @param activity
	 */
	public static void showMySign(Activity activity) {
		Intent intent = new Intent(activity, MySigninAc.class);
		activity.startActivity(intent);
	}
	
	/**
	 * ��ʾ�ҵĵ�¼��Ϣ
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
	 * ��ʾ�����¼�
	 * 
	 * @param activity
	 */
	public static void showNewEvent(Activity activity) {
		Intent intent = new Intent(activity, EventAddAc.class);
		activity.startActivity(intent);
	}
	
	/**
	 * ��ʾ���ð���
	 * 
	 * @param activity
	 */
	public static void showSetting(Activity activity) {
		Intent intent = new Intent(activity, SettingAc.class);
		activity.startActivity(intent);
	}
	
	/**
	 * ��ʾ�ֳ���������
	 * 
	 * @param activity
	 */
	public static void showInterac(Activity activity) {
		Intent intent = new Intent(activity, InteractionAc.class);
		activity.startActivity(intent);
	}
	
	/**
	 * ��ʾ��½����
	 * 
	 * @param activity
	 */
	public static void showLogin(Activity activity) {
		Intent intent = new Intent(activity, LoginInAc.class);
		activity.startActivity(intent);
	}
	
	/**
	 * ��ʾע�����1
	 * 
	 * @param activity
	 */
	public static void showRegister1(Activity activity) {
		Intent intent = new Intent(activity, Register1Ac.class);
		activity.startActivity(intent);
	}
	/**
	 * ��ʾע�����2
	 * 
	 * @param activity
	 */
	public static void showRegister2(Activity activity) {
		Intent intent = new Intent(activity, Register2Ac.class);
		activity.startActivity(intent);
	}
	
	/**
	 * ��ʾ�����������
	 * 
	 * @param activity
	 */
	public static void showAdvice(Activity activity) {
		Intent intent = new Intent(activity, AdviceAc.class);
		activity.startActivity(intent);
	}
	
	/**
	 * ��ʾ�ֳ�ǩ������
	 * 
	 * @param activity
	 */
	public static void showGuSign(String meetingID,Activity activity) {
		Intent intent = new Intent(activity, GuestSigninAc.class);
		intent.putExtra("meetingid", meetingID);
		activity.startActivity(intent);
	}
	
	/**
	 * ��ʾ��Ϣ�޸Ľ���
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
	 * �������
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
			ToastMessage(context, "�޷��������ҳ", 500);
		}
	}

	/**
	 * ��ȡwebviewClient����
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
	 * ��ȡTextWatcher����
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
				// ���浱ǰEditText���ڱ༭������
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
	 * �༭����ʾ����Ĳݸ�
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
			editer.setSelection(tempContent.length());// ���ù��λ��
		}
	}


	/**
	 * �������
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
						// �������
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
	 * ����Toast��Ϣ
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
	 * ������ؼ����¼�
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
	 * ���app����
	 * 
	 * @param activity
	 */
	public static void clearAppCache(Activity activity) {
		final AppContext ac = (AppContext) activity.getApplication();
		final Handler handler = new Handler() {
			public void handleMessage(Message msg) {
				if (msg.what == 1) {
					ToastMessage(ac, "��������ɹ�");
				} else {
					ToastMessage(ac, "�������ʧ��");
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
	 * ����App�쳣��������
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
						// �����쳣����
						Intent i = new Intent(Intent.ACTION_SEND);
						// i.setType("text/plain"); //ģ����
						i.setType("message/rfc822"); // ���
						i.putExtra(Intent.EXTRA_EMAIL,
								new String[] { "gaojun@moons.com.cn" });
						i.putExtra(Intent.EXTRA_SUBJECT,
								"��־LED Android�ͻ��� - ���󱨸�");
						i.putExtra(Intent.EXTRA_TEXT, crashReport);
						cont.startActivity(Intent.createChooser(i, "���ʹ��󱨸�"));
						// �˳�
						AppManager.getAppManager().AppExit(cont);
					}
				});
		builder.setNegativeButton(R.string.sure,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
						// �˳�
						AppManager.getAppManager().AppExit(cont);
					}
				});
		builder.show();
	}

	/**
	 * �˳�����
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
						// �˳�
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
