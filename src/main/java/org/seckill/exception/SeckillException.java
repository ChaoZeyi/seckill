package org.seckill.exception;

/**
 * @author cherry
 * @date 2017/12/7 16:58
 */
public class SeckillException extends RuntimeException{
    public SeckillException(String message) {
        super(message);
    }

    public SeckillException(String message, Throwable cause) {
        super(message, cause);
    }
}
