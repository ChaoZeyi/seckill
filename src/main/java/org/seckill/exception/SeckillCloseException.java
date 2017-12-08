package org.seckill.exception;

/**
 * @author cherry
 * @date 2017/12/7 16:58
 */
public class SeckillCloseException extends SeckillException{
    public SeckillCloseException(String message) {
        super(message);
    }

    public SeckillCloseException(String message, Throwable cause) {
        super(message, cause);
    }
}
