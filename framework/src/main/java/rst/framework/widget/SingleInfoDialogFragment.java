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
import android.widget.TextView;

import rst.framework.R;
import rst.framework.model.entity.DialogExchangeModel;


public class SingleInfoDialogFragment extends BaseDialogFragment {

    private TextView mDlgContent, mDlgButton;

    protected String mSingleBtnTxt;// 单个button文字

    public static SingleInfoDialogFragment getInstance(Bundle bundle) {
        SingleInfoDialogFragment baseDialogFragment = new SingleInfoDialogFragment();
        baseDialogFragment.setArguments(bundle);
        return baseDialogFragment;
    }

    public SingleInfoDialogFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.AppExcuteDialogTheme);
        if (getArguments() != null) {
            Bundle bundle = getArguments();
            DialogExchangeModel dialogExchangeModel = ((DialogExchangeModel.DialogExchangeModelBuilder) bundle.getSerializable(TAG)).creat();
            if (dialogExchangeModel != null) {
                mSingleBtnTxt = dialogExchangeModel.getSingleText();
                dialogClickInterface = dialogExchangeModel.getClickInterface();
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.dialog_error_layout, container, false);
        contentView.setOnClickListener(mSpaceClickListener);

        mDlgContent = (TextView) contentView.findViewById(R.id.content_text);
        if (!TextUtils.isEmpty(mContentTxt)) {
            mDlgContent.setText(mContentTxt);
            mDlgContent.setGravity(gravity);
        }

        mDlgButton = (TextView) contentView.findViewById(R.id.single_btn);
        mDlgButton.setClickable(true);
        if (!TextUtils.isEmpty(mSingleBtnTxt)) {
            mDlgButton.setText(mSingleBtnTxt);
        }

        mDlgButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Fragment tarFragment = getTargetFragment();
                Activity activity = getActivity();
                dismissSelf();
                if (dialogClickInterface != null) {
                    dialogClickInterface.onSingleBtnClick();
                }
//                if (tarFragment != null && tarFragment instanceof SingleDialogFragmentCallBack) {
//                    ((SingleDialogFragmentCallBack) tarFragment).onSingleBtnClick(mDialogTag);
//                } else if (activity != null && activity instanceof SingleDialogFragmentCallBack) {
//                    ((SingleDialogFragmentCallBack) activity).onSingleBtnClick(mDialogTag);
//                }
            }
        });
        return contentView;
    }
}
