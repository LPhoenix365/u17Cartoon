package rst.framework.network;

/**
 * @Description
 * @Author louyaming802
 * @Date 16/10/26
 * @Version 2.5.X
 */
public class RequestType {

    public interface Method {
        int DEPRECATED_GET_OR_POST = -1;
        int GET = 0;
        int POST = 1;
        int PUT = 2;
        int DELETE = 3;
        int HEAD = 4;
        int OPTIONS = 5;
        int TRACE = 6;
        int PATCH = 7;
    }

}
