package rst.framework.widget;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import rst.framework.R;
import rst.framework.model.entity.DialogExchangeModel;


public class ExcuteInfoDialogFragment extends BaseDialogFragment {

    protected String mPositiveBtnTxt;// 确认操作
    protected String mNegativeBtnTxt;// 取消操作

    private TextView mDlgContent, mBtnLeft, mRightBtn;
    private OnClickListener mExcuitePositiveListener, mExcuiteNegativeListener;

    public static ExcuteInfoDialogFragment getInstance(Bundle bundle) {
        ExcuteInfoDialogFragment baseDialogFragment = new ExcuteInfoDialogFragment();
        baseDialogFragment.setArguments(bundle);
        return baseDialogFragment;
    }

    public ExcuteInfoDialogFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.AppExcuteDialogTheme);
        if (getArguments() != null) {
            Bundle bundle = getArguments();
            DialogExchangeModel dialogExchangeModel = ((DialogExchangeModel.DialogExchangeModelBuilder) bundle.getSerializable(TAG)).creat();
            if (dialogExchangeModel != null) {
                mPositiveBtnTxt = dialogExchangeModel.getPostiveText();
                mNegativeBtnTxt = dialogExchangeModel.getNegativeText();
                dialogClickInterface = dialogExchangeModel.getClickInterface();
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_excute_layout, container, false);
        view.setOnClickListener(mSpaceClickListener);
        mDlgContent = (TextView) view.findViewById(R.id.content_text);
        if (!TextUtils.isEmpty(mContentTxt)) {
            mDlgContent.setText(mContentTxt);
            if (gravity != -1) {
                mDlgContent.setGravity(gravity);
            }
        }
        mBtnLeft = (TextView) view.findViewById(R.id.lef_btn);
        mRightBtn = (TextView) view.findViewById(R.id.right_btn);

        mExcuitePositiveListener = new OnClickListener() {

            @Override
            public void onClick(View v) {
                Fragment tarFragment = getTargetFragment();
                Activity activity = getActivity();
                dismissSelf();
                if (dialogClickInterface != null) {
                    dialogClickInterface.onPositiveBtnClick();
                }
//                if (tarFragment != null && tarFragment instanceof ExcuteDialogFragmentCallBack) {
//                    ((ExcuteDialogFragmentCallBack) tarFragment).onRightBtnClick(mDialogTag);
//                } else if (activity != null && activity instanceof ExcuteDialogFragmentCallBack) {
//                    ((ExcuteDialogFragmentCallBack) activity).onRightBtnClick(mDialogTag);
//                }
            }
        };

        mExcuiteNegativeListener = new OnClickListener() {

            @Override
            public void onClick(View v) {
                Fragment tarFragment = getTargetFragment();
                Activity activity = getActivity();
                dismissSelf();
                if (dialogClickInterface != null) {
                    dialogClickInterface.onNegativeBtnClick();
                }
//                if (tarFragment != null && tarFragment instanceof ExcuteDialogFragmentCallBack) {
//                    ((ExcuteDialogFragmentCallBack) tarFragment).onLeftBtnClick(mDialogTag);
//                } else if (activity != null && activity instanceof ExcuteDialogFragmentCallBack) {
//                    ((ExcuteDialogFragmentCallBack) activity).onLeftBtnClick(mDialogTag);
//                }
            }
        };

        if (!TextUtils.isEmpty(mPositiveBtnTxt)) {
            mRightBtn.setText(mPositiveBtnTxt);
        } else {
            mRightBtn.setText(R.string.ok);
        }
        mRightBtn.setOnClickListener(mExcuitePositiveListener);
        mRightBtn.setBackgroundResource(R.drawable.btn_dialog_selector);

        if (!TextUtils.isEmpty(mNegativeBtnTxt)) {
            mBtnLeft.setText(mNegativeBtnTxt);
        } else {
            mBtnLeft.setText(R.string.cancel);
        }
        mBtnLeft.setOnClickListener(mExcuiteNegativeListener);
        mBtnLeft.setBackgroundResource(R.drawable.btn_dialog_selector);
        return view;
    }
}
