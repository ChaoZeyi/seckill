package org.seckill.dto;

/**
 * @author cherry
 * @date 2017/12/11 20:36
 */

/**
 * 所有与前端交互的ajax请求的返回状态，是否执行成功
 * 如果成功，封装json结果；
 * 如果不成功，返回错误信息
 */
public class SeckillResult<T> {

    /**
     * 执行是否成功，是否有数据返回
      */
    private boolean success;
    /**
     * 数据类型不定，所以我们采用泛型
     */
    private T data;
    private String error;

    public SeckillResult(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    public SeckillResult(boolean success, String error) {
        this.success = success;
        this.error = error;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
