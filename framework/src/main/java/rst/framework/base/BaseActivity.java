package rst.framework.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.MotionEvent;

import com.tendcloud.tenddata.TCAgent;

import java.io.Serializable;

import rst.framework.FrameworkConstants;
import rst.framework.manager.LocalBroadcast;
import rst.framework.manager.RstInputMethodManager;
import rst.framework.model.viewmodel.BaseViewModel;
import rst.framework.task.MarkAble;
import rst.framework.task.NetworkExcuter;


/**
 * 封装了一些通用的代码,用来简化绑定广播,接收消息的功能
 */
public abstract class BaseActivity extends FragmentActivity implements MarkAble {
    protected MyBroadCastReceiver mLocalBroadCastReceiver;
    protected BaseViewModel mViewData;


    @Override
    public String getInstanceTag() {
        return this.getClass().getSimpleName() + this.hashCode();
    }

    protected class MyBroadCastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            int code = intent.getIntExtra(FrameworkConstants.MESSAGE_CODE, FrameworkConstants.MESSAGE_SUCCESS);
            String content = intent.getStringExtra(FrameworkConstants.MESSAGE_CONTENT);
            Serializable data = intent.getSerializableExtra(FrameworkConstants.MESSAGE_DATA);
            onMessageReceive(action, code, content, data);
        }
    }


    /**
     * boardcast响应方法
     *
     * @param action
     * @param code
     * @param message
     * @param data
     */
    protected void onMessageReceive(String action, int code, String message, Serializable data) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            mViewData = (BaseViewModel) savedInstanceState.getSerializable(FrameworkConstants.PAGE_BASE_EXCHANGEMODEL);
        } else if (getIntent() != null && getIntent().getExtras() != null) {
            mViewData = (BaseViewModel) getIntent().getExtras().getSerializable(FrameworkConstants.PAGE_BASE_EXCHANGEMODEL);
        }
        if (mLocalBroadCastReceiver != null) {
            LocalBroadcast.unregisterLocalReceiver(mLocalBroadCastReceiver);
        }
        mLocalBroadCastReceiver = new MyBroadCastReceiver();
        LocalBroadcast.registerLocalReceiver(mLocalBroadCastReceiver, listReceiveActions());


    }

    @Override
    protected void onStart() {
        super.onStart();
        if (getTalkingDataPagename() != null) {
            TCAgent.onPageStart(this, getTalkingDataPagename());
        }
    }

    /**
     * 列出页面需要接收的广播类型
     *
     * @return
     */
    protected abstract String[] listReceiveActions();


    /**
     * 用于埋点的页面名称,默认为空,不发送,如需统计,在子类中重写此方法
     *
     * @return
     */
    protected String getTalkingDataPagename() {
        return null;
    }

    @Override
    protected void onDestroy() {
        LocalBroadcast.unregisterLocalReceiver(mLocalBroadCastReceiver);
        NetworkExcuter.getInstance().cancelRequest(getInstanceTag());
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (getTalkingDataPagename() != null) {
            TCAgent.onPageEnd(this, getTalkingDataPagename());
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (mViewData != null) {
            outState.putSerializable(FrameworkConstants.PAGE_BASE_EXCHANGEMODEL, mViewData);
        }
        super.onSaveInstanceState(outState);

    }

    /**
     * 由于启动时用户很可能通过广告点击,在MainActivity之前进入其他页面,为了能在该情况下能正常返回到首页,于基类中重写此方法,
     * 若未曾进入过MainActivity,则按返回会进入MainActivity
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (KeyEvent.KEYCODE_BACK == keyCode) {
            if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
//                if (MainActivity.isHomeAlive) {
                finish();
                return true;
//                } else {
//                    startActivity(new Intent(this, MainActivity.class));
//                    finish();
//                    return true;
//                }
            } else {
                try {
                    getSupportFragmentManager().popBackStackImmediate();
                } catch (Exception e) {
                }
                return true;
            }
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (getCurrentFocus() != null && getCurrentFocus().getWindowToken() != null) {
                RstInputMethodManager.hideSoftInput(getCurrentFocus().getWindowToken());
            }
        }
        return super.onTouchEvent(event);
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        switch (resultCode)
//        {
//            case 222:
//                finish();
//                break;
//        }
//    }
}
