package rst.framework.manager;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Base64;

import com.google.gson.Gson;

import java.util.List;

import rst.framework.BaseApplication;
import rst.framework.utils.Md5Util;

/**
 * 缓存记录数据库工具
 */
public class CacheManager {
    private final int DefaultCacheValidTime = 30 * 24 * 3600;//默认1个月以前的缓存就不要了
    private DataBaseOpenHelper myOpenHelper;
    private String result;
    private List<String> voide_list;
    private Gson gson;

    private CacheManager(Context context) {
        myOpenHelper = new DataBaseOpenHelper(context);
        gson = new Gson();
    }


    private static CacheManager sInstance;

    public static CacheManager getInstance() {
        if (sInstance == null) {
            sInstance = new CacheManager(BaseApplication.getAppContext());
        }
        return sInstance;
    }

    /**
     * 插入数据库
     */
    public void putPageData(String url, Object object) {
        SQLiteDatabase db = myOpenHelper.getWritableDatabase();
        String dumpString = Base64.encodeToString(gson.toJson(object).getBytes(), Base64.DEFAULT);
//        url = url.replaceAll("(timestamp=)([0-9]{10})", "");
        url = Md5Util.makeMd5Sum(url.getBytes());
        if (db.isOpen()) {
            long time = System.currentTimeMillis();
            db.execSQL("replace into page_map (key,time,data) values (?,?,?)", new Object[]{url, time, dumpString});
            db.close();
        }
    }

    /**
     * 获取缓存
     *
     * @param url
     * @param className 返回的对象类型
     * @param <T>
     * @return
     */
    public <T> T getDataFromCache(String url, Class<T> className) {
        return getDataFromCache(url, className, DefaultCacheValidTime);
    }

    /**
     * 指定缓存时间,获取缓存
     *
     * @param url
     * @param className 返回的对象类型
     * @param validTime 以秒为单位,大于此期限则不要
     * @param <T>
     * @return
     */
    public <T> T getDataFromCache(String url, Class<T> className, long validTime) {
        url = Md5Util.makeMd5Sum(url.getBytes());
        SQLiteDatabase db = null;
        try {
            db = myOpenHelper.getReadableDatabase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (db != null && db.isOpen()) {
            Cursor cursor = db.rawQuery("select * from page_map where key = ?", new String[]{url});
            if (cursor.getCount() == 0) {
                cursor.close();
                return null;
            } else {
                cursor.moveToFirst();
                long time = cursor.getLong(cursor.getColumnIndex("time"));
                if (System.currentTimeMillis() - time < validTime * 1000) {
                    String data = cursor.getString(cursor.getColumnIndex("data"));
                    cursor.close();
                    T response = new Gson().fromJson(new String(Base64.decode(data.getBytes(), Base64.DEFAULT)), className);
                    return response;
                } else {
                    return null;
                }
            }
        } else {
            return null;
        }
    }
}
