package rst.framework.model.response;

import rst.framework.interfaces.UnProguard;

/**
 * Created by iceman.xu on 2014/11/17.
 * 网络响应基类.包含通常具备的code和msg
 */
public class BaseResponse implements UnProguard {
    public int code = 0;
    public String message = "";
}
