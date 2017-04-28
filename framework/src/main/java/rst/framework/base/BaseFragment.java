package rst.framework.base;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.tendcloud.tenddata.TCAgent;

import java.io.Serializable;

import rst.framework.BaseApplication;
import rst.framework.FrameworkConstants;
import rst.framework.model.viewmodel.BaseViewModel;
import rst.framework.task.MarkAble;
import rst.framework.manager.LocalBroadcast;
import rst.framework.task.NetworkExcuter;

/**
 * fragment基类，封装了自动获取viewmodel以及广播监听
 */
public abstract class BaseFragment extends Fragment implements MarkAble {
    protected MyBroadCastReceiver mLocalBroadCastReceiver;

    protected BaseViewModel mViewData;

    protected Activity mActivity;
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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            mViewData = (BaseViewModel) savedInstanceState.getSerializable(FrameworkConstants.PAGE_BASE_EXCHANGEMODEL);
        } else if (getArguments() != null) {
            mViewData = (BaseViewModel) getArguments().getSerializable(FrameworkConstants.PAGE_BASE_EXCHANGEMODEL);
        }
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = getActivity();
    }

    /**
     * 用于埋点的页面名称,默认为空,不发送,如需统计,在子类中重写此方法
     *
     * @return
     */
    protected String getTalkingDataPagename() {
        return null;
    }

    /**
     * 列出页面需要接收的广播类型
     *
     * @return
     */
    protected abstract String[] listReceiveActions();


    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (mViewData != null) {
            outState.putSerializable(FrameworkConstants.PAGE_BASE_EXCHANGEMODEL, mViewData);
        }
        super.onSaveInstanceState(outState);

    }


    @Override
    public void onStart() {
        super.onStart();
        if (getTalkingDataPagename() != null) {
            TCAgent.onPageStart(BaseApplication.getAppContext(), getTalkingDataPagename());
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (getTalkingDataPagename() != null) {
            TCAgent.onPageEnd(BaseApplication.getAppContext(), getTalkingDataPagename());
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
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (mLocalBroadCastReceiver != null) {
            LocalBroadcast.unregisterLocalReceiver(mLocalBroadCastReceiver);
        }
        mLocalBroadCastReceiver = new MyBroadCastReceiver();
        LocalBroadcast.registerLocalReceiver(mLocalBroadCastReceiver, listReceiveActions());

    }

    @Override
    public void onDestroy() {
        LocalBroadcast.unregisterLocalReceiver(mLocalBroadCastReceiver);
        NetworkExcuter.getInstance().cancelRequest(getInstanceTag());
        if (getTalkingDataPagename() != null) {
            TCAgent.onPageEnd(BaseApplication.getAppContext(), getTalkingDataPagename());
        }
        super.onDestroy();
    }
}
