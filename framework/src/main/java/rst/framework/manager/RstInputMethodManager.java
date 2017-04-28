package rst.framework.manager;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.ResultReceiver;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import rst.framework.BaseApplication;


public class RstInputMethodManager {
    /**
     * 隐藏输入框
     *
     * @param view
     */
    public static void hideSoftInput(EditText view) {
        hideSoftInput(view.getWindowToken());
    }

    /**
     * 隐藏输入框
     */
    public static void hideSoftInput(IBinder windowToken) {
        final InputMethodManager imm = (InputMethodManager) BaseApplication.getAppContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        final IMMResult result = new IMMResult();
        if (null != windowToken) {
            imm.hideSoftInputFromWindow(windowToken, 0, result);
        }
    }

    /**
     * 显示 输入框
     *
     * @param view
     */
    public static void showSoftInput(EditText view) {
        final InputMethodManager imm = (InputMethodManager) BaseApplication.getAppContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        final IMMResult result = new IMMResult();
        imm.showSoftInput(view, 0, result);
        view.postDelayed(new Runnable() {
            @Override
            public void run() {
                int res = result.getResult();
                if (res != InputMethodManager.RESULT_SHOWN && res != InputMethodManager.RESULT_UNCHANGED_HIDDEN) {
                    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
                }
            }
        }, 500);
        view.requestFocus();
    }

    public static class IMMResult extends ResultReceiver {
        public int result = -1;

        public IMMResult() {
            super(null);
        }

        @Override
        public void onReceiveResult(int r, Bundle data) {
            result = r;
        }

        public int getResult() {
            return result;
        }
    }

}
