package rst.framework.utils;

import android.content.Context;
import android.text.format.DateFormat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

/**
 * Created by iceman on 16/2/23.
 * 日期操作
 */
public class DateUtil {
    public static final String DATETIME_PATTERN_6_1 = "yyyy-MM-dd HH:mm:ss";
    private static final String[] weekDayStrings = {"日", "一", "二", "三", "四", "五", "六"};
    public static final String DATETIME_PATTERN_3_3 = "yyyy年MM月dd日";
    /**
     * 获取任意一段时间内的简单日历,主要包含日期和星期对应关系
     *
     * @param startDay 起始日期
     * @param endDay   结束日期
     * @return 二维数组, <br>星期/日期
     */
    public static String[][] getWeekDays(Calendar startDay, Calendar endDay) {

        ArrayList<String[]> weekDays = new ArrayList<>();

        Calendar tempStart = (Calendar) startDay.clone();
        while (endDay.compareTo(tempStart) >= 0) {
            String[] arr = new String[2];
            arr[0] = weekDayStrings[tempStart.get(Calendar.DAY_OF_WEEK) - 1];
            arr[1] = String.valueOf(tempStart.get(Calendar.DAY_OF_MONTH));
            weekDays.add(arr);
            tempStart.add(Calendar.DAY_OF_YEAR, 1);
        }
        return (weekDays.toArray(new String[0][0]));

    }

    /**
     * 根据年合月，算出当月的天数
     *
     * @param year
     * @param month
     * @return
     */
    public static int getMonthDays(int year, int month) {
        if (month > 12) {
            month = 1;
            year += 1;
        } else if (month < 1) {
            month = 12;
            year -= 1;
        }
        int[] arr = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int days = 0;

        if (isLeapYear(year)) {
            arr[1] = 29; // 闰年2月29天
        }

        try {
            days = arr[month - 1];
        } catch (Exception e) {
            e.getStackTrace();
        }

        return days;
    }

    /**
     * 是否为闰年
     *
     * @param year
     * @return
     */
    public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
    }


    /**
     * 根据年份和月份获取日期数组，1、2、3...
     *
     * @param year
     * @param month
     * @return
     */
    public static List<String> getMonthDaysArray(int year, int month) {
        List<String> dayList = new ArrayList<>();
        int days = DateUtil.getMonthDays(year, month);
        for (int i = 1; i <= days; i++) {
            dayList.add(i + "");
        }
        return dayList;
    }


    /**
     * 获取当前系统时间的年份
     *
     * @return
     */
    public static int getYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    /**
     * 获取当前系统时间的月份
     *
     * @return
     */
    public static int getMonth() {
        return Calendar.getInstance().get(Calendar.MONTH) + 1;
    }

    /**
     * 获取当前系统时间的月份的第几天
     *
     * @return
     */
    public static int getCurrentMonthDay() {
        return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取当前系统时间的小时数
     *
     * @return
     */
    public static int getHour() {
        return Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 获取当前系统时间的分钟数
     *
     * @return
     */
    public static int getMinute() {
        return Calendar.getInstance().get(Calendar.MINUTE);
    }

    /**
     * 获取当前系统时间的秒数
     *
     * @return
     */
    public static int getSecond() {
        return Calendar.getInstance().get(Calendar.SECOND);
    }

    /**
     * 根据系统默认时区，获取当前时间与time的天数差
     *
     * @param time
     * @return 等于0表示今天，大于0表示今天之前
     */
    public static long getDaySpan(long time) {
        // 系统默认时区，ms
        int tiemzone = TimeZone.getDefault().getRawOffset();
        // １天＝24*60*60*1000ms
        return (System.currentTimeMillis() + tiemzone) / 86400000
                - (time + tiemzone) / 86400000;
    }

    /**
     * 是否是今天
     * @param time
     * @return
     */
    public static boolean isToday(long time) {
        return getDaySpan(time) == 0;
    }

    /**
     * 是否是昨天
     * @param time
     * @return
     */
    public static boolean isYestoday(long time) {
        return getDaySpan(time) == 1;
    }

    /**
     * @return 返回当前时间，yyyy-MM-dd HH-mm-ss
     */
    public static String getDate() {
        return getDate("yyyy-MM-dd HH-mm-ss");
    }

    public static String getDate(String format) {
        SimpleDateFormat sDateFormat = new SimpleDateFormat(format);
        String date = sDateFormat.format(new Date());
        return date;
    }
    public static String getDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATETIME_PATTERN_3_3);
        return sdf.format(date);
    }
    /**
     * 将毫秒值转换成yyyy-MM-dd hh:mm:ss 格式
     * @param ms
     * @return
     */
    public static String parseStrTime(long ms) {
        SimpleDateFormat formatter = new SimpleDateFormat(DATETIME_PATTERN_6_1);
        Date curDate = new Date(ms);//获取当前时间
        return formatter.format(curDate);
    }

    public static String getWeekOfDate(Date dt, Calendar calendar) {
        String[] weekDays = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        calendar.setTime(dt);
        int w = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

    /**
     * 判断是白天还是晚上
     *
     * @return 白天返回 true ; 晚上 返回false
     */
    public static boolean isDayOrNight(Context context) {
        //获得内容提供者
//        ContentResolver mResolver = context.getContentResolver();
        //获得系统时间制
//        String timeFormat = android.provider.Settings.System.getString(mResolver, android.provider.Settings.System.TIME_12_24);
//        if (timeFormat.equals("24")) {
        boolean hourFormat = DateFormat.is24HourFormat(context);
        //判断时间制
        if (hourFormat) {
            //24小时制
            SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");//设置日期格式
            String time = df.format(new Date());
            int hour = Integer.parseInt(time.substring(0, 2));
            if (hour >= 6 && hour < 18) { //白天
                return true;
            } else {
                return false;
            }
        } else {
            //12小时制
            //获得日历
            Calendar mCalendar = Calendar.getInstance();
            if (mCalendar.get(Calendar.AM_PM) == 0) {
                //白天
                return true;
            } else {
                //晚
                return false;
            }
        }
    }

    public static String replace(int values){
        String number = "";
        if (values < 10) {
            number = "0" + values;
        } else {
            number = String.valueOf(values);
        }
        return number;
    }

    public static String getHzMonth(String month) {
        Map<String, String> map = new HashMap<>();
        map.put("01","1月");
        map.put("02","2月");
        map.put("03","3月");
        map.put("04","4月");
        map.put("05","5月");
        map.put("06","6月");
        map.put("07","7月");
        map.put("08","8月");
        map.put("09","9月");
        map.put("10","10月");
        map.put("11","11月");
        map.put("12","12月");
        Set<Map.Entry<String, String>> seting = map.entrySet();
        for(Map.Entry<String, String> entry : seting) {
            if(month.equals(entry.getKey())){
                return entry.getValue();
            }
        }
        return "";
    }

}
