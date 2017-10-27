package com.pingan.u17.bean;

/**
 * Description
 *
 * @author liupeng502
 * @data 2017/10/27
 */

public class DispatchEventBean {
    public int type;
    public int modelId;
    public String desUrl;

    /**
     * @param type
     * @param modelId
     * @param desUrl
     */
    public DispatchEventBean(int type, int modelId, String desUrl) {
        this.type = type;
        this.modelId = modelId;
        this.desUrl = desUrl;
    }
}
