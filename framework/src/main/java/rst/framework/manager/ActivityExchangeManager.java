package rst.framework.manager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;


import rst.framework.FrameworkConstants;
import rst.framework.model.viewmodel.BaseViewModel;

/**
 * activity跳转工具类,自动打包viewmodel
 */
public class ActivityExchangeManager {
    public static void goActivity(Context context, Class classname, BaseViewModel viewModel) {
        goActivity(context, classname, viewModel, null);
    }


    public static void goActivity(Context context, Class classname) {
        goActivity(context, classname,null,null);
    }

    public static void goActivity(Context context, Class classname, BaseViewModel viewModel, Bundle bundle) {
        Intent it = new Intent(context, classname);
        if (bundle != null) {
            it.putExtras(bundle);
        }
        if (viewModel != null) {
            it.putExtra(FrameworkConstants.PAGE_BASE_EXCHANGEMODEL, viewModel);
        }
        context.startActivity(it);
    }

    public static Bundle makeBundle(BaseViewModel viewModel) {
        Bundle bun = new Bundle();
        bun.putSerializable(FrameworkConstants.PAGE_BASE_EXCHANGEMODEL, viewModel);
        return bun;
    }

    public static void goActivity(Context context, Intent it) {
        if (it != null) {
            context.startActivity(it);
        }
    }
}
