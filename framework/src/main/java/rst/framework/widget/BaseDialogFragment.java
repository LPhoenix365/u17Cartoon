package rst.framework.widget;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;

import rst.framework.R;
import rst.framework.manager.FragmentExchangeManager;
import rst.framework.model.entity.DialogExchangeModel;


public class BaseDialogFragment extends DialogFragment {
    public final static String TAG = "BaseDialogFragment";
    protected DialogClickInterface dialogClickInterface;
    protected String mDialogTag;// 标记
    protected CharSequence mContentTxt;// 内容
    public boolean bIsBackable;// 是否back取消
    public int gravity = Gravity.CENTER;
    protected OnClickListener mSpaceClickListener = new OnClickListener() {

        @Override
        public void onClick(View v) {
            if (bIsBackable) {
                Fragment tarFragment = getTargetFragment();
                Activity activity = getActivity();
                dismissSelf();
            }
        }
    };

    public static BaseDialogFragment getInstance(Bundle bundle) {
        BaseDialogFragment baseDialogFragment = new BaseDialogFragment();
        baseDialogFragment.setArguments(bundle);
        return baseDialogFragment;
    }

    public BaseDialogFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Bundle bundle = getArguments();
            DialogExchangeModel dialogExchangeModel = ((DialogExchangeModel.DialogExchangeModelBuilder) bundle.getSerializable(TAG)).creat();
            if (dialogExchangeModel != null) {
                mDialogTag = dialogExchangeModel.getTag();
                bIsBackable = dialogExchangeModel.isBackable();
                mContentTxt = dialogExchangeModel.getDialogContext();
                gravity = dialogExchangeModel.getGravity();
                setCancelable(bIsBackable);
            }
        }
    }

    @Override
    public void dismiss() {
        super.dismissAllowingStateLoss();
    }

    @Override
    public int show(FragmentTransaction transaction, String tag) {
        return show(transaction, tag, true);
    }

    public int show(FragmentTransaction transaction, String tag, boolean allowStateLoss) {
        transaction.add(this, tag);
        int mBackStackId = allowStateLoss ? transaction.commitAllowingStateLoss() : transaction.commit();
        return mBackStackId;
    }

    public void dismissSelf() {
        FragmentExchangeManager.removeFragment(getFragmentManager(), this);
    }

}
