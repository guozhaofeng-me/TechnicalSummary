package com.guozhf.network.nativecall;


import com.google.gson.Gson;
import com.guozhf.network.BaseResponse;
import com.guozhf.network.CodeKt;
import com.guozhf.network.exception.BusinessException;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Response;

/**
 * User: Zephyr
 * Date: 2022/5/10
 * Time: 9:22
 */
public class RetrofitHelper {
    private static Gson gson = new Gson();

    /**
     * 使用同步的方法来执行网络请求
     * 请求会抛出异常，在这里统一处理异常，然后封装我们自己的异常类，然后再抛出
     * 异常处理包括了下面数字表明的4部分异常， 1， 2， 3， 4
     *
     * @param call
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> T executeCall(Call<BaseResponse<T>> call) throws BusinessException {
        try {
            Response<BaseResponse<T>> response = call.execute();
            if (response.isSuccessful()) { // 网络请求成功了
                BaseResponse<T> body = response.body();
                if (body == null) {
                    throw new BusinessException("未知错误", CodeKt.UNKNOWN_ERROR);
                }
                if (Objects.equals(body.getCode(), "200")) {
                    return body.getData();
                } else {
//                    ResponseErrorHandler.handleRestfulError(code, json);
                }



                String json = gson.toJson(body);
                JSONObject jsonObject = new JSONObject(json);
                String code = jsonObject.optString("code"); // 获取和后端定义的返回格式里的code
                ResponseErrorHandler.handleRestfulError(code, json);
                return body.getData();
            } else { // 2. 网络请求失败了，可以收到errorBody,这里的code是网络错误的code
//                response.errorBody().string()
                ResponseErrorHandler.handleNetworkError(response.code());
            }
        } catch (IOException ex) { // 1. 网络请求过程中抛出了异常，比如超时
            if (ex instanceof SocketTimeoutException) {
                throw new BusinessException("网络请求超时", CodeKt.REQUEST_TIMEOUT);
            }
            // 还有其他异常，这里不写了，都是网络异常
        } catch (JSONException e) { // 3. 数据解析的时候抛出了异常
            throw new BusinessException("数据解析错误", CodeKt.JSON_PARSE_ERROR);
        }
        // 4. 未知错误
        throw new BusinessException("未知错误", CodeKt.UNKNOWN_ERROR);
    }
}
