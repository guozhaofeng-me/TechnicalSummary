package com.guozhf.network.nativecall;

import com.guozhf.network.exception.BusinessException;

/**
 * User: Zephyr
 * Date: 2022/5/10
 * Time: 9:25
 */
public class ResponseErrorHandler {
    /**
     * 在这里主要做code的转换，可以把异常code转换成统一的code，并给它对应的message
     * @param httpCode
     * @throws BusinessException
     */
    public static void handleNetworkError(int httpCode) throws BusinessException {
//        int errorCode = CodeKt.UNKNOWN_ERROR;
        String msg = "网络错误"; // 这里简单写成网络错误，实际上可以根据HttpCode里边的异常来返回对应的消息
        int errorCode = 2; // 假设错误码是2,也就是将所有的网络错误码统一成了2
        throw new BusinessException(msg, errorCode);
    }

    public static void handleRestfulError(String code, String body) throws BusinessException {
        throw new BusinessException("业务处理错误的异常");
    }
}
