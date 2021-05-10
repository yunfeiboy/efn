package com.nowbio.database.efn.exception;


import com.nowbio.database.efn.common.api.IErrorCode;

/**
 * 断言处理类，用于抛出各种API异常
 * @author yun
 * @since 2021/5/7 14:51
 */
public class Asserts {
    public static void fail(String message) {
        throw new ApiException(message);
    }

    public static void fail(IErrorCode errorCode) {
        throw new ApiException(errorCode);
    }
}
