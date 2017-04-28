package rst.framework.model.entity;

/**
 * 每一个事件的唯一标识.包含环境+服务+时间戳
 * Created by iceman on 16/4/1 10:53
 * 邮箱：xubin865@pingan.com.cn
 */
public class TaskTagModel {
    public String contextTag;
    public String serviceTag;
    public long timeTag;

    public String getContextTag() {
        return contextTag;
    }

    public void setContextTag(String contextTag) {
        this.contextTag = contextTag;
    }

    public String getServiceTag() {
        return serviceTag;
    }

    public void setServiceTag(String serviceTag) {
        this.serviceTag = serviceTag;
    }

    public long getTimeTag() {
        return timeTag;
    }

    public void setTimeTag(long timeTag) {
        this.timeTag = timeTag;
    }

    public String getTag() {
        return contextTag + "-" + serviceTag + "-" + timeTag;
    }
}
