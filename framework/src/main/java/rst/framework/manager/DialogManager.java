package rst.framework.manager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import rst.framework.widget.BaseDialogFragment;
import rst.framework.model.entity.DialogExchangeModel;
import rst.framework.widget.DialogClickInterface;
import rst.framework.widget.DialogType;
import rst.framework.widget.ProcessDialogFragment;
import rst.framework.widget.ExcuteInfoDialogFragment;
import rst.framework.widget.SingleInfoDialogFragment;


/**
 * dialog显示工具类
 */
public class DialogManager {
    /**
     * 弹框方法
     *
     * @param fragmentManager     (必传字段)
     * @param DialogExchangeModel (必传字段)
     * @return BaseDialogFragmentV2对象
     */
    public static BaseDialogFragment showDialogFragment(FragmentManager fragmentManager,
                                                        DialogExchangeModel DialogExchangeModel) {

        BaseDialogFragment baseDialogFragment = null;
        if (DialogExchangeModel != null) {
            Bundle bundle = new Bundle();
            bundle.putSerializable(BaseDialogFragment.TAG, DialogExchangeModel.dialogExchangeModelBuilder);
            DialogType dialogType = DialogExchangeModel.getDialogType();
            if (dialogType == DialogType.SINGLE) {
                baseDialogFragment = SingleInfoDialogFragment.getInstance(bundle);
            } else if (dialogType == DialogType.EXCUTE) {
                baseDialogFragment = ExcuteInfoDialogFragment.getInstance(bundle);
            } else if (dialogType == DialogType.PROGRESS) {
                baseDialogFragment = ProcessDialogFragment.getInstance(bundle);
            }
        }
        if (baseDialogFragment != null) {
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.add(baseDialogFragment, DialogExchangeModel.getTag());
            ft.commitAllowingStateLoss();
        }
        return baseDialogFragment;
    }


//    /**
//     * 弹框方法
//     *
//     * @param fragmentManager     (必传字段)
//     * @param DialogExchangeModel (必传字段)
//     * @return BaseDialogFragmentV2对象
//     */
//    public static ProcessDialogFragment showDialogFragment(FragmentManager fragmentManager,
//                                                           DialogExchangeModel DialogExchangeModel) {
//
//        ProcessDialogFragment baseDialogFragment = null;
//        if (DialogExchangeModel != null) {
//            Bundle bundle = new Bundle();
//            bundle.putSerializable(ProcessDialogFragment.TAG, DialogExchangeModel.dialogExchangeModelBuilder);
//            baseDialogFragment = ProcessDialogFragment.getInstance(bundle);
//        }
//        if (baseDialogFragment != null) {
//            FragmentTransaction ft = fragmentManager.beginTransaction();
//            ft.add(baseDialogFragment, DialogExchangeModel.getTag());
//            ft.commitAllowingStateLoss();
//        }
//        return baseDialogFragment;
//    }

    public static void checkAndDismissProgressDialog(FragmentManager manager, String tag) {
        Fragment progress = manager.findFragmentByTag(tag);
        if (progress != null && progress instanceof ProcessDialogFragment) {
            ((ProcessDialogFragment) progress).dismiss();
        }
    }

    /**
     * 显示loading效果
     *
     * @param tag 通常使用broadcast的action.可以在服务回来以后自动取消
     */
    public static ProcessDialogFragment showSimpleProgress(FragmentManager fragmentManager, String tag) {
        DialogExchangeModel.DialogExchangeModelBuilder builder = new DialogExchangeModel.DialogExchangeModelBuilder(DialogType.PROGRESS, tag);
        builder.setBackable(false);
        return (ProcessDialogFragment) DialogManager.showDialogFragment(fragmentManager, builder.creat());
    }


    /**
     * 显示只有一个按钮的对话框,activity和fragment不能全部为空
     *
     * @param content 对话框内容
     */
    public static void showSimpleInfoDialog(FragmentManager fragmentManager, String content, DialogClickInterface clickInterface) {
        DialogExchangeModel.DialogExchangeModelBuilder builder = new DialogExchangeModel.DialogExchangeModelBuilder(DialogType.SINGLE, "dialog");
        builder.setBackable(false).setDialogContext(content).setClickInterface(clickInterface);
        DialogManager.showDialogFragment(fragmentManager, builder.creat());
    }

    /**
     * 显示有两个按钮按钮的对话框,activity和fragment不能全部为空
     *
     * @param content 对话框内容
     */
    public static void showSimpleExcuteDialog(FragmentManager fragmentManager, String content, DialogClickInterface clickInterface) {
        DialogExchangeModel.DialogExchangeModelBuilder builder = new DialogExchangeModel.DialogExchangeModelBuilder(DialogType.EXCUTE, "dialog");
        builder.setBackable(true).setDialogContext(content).setClickInterface(clickInterface);
        DialogManager.showDialogFragment(fragmentManager, builder.creat());
    }

}
