package com.pingan.u17.util;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Handler;
import android.telephony.TelephonyManager;
import android.text.method.ReplacementTransformationMethod;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;

import com.pingan.u17.base.U17Application;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;


@TargetApi(Build.VERSION_CODES.HONEYCOMB)
@SuppressWarnings("deprecation")
public class ToolUtils {

	static int IMAGE_HALFWIDTH = 50;

	/**
	 * scrollView延迟0.2秒移动
	 * 
	 * @param scrollView
	 * @param x
	 * @param y
	 */
	public static void scrollTo(final ScrollView scrollView, final int x,
								final int y) {
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				scrollView.scrollTo(x, y);
			}
		}, 200);
	}

	/** 根据屏幕宽度与密度计算GridView显示的列数， 最少为三列，并获取Item宽度 */
	public static int getImageItemWidth(Activity activity) {
		int screenWidth = activity.getResources().getDisplayMetrics().widthPixels;
		int densityDpi = activity.getResources().getDisplayMetrics().densityDpi;
		int cols = screenWidth / densityDpi;
		cols = cols < 3 ? 3 : cols;
		int columnSpace = (int) (2 * activity.getResources().getDisplayMetrics().density);
		return (screenWidth - columnSpace * (cols - 1)) / cols;
	}


	/**
	 * 判断是否是wifi网络
	 * 
	 * @param mContext
	 * @return
	 */
	public static boolean isWifi(Context mContext) {
		ConnectivityManager connectivityManager = (ConnectivityManager) mContext
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
		if (activeNetInfo != null
				&& activeNetInfo.getType() == ConnectivityManager.TYPE_WIFI) {
			return true;
		}
		return false;
	}

	/**
	 * 关闭键盘
	 *
	 * @param editText
	 */
	public static void closeKey(EditText editText) {
		InputMethodManager imm = (InputMethodManager) U17Application.getInstance()
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
	}

	public static void closeKey(Context context) {
		try {
			InputMethodManager imm = (InputMethodManager) U17Application.getInstance()
					.getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.hideSoftInputFromWindow(((Activity) context).getCurrentFocus()
					.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static int getScreenWidth(Activity activity) {
		int screenWidth = activity.getWindowManager().getDefaultDisplay()
				.getWidth(); // 屏幕宽（像素，如：480px）
		return screenWidth;
	}

	public static int getScreenHeight(Activity activity) {
		int screenHeight = activity.getWindowManager().getDefaultDisplay()
				.getHeight(); // 屏幕高（像素，如：800p）
		return screenHeight;
	}

	public static int[] getScreenSize(){
		int[] size=new int[2];
		DisplayMetrics metrics=new DisplayMetrics();
		WindowManager wm=(WindowManager)U17Application.getInstance().getSystemService(Context.WINDOW_SERVICE);
		wm.getDefaultDisplay().getMetrics(metrics);
		size[0]=metrics.widthPixels;
		size[1]=metrics.heightPixels;
		return size;
	}
	/**
	 *
	 * @param listView
	 */

	public static void fixListViewHeight(ListView listView) {
		// 如果没有设置数据适配器，则ListView没有子项，返回
		ListAdapter listAdapter = listView.getAdapter();
		int totalHeight = 0;
		if (listAdapter == null) {
			return;
		}
		for (int i = 0, len = listAdapter.getCount(); i < len; i++) {
			View listViewItem = listAdapter.getView(i, null, listView);
			if (listViewItem != null) {
				// 计算子项View 的宽高
				listViewItem.measure(0, 0);
				// 计算所有子项的高度和
				totalHeight += listViewItem.getMeasuredHeight();
			}
		}

		ViewGroup.LayoutParams params = listView.getLayoutParams();
		// listView.getDividerHeight()获取子项间分隔符的高度
		// params.height设置ListView完全显示需要的高度
		params.height = totalHeight
				+ (listView.getDividerHeight() * (listAdapter.getCount() - 1));
		listView.setLayoutParams(params);
	}

	/**
	 * 将px值转换为dip或dp值，保证尺寸大小不变
	 * 
	 * @param pxValue
	 *            （DisplayMetrics类中属性density）
	 * @return
	 */
	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	/**
	 * 将dip或dp值转换为px值，保证尺寸大小不变
	 * 
	 * @param dipValue
	 *            （DisplayMetrics类中属性density）
	 * @return
	 */
	public static int dip2px(Context context, float dipValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dipValue * scale + 0.5f);
	}

	/**
	 * 将px值转换为sp值，保证文字大小不变
	 * 
	 * @param pxValue
	 *            （DisplayMetrics类中属性scaledDensity）
	 * @return
	 */
	public static int px2sp(Context context, float pxValue) {
		final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (pxValue / fontScale + 0.5f);
	}

	/**
	 * 将sp值转换为px值，保证文字大小不变
	 * 
	 * @param spValue
	 *            （DisplayMetrics类中属性scaledDensity）
	 * @return
	 */
	public static int sp2px(Context context, float spValue) {
		final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (spValue * fontScale + 0.5f);
	}

	public static int getScreenDpi(Context context) {
		DisplayMetrics metric = new DisplayMetrics();
		((Activity) context).getWindowManager().getDefaultDisplay()
				.getMetrics(metric);
		int densityDpi = metric.densityDpi; // 屏幕密度DPI
		return densityDpi;
	}

	/**
	 * 转换服务器传回的Date型参数
	 * 
	 * @param format
	 *            需要的格式 DateFormat.MEDIUM DateFormat.FULL ...
	 * @param date
	 *            接口返回的Date
	 */
	public static String getDate(int format, Date date) {
		if (null != date) {
			return DateFormat.getDateInstance(format).format(date);
		}
		return null;
	}

	public static String initNullString(String str) {
		if (str == null)
			return "";
		else
			return str;
	}

	/**
	 * 
	 * 转换数字称以万为单位的文本，小数点后面保留一位
	 * 
	 */
	public static String getFormatNum(double num) {
		return String.format("%.1f", num);
	}

	/**
	 * 
	 * 日期转换
	 * 
	 */
	@SuppressLint("SimpleDateFormat")
	public static String getDateYM(long millisecond) {
		Date date = new Date(millisecond);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月");
		return sdf.format(date);
	}

	/**
	 * 
	 * 转换数字称以万为单位的文本，小数点后面保留一位
	 * 
	 */
	public static String getDateYMD(long millisecond) {
		Date date = new Date(millisecond);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}

	/**
	 * 
	 * 转换字符称以万为单位的文本，小数点后面保留两位
	 * 
	 */
	public static String getFormatNumFromString(String c) {
		if (!c.equals("")) {
			Double d = (double) (Float.parseFloat(c) / 10000.00);// 换算成万
			c = String.format("%.2f", d) + "万";
			return c;
		}
		return "";

	}

	public static String getDateYMDHM(long millisecond) {
		Date date = new Date(millisecond);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd kk:mm");
		return sdf.format(date);
	}

	/**
	 * 判断字符串是否为数字
	 * 
	 * @param str
	 *            待判断的字符串
	 * @return
	 */
	public static boolean isNumber(String str) {
		boolean isNumber = true;
		char[] ch = str.toCharArray();
		for (int i = 0; i < ch.length; i++) {
			isNumber = Character.isDigit(ch[i]);
			if (!isNumber) {
				break;
			}
		}
		return isNumber;
	}

	/**
	 * 按照屏幕比例设置控件高度
	 * 
	 * @param view
	 * @param scale
	 */
	public static void SetViewHeight(Activity activity, View view, double scale) {
		LayoutParams params = view.getLayoutParams();
		params.height = (int) (getScreenHeight(activity) * scale);
		view.setLayoutParams(params);
	}

	/**
	 * 输入框 点击 hint自动消失
	 */
	public static OnFocusChangeListener onFocusAutoClearHintListener = new OnFocusChangeListener() {
		@Override
		public void onFocusChange(View v, boolean hasFocus) {
			EditText textView = (EditText) v;
			String hint;
			if (hasFocus) {
				hint = textView.getHint().toString();
				textView.setTag(hint);
				textView.setHint("");
			} else {
				hint = textView.getTag().toString();
				textView.setHint(hint);
			}
		}
	};

	/**
	 * 判断该设备是否有smartBar
	 * 
	 * @return
	 */
	public static boolean hasSmartBar() {
		try {
			// 可用反射调用Build.hasSmartBar()
			Method method = Class.forName("android.os.Build").getMethod(
					"hasSmartBar");
			return ((Boolean) method.invoke(null)).booleanValue();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 判断设备是否有navigationbar
	 * 
	 */
	public static boolean checkDeviceHasNavigationBar(Context context) {
		Resources rs = context.getResources();
		int id = rs
				.getIdentifier("config_showNavigationBar", "bool", "android");
		if (id > 0) {
			return rs.getBoolean(id);
		}
		return false;

	}

	/**
	 * 获取navigationbar 高度
	 */
	public static int getNavigationBarHeight(Context context) {
		Resources resources = context.getResources();
		int resourceId = resources.getIdentifier("navigation_bar_height",
				"dimen", "android");
		// 获取NavigationBar的高度
		int height = 0;
		if (resourceId > 0) {
			height = resources.getDimensionPixelSize(resourceId);
		}
		return height;
	}

	/**
	 * 得到当前渠道号
	 * 
	 * @return
	 */
	public static String getMarketId() {
		String appType = null;
		try {
			ApplicationInfo appInfo = U17Application.getInstance().getPackageManager()
					.getApplicationInfo(U17Application.getInstance().getPackageName(),
							PackageManager.GET_META_DATA);
			appType = String.valueOf(appInfo.metaData.get("WJ_CHANNEL"));
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return appType;
	}

	/**
	 * 获取app版本号
	 * 
	 * @return
	 */
	public static String getVersionName() {
		try {
			String pkName = U17Application.getInstance().getPackageName();
			String versionName = U17Application.getInstance().getPackageManager().getPackageInfo(
					pkName, 0).versionName;
			return versionName;
		} catch (Exception e) {
		}
		return "";
	}

	/**
	 * 获取app升级次数
	 * 
	 * @return
	 */
	public static int getVersionCode() {
		int versionCode = -1;
		try {
			String pkName = U17Application.getInstance().getPackageName();
			versionCode = U17Application.getInstance().getPackageManager().getPackageInfo(pkName, 0).versionCode;
			return versionCode;
		} catch (Exception e) {
		}
		return versionCode;
	}

	/**
	 * 地址参数进行UTF-8编码
	 * 
	 * @param string
	 * @return
	 */

	public static String getUTF8XMLString(String string) {
		// A StringBuffer Object
		StringBuffer sb = new StringBuffer();
		sb.append(string);
		String xmString = "";
		String xmlUTF8 = "";
		try {
			xmString = new String(sb.toString().getBytes("UTF-8"));
			xmlUTF8 = URLEncoder.encode(xmString, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// return to String Formed
		return xmlUTF8;
	}

	/**
	 * 违章参数进行两次编码
	 * 
	 * @param string
	 * @return
	 */
	public static String getStringTwoEncode(String string) {
		return getUTF8XMLString(getUTF8XMLString(string));
	}

	/**
	 * 输入小写自动变成大写
	 * 
	 * @author EX-GAOFEI653
	 * 
	 */
	public class AllCapTransformationMethod extends
			ReplacementTransformationMethod {

		@Override
		protected char[] getOriginal() {
			char[] aa = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
					'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
					'w', 'x', 'y', 'z' };
			return aa;
		}

		@Override
		protected char[] getReplacement() {
			char[] cc = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
					'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V',
					'W', 'X', 'Y', 'Z' };
			return cc;
		}

	}

	/**
	 * 隐藏输入法
	 * 
	 * @param v
	 */
	public static void hideKeyboard(View v) {
		InputMethodManager imm = (InputMethodManager) v.getContext()
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		if (imm.isActive()) {
			imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
		}
	}



	public static int getCurrentWeekIndex(String strDate) {
		int weekIndex = -1;
		int year = Integer.parseInt(strDate.substring(0, 4));
		int month = Integer.parseInt(strDate.substring(5, 7));
		int day = Integer.parseInt(strDate.substring(8, 10));

		Calendar c = Calendar.getInstance();

		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, month - 1);
		c.set(Calendar.DAY_OF_MONTH, day);

		weekIndex = c.get(Calendar.DAY_OF_WEEK);
		return weekIndex;
	}

	@SuppressLint("SimpleDateFormat")
	public static String getDateDiff(String date1, String date2){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
		Timestamp ts1 = Timestamp.valueOf(date1);
		Timestamp ts2 = Timestamp.valueOf(date2);
		Long difftime = (ts2.getTime() - ts1.getTime())/1000;
		if(difftime <= 30*60){
			return "刚刚";
		}else if(30*60 < difftime && difftime <= 6*60*60){
			return "6小时前";
		}else if(6*60*60 < difftime && difftime <= 12*60*60){
			return "12小时前";
		}else if(difftime > 12*60*60){
			return (sdf.format(ts1)).toString();
		}
		return "";
	}

	// 用于手机校验，判断是否是以1开头的11位数字
	public final static Pattern PHONE_PATTERN = Pattern.compile("[1][\\d]{10}");
		
	// 用于密码校验
	public final static Pattern PWD_PATTERN      = Pattern
			.compile("^[a-z0-9A-Z]{6,16}$");
	// 用于用户名校验
	public final static Pattern USERNAME_PATTERN = Pattern
			.compile("^[a-z0-9A-Z_]{6,16}$");
	
	// 判断是否为合法的手机号
	public static Boolean validatePhone(final String phone) {
		if (PHONE_PATTERN.matcher(phone).matches())
			return true;
		return false;
	}
	
	// 判断是否为合法密码
	public static Boolean validatePsw(final String psw) {
		if (PWD_PATTERN.matcher(psw).matches())
			return true;
		return false;
	}
	
	// 判断是否为合法密码--6-16位数字字母组合，必须包含一个数字和字母
	public static Boolean validatePsw2(final String psw) {
		if (Pattern.compile("^(?=.*[a-zA-Z])(?=.*[0-9])[a-zA-Z0-9]{6,16}$").matcher(psw).matches())
			return true;
		return false;
	}

	// 姓名匹配  字母和汉字
	public static Boolean validateName(final String name) {
		if (Pattern.compile("^([A-Za-z]|[\u4E00-\u9FA5]){1,10}$").matcher(name).matches())
			return true;
		return false;
	}
	
	//资格证书  24-27位数字
	public static Boolean validateCertificate(final String cerfificate) {
		if (Pattern.compile("^[0-9]{24,27}$").matcher(cerfificate).matches())
			return true;
		return false;
	}

	//执业证书  15位数字
	public static Boolean validatePractice(final String practice) {
		if (Pattern.compile("^[0-9]{15}$").matcher(practice).matches())
			return true;
		return false;
	}	
	// 判断是否为合法用户名--6-16位数字字母下划线
	public static Boolean validateUserName(final String userName) {
		if (USERNAME_PATTERN.matcher(userName).matches())
			return true;
		return false;
	}
		
	public static void deleteFile(File file) {
		if (file.isFile()) {
			file.delete();
			return;
		}

		if (file.isDirectory()) {
			File[] childFiles = file.listFiles();
			if (childFiles == null || childFiles.length == 0) {
				file.delete();
				return;
			}

			for (int i = 0; i < childFiles.length; i++) {
				deleteFile(childFiles[i]);
			}
			file.delete();
		}
	}
	


    // 根据生日计算年龄
	public static String getAgeByBirthday(String birthday) {
		String age = "";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date birthDate = format.parse(birthday);

			Calendar cal = Calendar.getInstance();
			if (cal.before(birthDate)) {
				throw new IllegalArgumentException("生日在当前日期之后");
			}

			int yearNow = cal.get(Calendar.YEAR);
			int monthNow = cal.get(Calendar.MONTH) + 1;
			int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);

			cal.setTime(birthDate);
			int yearBirth = cal.get(Calendar.YEAR);
			int monthBirth = cal.get(Calendar.MONTH) + 1;
			int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

			int intAge = yearNow - yearBirth;

			if (monthNow <= monthBirth) {
				if (monthNow == monthBirth) {
					if (dayOfMonthNow < dayOfMonthBirth) {
						intAge--;
					}
				} else {
					intAge--;
				}
			}
			age = String.valueOf(intAge);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return age;
	}

	public static String getUserActionUploadDate() {
		String date = "" + System.currentTimeMillis() + Math.floor(Math.random() * 1000000);
		return date.substring(0, date.length() - 2);
	}

	public static String getSerialNo() {
		return android.os.Build.SERIAL;
	}

	public static String getIMEI() {
		String deviceId = null;
		TelephonyManager manager = (TelephonyManager) U17Application.getInstance().getSystemService(Context.TELEPHONY_SERVICE);
		if (manager != null) {
			deviceId = manager.getDeviceId();
		}
		return deviceId;
	}

	public static String getPhoneModel() {
		return android.os.Build.MODEL;
	}

	public static String getSystemVersion() {
		return android.os.Build.VERSION.RELEASE;
	}

	public static String getBrand() { return Build.BRAND; }

	public static String getDeviceId() {
		return getIMEI() + "|" + getSerialNo();
	}

	// 由毫秒数获取指定格式的日期
	public static String getDateFromMilliSecond(long milliSeconds, SimpleDateFormat format) {
		Date d = new Date(milliSeconds);
		return format.format(d).toString();
	}

	/***
	 * 验证身份证是否有效，只校验位数和生日
	 * 如果返回是空，则表示身份证有效
	 *
	 * @param idcardNumber	身份证
	 * @param birthday	生日
	 *
	 * */

	private final static int    NEW_CARD_NUMBER_LENGTH = 18;
	private final static int    OLD_CARD_NUMBER_LENGTH = 15;
	private final static String LENGTH_ERROR           ="身份证长度必须为15或者18位！";
	private final static String DATE_ERROR             ="出生日期和身份证信息不一致!";

	public static String validateIdCardNum(String idcardNumber, String birthday) {
		String Ai = idcardNumber.trim();
		String Ai1,Ai2;
		if (Ai.length() == 15 | Ai.length() == 18) {
			//15位和18位数
			if (Ai.length() == OLD_CARD_NUMBER_LENGTH) {
				Ai1 = contertToNewCardNumber1(Ai);
				Ai2 = contertToNewCardNumber2(Ai);
				if(!Ai1.substring(6,14).equals(birthday)&& !Ai2.substring(6,14).equals(birthday)){
					return DATE_ERROR;
				}
			}else {
				if(!Ai.substring(6,14).equals(birthday)){
					return DATE_ERROR;
				}
			}
		} else {
			return LENGTH_ERROR;
		}
		return "";
	}


	private static String contertToNewCardNumber1(String oldCardNumber) {
		StringBuilder buf = new StringBuilder(NEW_CARD_NUMBER_LENGTH);
		buf.append(oldCardNumber.substring(0, 6));
		buf.append("19");
		buf.append(oldCardNumber.substring(6));
		buf.append("x");
		return buf.toString();
	}

	private static String contertToNewCardNumber2(String oldCardNumber) {
		StringBuilder buf = new StringBuilder(NEW_CARD_NUMBER_LENGTH);
		buf.append(oldCardNumber.substring(0, 6));
		buf.append("20");
		buf.append(oldCardNumber.substring(6));
		buf.append("x");
		return buf.toString();
	}




}
