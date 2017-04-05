package com.example.framework.ab.network;

/**
 * @Description 网络请求解析
 * @Author chenyongjian949
 * @Email chenyongjian949@pingan.com.cn
 * @Date 16/7/12
 * @Version 2.5.1
 */
public interface HttpResultParser<T> {
   void parseData(T t);
}
