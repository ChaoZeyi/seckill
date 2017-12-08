package org.seckill.exception;

/**
 * @author cherry
 * @date 2017/12/7 16:59
 */
public class SeckillRepeatException extends SeckillException {
    public SeckillRepeatException(String message) {
        super(message);
    }

    public SeckillRepeatException(String message, Throwable cause) {
        super(message, cause);
    }
}
