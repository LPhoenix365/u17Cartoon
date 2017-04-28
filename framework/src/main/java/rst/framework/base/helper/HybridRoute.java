package rst.framework.base.helper;

import rst.framework.model.viewmodel.BaseViewModel;

/**
 * Created by iceman on 16/8/18 16:48
 * 邮箱：xubin865@pingan.com.cn
 */
public class HybridRoute {
    public static final int ROUTE_TYPE_NATIVE = 1;
    public static final int ROUTE_TYPE_H5 = 2;

    /**
     * 模块名称
     */
    public String moduleName;
    /**
     * 模块中文名
     */
    public String moduleCName;

    /**
     * 是否需要登录
     */
    public boolean needLogin;
    /**
     * 是否需要绑定
     */
    public boolean needBind;


    public boolean needGesture;
    public boolean needV2;
    public boolean needV3;
    /**
     * 模块类型
     */
    public int moduleType;

    public Class pageClass;
    public BaseViewModel viewModel;
}
