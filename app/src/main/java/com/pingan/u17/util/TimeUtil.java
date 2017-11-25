package com.pingan.u17.util;

import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class TimeUtil {

	public static SimpleDateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss", Locale.getDefault());
	public static SimpleDateFormat DAY_HOUR = new SimpleDateFormat("HH:mm:ss",
			Locale.getDefault());
	public static SimpleDateFormat DAYHOUR = new SimpleDateFormat("HHmmss",
			Locale.getDefault());
	public static SimpleDateFormat YEAR_MONTH = new SimpleDateFormat(
			"yyyyMMdd", Locale.getDefault());
	public static SimpleDateFormat YEAR_MONTH2 = new SimpleDateFormat(
			"yyyy-MM-dd", Locale.getDefault());
	public static SimpleDateFormat YEAR_MONTH3 = new SimpleDateFormat(
			"yyyy年MM月dd日", Locale.getDefault());
	public static SimpleDateFormat YEAR_MONTH5 = new SimpleDateFormat(
			"yyyy/MM/dd", Locale.getDefault());
    public static SimpleDateFormat YEAR_MONTH4 = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm", Locale.getDefault());
	public static SimpleDateFormat MONTH_DAY = new SimpleDateFormat("MM/dd", Locale.getDefault());
	public static SimpleDateFormat YEAR = new SimpleDateFormat("yyyy",
			Locale.getDefault());
	public static SimpleDateFormat MONTH = new SimpleDateFormat("MM",
			Locale.getDefault());
	public static SimpleDateFormat DAY = new SimpleDateFormat("dd",
			Locale.getDefault());
	public static SimpleDateFormat HOUR_MINUTE = new SimpleDateFormat("HH:mm",
			Locale.getDefault());
    public static SimpleDateFormat HOUR_MINUTE_SECOND = new SimpleDateFormat("HH:mm:ss",
            Locale.getDefault());
	public static SimpleDateFormat YEAR_MONTH6 = new SimpleDateFormat(
			"yyyy-MM", Locale.getDefault());
	public static SimpleDateFormat YEAR_MONTH7 = new SimpleDateFormat(
			"yyyy年MM月", Locale.getDefault());
	public static SimpleDateFormat YEAR_MONTH8 = new SimpleDateFormat(
			"yyyyMM", Locale.getDefault());
	public static SimpleDateFormat YEAR_MONTH9 = new SimpleDateFormat(
			"yyyy.MM.dd", Locale.getDefault());

	public static String getPrintTime(long time) {
		return DEFAULT_DATE_FORMAT.format(new Date(time));
	}
	public static String getPrintTimeFormat4(long time) {
		return YEAR_MONTH4.format(new Date(time));
	}

	public static String getTime(long time) {
		return DAY_HOUR.format(new Date(time));
	}

	public static String getTime2(long time) {
		return DAYHOUR.format(new Date(time));
	}

	public static String getDate(long time) {
		return YEAR_MONTH2.format(new Date(time));
	}

	public static String getDay(long time) {
		return DAY.format(new Date(time));
	}

	public static String getMonth(long time) {
		return MONTH.format(new Date(time));
	}

	public static String getCurrentMonthFirstDate(long time) {
		return YEAR_MONTH2.format(new Date(time)).substring(0,8)+"01";
	}

	public static String getYear(long time) {
		return YEAR.format(new Date(time));
	}

	public static String todayWeek(){
		String str[]={"星期日","星期一","星期二","星期三","星期四","星期五","星期六"};//字符串数组
		Calendar rightNow=Calendar.getInstance();
		int day=rightNow.get(Calendar.DAY_OF_WEEK);//获取时间
		return str[day - 1];
	}

	public static String getDayOfWeek(String date) {
		if (TextUtils.isEmpty(date)) {
			return "";
		}
		String str[]={"周日","周一","周二","周三","周四","周五","周六"};
		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(YEAR_MONTH2.parse(date));
			int today = cal.get(Calendar.DAY_OF_WEEK);
			return str[today - 1];
		} catch (Exception e) {
			return "";
		}
	}

    /**
     * 将时间从一种格式转换为另一种格式
     * @param time 时间
     * @param fromFormat 当前格式
     * @param toFormat 转换后的格式
     * @return 转换后的时间
     */
    public static String getChangedDate(String time, SimpleDateFormat fromFormat, SimpleDateFormat toFormat) {
        if(TextUtils.isEmpty(time)) {
            return "";
        }
        try{
            Date date = fromFormat.parse(time);
            return toFormat.format(date);
        }catch (ParseException e){
            e.printStackTrace();
            return "";
        }
    }

	public static String getYMDTime(String time){
        return getChangedDate(time, DEFAULT_DATE_FORMAT , YEAR_MONTH2);
	}

    public static String YMDTime(String time){
        return getChangedDate(time, YEAR_MONTH3 , YEAR_MONTH2);
    }

	public static String YMDChinaTime(String time){
		return getChangedDate(time, YEAR_MONTH2 , YEAR_MONTH3);
	}

    public static String MDTime(String time){
        return getChangedDate(time, YEAR_MONTH2 , MONTH_DAY);
    }

	public static long getLongTime(String time) {
		try {
			Date date = YEAR_MONTH2.parse(time);
			return date.getTime();
		} catch (ParseException e) {
			e.printStackTrace();
			return System.currentTimeMillis();
		}
	}

	public static long getLongTime3(String time){
		try {
			Date date = YEAR_MONTH3.parse(time);
			return date.getTime();
		} catch (ParseException e) {
			e.printStackTrace();
			return System.currentTimeMillis();
		}
	}

	public static long getLongTimeFromHS(String time) {
		try {
			Date date = HOUR_MINUTE.parse(time);
			return date.getTime();
		} catch (ParseException e) {
			e.printStackTrace();
			return System.currentTimeMillis();
		}
	}

	public static Date getMDDate(String time) {
		try{
			Date date = YEAR_MONTH2.parse(time);
			return date;
		}catch (ParseException e){
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * 获取当前天的年月日
	 * @return
	 */
	public static String getYMDNow() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = sdf.format(date);
		return dateStr;
	}

	/**
	 * 获取当前日期一个月前的年月日
	 * @return
	 */
	public static String getYMDNowMonthBefore() {
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1);
		return YEAR_MONTH2.format(calendar.getTime());
	}

	/**
	 * 获取当前时间前29天的日期
	 * @return 年月日
	 */
	public static String getYMDNowDaysBefore() {
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) - 29);
		return YEAR_MONTH2.format(calendar.getTime());
	}
	/*
	 * String t1 = 现在实际日期.replace('-','/'); String t2 = 出生日期.replace('-','/');
	 * 
	 * Date dt1= new Date(t1); Date dt2= new Date(t2); long i= (dt1.getTime() -
	 * dt2.getTime())/(1000*60*60*24); //i就是总天数了，之后你除个365 就是岁数， 余数就是天数
	 * 要算月的话就把余数再除以12 我的这个日期的格式是2008-11-22 实岁就是现在的年减去出生的年呀，虚岁是实岁再加1
	 * 这个天数就是能比较精确了吧
	 */

	/**
	 * 根据用户生日计算年龄
	 */
	public static String getAgeByBirthday(String time) {
		if (TextUtils.isEmpty(time)) {
			return "";
		}
		Date birthday = null;
		try {
			birthday = YEAR_MONTH2.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar cal = Calendar.getInstance();

		if (cal.before(birthday)) {
			throw new IllegalArgumentException(
					"The birthday is after Now. It's unbelievable!");
		}

		int yearNow = cal.get(Calendar.YEAR);
		int monthNow = cal.get(Calendar.MONTH) + 1;
		int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);

		cal.setTime(birthday);
		int yearBirth = cal.get(Calendar.YEAR);
		int monthBirth = cal.get(Calendar.MONTH) + 1;
		int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

		int age = yearNow - yearBirth;

		if (monthNow <= monthBirth) {
			if (monthNow == monthBirth) {
				// monthNow==monthBirth
				if (dayOfMonthNow < dayOfMonthBirth) {
					age--;
				}
			} else {
				// monthNow>monthBirth
				age--;
			}
		}
		if(age<0){
			age=0;
		}
		return age + "岁";
	}

	// 时间转换为数字0-47
	public static String parseTimeToNum(int hourIndex, int minuteIndex) {
		if (minuteIndex == 0) {
			return String.valueOf(hourIndex * 2);
		} else {
			return String.valueOf(hourIndex * 2 + 1);
		}
	}

	// 数字代码转换时间0-47
	public static String parseNumToTime(String num) {
		if (TextUtils.isEmpty(num))
			return num;
		int originTime2 = Integer.valueOf(num);
		int zhengdianH = originTime2 / 2;
		long minuteM = originTime2 % 2;
		if (minuteM != 0) {
			return String.format("%02d", zhengdianH) + ":30";
		} else {
			return String.format("%02d", zhengdianH) + ":00";
		}
	}

	public static String toHexString(long time){
		return Long.toHexString(time);
	}

	public static String parseStringToDefinedFormat(String format,long time){
		SimpleDateFormat userDefineFormat = new SimpleDateFormat(format, Locale.getDefault());
		return userDefineFormat.format(new Date(time));
	}


	/**
	 * 处理年月日时分秒日期，如果日期为当天返回时分，如果当天之前返回yyyy/mm/dd格式日期
	 * @param date
	 * @return
	 */
	public static String getYMDorHMDate(String date) {
		String time = "";
		Date targetDate;
		try {
			targetDate = YEAR_MONTH2.parse(getChangedDate(date,DEFAULT_DATE_FORMAT,YEAR_MONTH2));
			Date now = YEAR_MONTH2.parse(YEAR_MONTH2.format(new Date()));
			if (targetDate.compareTo(now)<0) {
				time = YEAR_MONTH5.format(targetDate);
			} else {
				time = getChangedDate(date,DEFAULT_DATE_FORMAT, HOUR_MINUTE);
//						HOUR_MINUTE.format(targetDate);
			}
		} catch (ParseException e) {
			time = date;
			e.printStackTrace();
		}
		return time;
	}

	// 判断字符串是否为某种特定日期格式
	public static boolean isValideDateFormat(String dateString, SimpleDateFormat format) {
		if (TextUtils.isEmpty(dateString)) return false;
		boolean dateflag = true;
		try {
			Date date = format.parse(dateString);
		} catch (ParseException e) {
			dateflag = false;
		}
		return dateflag;
	}

	// 比较两个日期的先后，日期格式类似XXXX-XX-XX，如果date1先于date2，返回true; 如果date1晚于或等于date2，返回false
	public static boolean compareDateSequence(String date1, String date2) {
		String year1 = date1.substring(0, 4);
		String year2 = date2.substring(0, 4);
		if (Integer.valueOf(year1) < Integer.valueOf(year2)) {
			return true;
		} else if (Integer.valueOf(year1) > Integer.valueOf(year2)){
			return false;
		} else {
			String month1 = date1.substring(5, 7);
			String month2 = date2.substring(5, 7);
			if (Integer.valueOf(month1) < Integer.valueOf(month2)) {
				return true;
			} else if (Integer.valueOf(month1) > Integer.valueOf(month2)) {
				return false;
			} else {
				String day1 = date1.substring(8);
				String day2 = date2.substring(8);
				if (Integer.valueOf(day1) < Integer.valueOf(day2)) {
					return true;
				} else if (Integer.valueOf(day1) >= Integer.valueOf(day2)) {
					return false;
				}
			}
		}
		return false;
	}

	// 判断某个日期是否在某段时间范围内，注意，取闭区间，如果在，返回true
	public static boolean isDateInDateRange(String date, String startDate, String endDate) {
		if (TextUtils.isEmpty(date) || TextUtils.isEmpty(startDate) || TextUtils.isEmpty(endDate)) {
			return false;
		}
		return (compareDate(dateStringFormat(startDate), dateStringFormat(date))
				&& compareDate(dateStringFormat(date), dateStringFormat(endDate)));
	}

	// String类型日期如1999-1-1格式化为"yyyy-MM-dd"形式
	public static String dateStringFormat(String strToFormat) {
		if (10 == strToFormat.length()) {
			return strToFormat;
		} else {
			String[] items = strToFormat.split("-");   //  取出年月日
			String year = items[0];
			String month = items[1];
			String day = items[2];
			if (1 == month.length() && 10 > Integer.valueOf(month)) {
				month = "0" + month;
			}
			if (1 == day.length() && 10 > Integer.valueOf(day)) {
				day = "0" + day;
			}
			return year + "-" + month + "-" + day;
		}
	}

	// 比较两个日期的先后，日期格式类似XXXX-XX-XX，如果date1先于或等于date2，返回true; 如果date1晚于date2，返回false
	public static boolean compareDate(String date1, String date2) {
		String year1 = date1.substring(0, 4);
		String year2 = date2.substring(0, 4);
		if (Integer.valueOf(year1) < Integer.valueOf(year2)) {
			return true;
		} else if (Integer.valueOf(year1) > Integer.valueOf(year2)){
			return false;
		} else {
			String month1 = date1.substring(5, 7);
			String month2 = date2.substring(5, 7);
			if (Integer.valueOf(month1) < Integer.valueOf(month2)) {
				return true;
			} else if (Integer.valueOf(month1) > Integer.valueOf(month2)) {
				return false;
			} else {
				String day1 = date1.substring(8);
				String day2 = date2.substring(8);
				if (Integer.valueOf(day1) <= Integer.valueOf(day2)) {
					return true;
				} else if (Integer.valueOf(day1) > Integer.valueOf(day2)) {
					return false;
				}
			}
		}
		return false;
	}

	/**
	 * 获取日期之后多少天之后的日期
	 * @param date 当前日期
	 * @param days 之后天数
	 * @return
	 */
	public static Date getDateAfter(Date date, int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + days);
		return  calendar.getTime();
	}

	/**
	 * 将date转换为string
	 * @param date
	 * @param format
	 * @return
	 */
	public static String getFormatDate(Date date,SimpleDateFormat format) {
		return format.format(date);
	}

	public static String timeStampToDate(long timeStamp) {
		Date date = new Date(timeStamp);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = simpleDateFormat.format(date);
		return dateStr;
	}

	public static String timeStampToDate(long timeStamp, SimpleDateFormat format) {
		Date date = new Date(timeStamp);
		String dateStr = format.format(date);
		return dateStr;
	}

	public static int getYearByTimeStamp(long timeStamp){
		String date = timeStampToDate(timeStamp);
		String year = date.substring(0, 4);
		return Integer.parseInt(year);
	}

	public static int getMonthByTimeStamp(long timeStamp){
		String date = timeStampToDate(timeStamp);
		String month = date.substring(5, 7);
		return Integer.parseInt(month);
	}

	public static int getDayByTimeStamp(long timeStamp){
		String date = timeStampToDate(timeStamp);
		String day = date.substring(8, 10);
		return Integer.parseInt(day);
	}


	/**
	 * 根据用户生日计算年龄
	 */
	public static int getAgeByBirthdayInt(String time) {
		if (null == time) {
			return 0;
		}
		Date birthday = null;
		try {
			birthday = YEAR_MONTH2.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar cal = Calendar.getInstance();

		if (cal.before(birthday)) {
			throw new IllegalArgumentException(
					"The birthday is after Now. It's unbelievable!");
		}

		int yearNow = cal.get(Calendar.YEAR);
		int monthNow = cal.get(Calendar.MONTH) + 1;
		int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);

		cal.setTime(birthday);
		int yearBirth = cal.get(Calendar.YEAR);
		int monthBirth = cal.get(Calendar.MONTH) + 1;
		int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

		int age = yearNow - yearBirth;

		if (monthNow <= monthBirth) {
			if (monthNow == monthBirth) {
				// monthNow==monthBirth
				if (dayOfMonthNow < dayOfMonthBirth) {
					age--;
				}
			} else {
				// monthNow>monthBirth
				age--;
			}
		}
		if(age<0){
			age=0;
		}
		return age;
	}


    /**
     * 获取当前时间指定的某一天
     *
     * @return 年月日
     */
    public static String getYMDNowDaysBeforeDay(int beforeDays) {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) - beforeDays);
        return YEAR_MONTH2.format(calendar.getTime());
    }

    final static long DAY_MILL = 1000l * 60 * 60 * 24;
    final static long WEEK_MILL = DAY_MILL * 7;

    public static String getCahtDetailDateFromMill(long mill) {
        long nowMill = System.currentTimeMillis();
        String nowTime = parseStringToDefinedFormat("yyyy-MM-dd", nowMill);
        String pastTime = parseStringToDefinedFormat("yyyy-MM-dd", mill);
        if (nowTime.equals(pastTime)) { //当天
            return parseStringToDefinedFormat("HH:mm", mill);
        } else if (isYesterday(mill)) {
            return "昨天 " + parseStringToDefinedFormat("HH:mm", mill);
        } else if ((nowMill - mill) < WEEK_MILL) {
            return parseStringToDefinedFormat("EEEE HH:mm", mill);
        }
        return parseStringToDefinedFormat("yyyy年MM月dd日 HH:mm", mill);
    }

	public static String getChatConDateFromMill(long mill) {
		long nowMill = System.currentTimeMillis();
		String nowTime = parseStringToDefinedFormat("yyyy-MM-dd", nowMill);
		String pastTime = parseStringToDefinedFormat("yyyy-MM-dd", mill);
		if (nowTime.equals(pastTime)) { //当天
			return parseStringToDefinedFormat("HH:mm", mill);
		}
		if(parseStringToDefinedFormat("yyyy-MM", nowMill).equals(parseStringToDefinedFormat("yyyy-MM", mill))){ //当月
			return parseStringToDefinedFormat("dd/MM", mill);
		}
		return parseStringToDefinedFormat("dd/MM/yyyy", mill);
	}

    public static boolean isYesterday(long pastMill) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String nowDate = sdf.format(calendar.getTime());
        return parseStringToDefinedFormat("yyyy-MM-dd", pastMill).equals(nowDate);
    }

	/**
	 * 根据用户生日计算年龄(xx岁xx月)，忽略日
	 */
	public static int[] getYearsAndMonthByBirthdayInt(String time) {
		int yearsAndMonth[] = new int[2];
		if (null == time) {
			return yearsAndMonth;
		}
		Date birthday = null;
		try {
			birthday = YEAR_MONTH2.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar cal = Calendar.getInstance();

		if (cal.before(birthday)) {
			throw new IllegalArgumentException(
					"The birthday is after Now. It's unbelievable!");
		}

		int yearNow = cal.get(Calendar.YEAR);
		int monthNow = cal.get(Calendar.MONTH) + 1;

		cal.setTime(birthday);
		int yearBirth = cal.get(Calendar.YEAR);
		int monthBirth = cal.get(Calendar.MONTH) + 1;

		int age = yearNow - yearBirth;
		int month = monthNow - monthBirth;

		if (monthNow < monthBirth) {
			age--;
			month += 12;
		}

		if (age < 0) {
			age = 0;
		}
		yearsAndMonth[0] = age;
		yearsAndMonth[1] = month;

		return yearsAndMonth;
	}

	/**
	 * 根据用户年龄(xx岁xx月)计算生日，忽略日
	 */
	public static String getBirthdayByYearsAndMonth(int year, int month) {
		String birthDay = "";
		Calendar cal = Calendar.getInstance();
		int yearNow = cal.get(Calendar.YEAR);
		int monthNow = cal.get(Calendar.MONTH) + 1;
		int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);

		int yearBirth = yearNow - year;
		int monthBirth = monthNow - month;

		if (monthBirth <= 0) {
			yearBirth--;
			monthBirth += 12;
		}

		cal.set(yearBirth, monthBirth-1, dayOfMonthNow);
		birthDay = YEAR_MONTH2.format(cal.getTime());

		return birthDay;
	}
}
