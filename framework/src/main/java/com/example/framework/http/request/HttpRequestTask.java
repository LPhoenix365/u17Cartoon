package com.example.framework.http.request;

import android.content.Context;

import com.android.volley.Request;
import com.example.framework.http.response.HttpResultParser;

import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description 网络请求任务
 * @Author chenyongjian949
 * @Email chenyongjian949@pingan.com.cn
 * @Date 16/7/12
 * @Version 2.5.1
 */
public class HttpRequestTask<T> {
    protected String url;
    protected int method = Request.Method.GET;
    protected boolean needCacheData = false;
    protected String servieTag = "default";
    protected String          jsonRequestEntity;
    protected JSONObject      jsonParams;
    protected AbRequestParams abRequestParams;
    protected Map<String, String> headMap      = new HashMap<>();
    protected boolean             isFormSubmit = false;
    /**
     * 是否显示转圈
     */
//    private boolean isShowProcess = true;

    /**
     * 显示Loading样式 0 不显示 1 状态小人 2 菊花转
     */
    private int loadingType = 1;
    /**
     * 是否可以取消
     */
    private boolean isCancelable = true;
    /**
     * 是否使用模拟数据
     */
    private boolean useVirtualData = false;
    /**
     * loadin提示信息
     */
    private String progressContent;

    /**
     * 模块标识
     */
    private String moduleFlag;
    HttpResultParser<T> httpResultParser;

    private Context context;

    protected Class<T> responsType;

    private WeakReference<HttpResultCallback> callback;

    public HttpRequestTask(Context context) {
        this.context = context;
    }

    private boolean isMultipart = false;

    public HttpRequestTask(Context context, Class<T> responsType) {
        this.context = context;
        this.responsType = responsType;
    }

    public boolean isFormSubmit() {
        return isFormSubmit;
    }

    public HttpRequestTask<T> setFormSubmit(boolean formSubmit) {
        isFormSubmit = formSubmit;
        return this;
    }

    public WeakReference<HttpResultCallback> getCallback() {
        return callback;
    }

    public HttpRequestTask<T> setCallback(HttpResultCallback callback) {
        this.callback = new WeakReference<>(callback);
        return this;
    }

    public String getUrl() {
        return url;
    }

    public HttpRequestTask<T> setUrl(String url) {
        this.url = url;
        return this;
    }

    public boolean isMultipart() {
        return isMultipart;
    }

    public HttpRequestTask<T> setMultipart(boolean isMultipart) {
        this.isMultipart = isMultipart;
        method = Request.Method.POST;
        return this;
    }

    public int getMethod() {
        return method;
    }

    public HttpRequestTask<T> setMethod(int method) {
        this.method = method;
        return this;
    }

    public boolean isNeedCacheData() {
        return needCacheData;
    }

    public HttpRequestTask<T> setNeedCacheData(boolean needCacheData) {
        this.needCacheData = needCacheData;
        return this;
    }

    public String getServieTag() {
        return servieTag;
    }

    public HttpRequestTask<T> setServieTag(String servieTag) {
        this.servieTag = servieTag;
        return this;
    }

    public String getJsonRequestEntity() {
        return jsonRequestEntity;
    }

    public HttpRequestTask<T> setJsonRequestEntity(String jsonRequestEntity) {
        this.jsonRequestEntity = jsonRequestEntity;
        return this;
    }

    public JSONObject getJsonParams() {
        return jsonParams;
    }

    public HttpRequestTask<T> setJsonParams(JSONObject jsonParams) {
        this.jsonParams = jsonParams;
        method = Request.Method.POST;
        return this;
    }

    public AbRequestParams getAbRequestParams() {
        return abRequestParams;
    }

    public HttpRequestTask<T> setAbRequestParams(AbRequestParams abRequestParams) {
        this.abRequestParams = abRequestParams;
        return this;
    }

    public Map<String, String> getHeadMap() {
        return headMap;
    }

    public HttpRequestTask<T> setHeadMap(Map<String, String> headMap) {
        this.headMap = headMap;
        return this;
    }

//    public boolean isShowProcess() {
//        return isShowProcess;
//    }

//    public HttpRequestTask<T> setShowProcess(boolean showProcess) {
//        this.isShowProcess =showProcess;
//        return this;
//    }

    public HttpRequestTask<T> setLoadingType(int loadingType) {
        this.loadingType = loadingType;
        return this;
    }

    public HttpRequestTask<T> setModuleFlag(String moduleFlag) {
        this.moduleFlag = moduleFlag;
        return this;
    }

    public String getModuleFlag() {
        return moduleFlag;
    }

    public int getLoadingType() {
        return loadingType;
    }

    public boolean isCancelable() {
        return isCancelable;
    }

    public HttpRequestTask<T> setCancelable(boolean cancelable) {
        isCancelable = cancelable;
        return this;
    }

    public boolean isUseVirtualData() {
        return useVirtualData;
    }

    public HttpRequestTask<T> setUseVirtualData(boolean useVirtualData) {
        this.useVirtualData = useVirtualData;
        return this;
    }

    public String getProgressContent() {
        return progressContent;
    }

    public HttpRequestTask<T> setProgressContent(String progressContent) {
        this.progressContent = progressContent;
        return this;
    }

    public HttpResultParser<T> getHttpResultParser() {
        return httpResultParser;
    }

    public HttpRequestTask<T> setHttpResultParser(HttpResultParser<T> httpResultParser) {
        this.httpResultParser = httpResultParser;
        return this;
    }

    public Context getContext() {
        return context;
    }

    public HttpRequestTask<T> setContext(Context context) {
        this.context = context;
        return this;
    }

    public Class<T> getResponsType() {
        return responsType;
    }

    public HttpRequestTask<T> setResponsType(Class<T> responsType) {
        this.responsType = responsType;
        return this;
    }
}
