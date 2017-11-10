package com.pingan.u17.util;

public class U17Log {
	private final  static boolean isSaveLog=false;
	public static final String TAG = "wanjia";
	public static boolean openLog = true;


	//public final static boolean Constants.IS_LOG=true;//打印log开关
	
	public static void d(String tag,String msg){
		isSaveLogToFile("d  "+tag+":"+msg);
		if(openLog){
			android.util.Log.d(tag, msg);
		}
	}
	public static void i(String tag,String msg){
		isSaveLogToFile("i  "+tag+":"+msg);
		if(openLog){
			android.util.Log.i(tag, msg);
		}
	}

	public static void i(Object tag,String msg){
		isSaveLogToFile("i  "+tag+":"+msg);
		if(openLog){
			android.util.Log.i(tag.getClass().getSimpleName(), msg);
		}
	}
	public static void e(String tag,String msg){
		isSaveLogToFile("e  "+tag+":"+msg);
		if(openLog){
			android.util.Log.e(tag, msg);
		}
	}
	public static void e(String tag,String msg,Throwable tr){
		isSaveLogToFile("e  "+tag+":"+msg);
		if(openLog){
			android.util.Log.e(tag, msg,tr);
		}
	}

	public static void v(String tag,String msg){
		isSaveLogToFile("v  "+tag+":"+msg);
		if(openLog){
			android.util.Log.v(tag, msg);
		}
	}
	public static void w(String tag,String msg){
		isSaveLogToFile("v  "+tag+":"+msg);
		if(openLog){
			android.util.Log.w(tag, msg);
		}
	}
	public static void w(String tag,String msg,Throwable tw){
		isSaveLogToFile("v  "+tag+":"+msg);
		if(openLog){
			android.util.Log.w(tag, msg ,tw);
		}
	}


	public static void d(String msg){
		isSaveLogToFile("d  "+TAG+":"+msg);
		if(openLog){
			android.util.Log.d(TAG, msg);
		}
	}
	public static void i(String msg){
		isSaveLogToFile("i  "+TAG+":"+msg);
		if(openLog){
			android.util.Log.i(TAG, msg);
		}
	}
	public static void e(String msg){
		isSaveLogToFile("e  "+TAG+":"+msg);
		if(openLog){
			android.util.Log.e(TAG, msg);
		}
	}
	public static void v(String msg){
		isSaveLogToFile("v  "+TAG+":"+msg);
		if(openLog){
			android.util.Log.v(TAG, msg);
		}
	}
	public static void w(String msg){
		isSaveLogToFile("v  "+TAG+":"+msg);
		if(openLog){
			android.util.Log.w(TAG, msg);
		}
	}
	public static void w(String msg,Throwable tw){
		isSaveLogToFile("v  "+TAG+":"+msg);
		if(openLog){
			android.util.Log.w(TAG, msg ,tw);
		}
	}
	/**
	 * 保存log到文件
	 */
	public static void isSaveLogToFile(String log){
		if(isSaveLog){
			
		}
	}

}
