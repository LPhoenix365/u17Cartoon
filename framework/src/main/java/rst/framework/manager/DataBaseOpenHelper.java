package rst.framework.manager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 数据库工具类
 */
public class DataBaseOpenHelper extends SQLiteOpenHelper {
    /**
     * 创建数据库
     *
     * @param context
     */
    public DataBaseOpenHelper(Context context) {
        super(context, "pagecache.db", null, 2);
    }

    /**
     * 创建表
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String user_sql = "CREATE TABLE " + "page_map" + "(" + "_id INTEGER  PRIMARY KEY ," + "key TEXT UNIQUE,time INTEGER,data TEXT)";
        db.execSQL(user_sql);
    }

    /**
     * 检查数据库是否更新 操作
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
        String user_sql = "DROP TABLE " + "page_map";
        db.execSQL(user_sql);

        String creatTable = "CREATE TABLE " + "page_map" + "(" + "_id INTEGER  PRIMARY KEY ," + "key TEXT UNIQUE,time INTEGER,data TEXT)";
        db.execSQL(creatTable);


    }
}
