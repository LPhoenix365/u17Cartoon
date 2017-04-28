package rst.framework.manager;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import rst.framework.BaseApplication;

/**
 *
 */
public class ToastManager {

//    public static synchronized Toast show(Context context, int iconID, String paramString) {
//        Toast toast = new Toast(context);
//        toast.setView(getToastView(context, iconID, paramString));
//        toast.setDuration(Toast.LENGTH_SHORT);
//        toast.show();
//        return toast;
//    }

    public static void showToast(Context context, String content) {
        if (!TextUtils.isEmpty(content)) {
            Toast.makeText(context, content, Toast.LENGTH_SHORT).show();
        }
    }
    public static void showToast( String content) {
        if (!TextUtils.isEmpty(content)) {
            Toast.makeText(BaseApplication.getAppContext(), content, Toast.LENGTH_SHORT).show();
        }
    }


    /**
     * 仅当显示时间比 Toast.LENGTH_SHORT（可视时间大概是3.5s，包括进出的动画的渐隐时间）的时长更短的时候才使用
     * 时间必须至少为500ms，建议在1000ms 与 2500ms 之间
     *
     * @param durationInMs 毫秒数
     */
//    public static void show(Context context, boolean isError, String paramString, int durationInMs) {
//
//        show(context, isError ? R.drawable.toast_ok_icon : R.drawable.toast_error_icon,
//                paramString, durationInMs);
//    }


//    private static View getToastView(Context context, int iconID, String paramString) {
//        View localView = LayoutInflater.from(context).inflate(R.layout.toast, null);
//        ((TextView) localView.findViewById(R.id.message)).setText(paramString);
//        return localView;
//    }
}
