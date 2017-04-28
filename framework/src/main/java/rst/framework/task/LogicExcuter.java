package rst.framework.task;

import android.os.Handler;
import android.os.Looper;

import rst.framework.network.OkhttpUtil;

/**
 * Created by iceman on 16/11/21.
 * 处理耗时操作
 */

public class LogicExcuter {
    protected LogicExcuter() {
    }

    public void excute() {

    }


    public interface LogicExcuteInterface {
        void dologic();
    }
}
