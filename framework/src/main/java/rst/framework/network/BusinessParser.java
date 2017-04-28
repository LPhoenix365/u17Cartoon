package rst.framework.network;

import rst.framework.network.BusinessResult;

/**
 * Created by iceman on 15/6/22.
 * 业务数据解析接口
 */
public interface BusinessParser<T> {
    /**
     * 解析返回的response
     * @param t 返回的数据
     * @return
     */
    BusinessResult parseData(T t);
}
