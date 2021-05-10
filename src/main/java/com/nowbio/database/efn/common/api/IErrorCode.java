package com.nowbio.database.efn.common.api;

/**
 * 封装API的错误码
 * @author yun
 * @since 2021/5/10
 */
public interface IErrorCode {
    long getCode();

    String getMessage();
}
