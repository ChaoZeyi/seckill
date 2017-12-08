package org.seckill.dto;

/**
 * @author cherry
 * @date 2017/12/7 16:47
 */

import org.seckill.entity.KilledInfo;
import org.seckill.enums.SeckillStateEnum;

/**
 * 封装秒杀执行后的结果
 * 秒杀是否成功，如果失败的话，失败原因是什么
 */
public class SeckillExecution {

    private long productId;
    private int state;
    private String stateInfo;
    /**
     * 如果秒杀成功，返回秒杀成功对象
     */
    private KilledInfo killedInfo;

    public SeckillExecution(long productId, SeckillStateEnum seckillStateEnum, KilledInfo killedInfo) {
        this.productId = productId;
        this.state = seckillStateEnum.getState();
        this.stateInfo = seckillStateEnum.getStateInfo();
        this.killedInfo = killedInfo;
    }

    public SeckillExecution(long productId, SeckillStateEnum seckillStateEnum) {
        this.productId = productId;
        this.state = seckillStateEnum.getState();
        this.stateInfo = seckillStateEnum.getStateInfo();
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public KilledInfo getKilledInfo() {
        return killedInfo;
    }

    public void setKilledInfo(KilledInfo killedInfo) {
        this.killedInfo = killedInfo;
    }

    @Override
    public String toString() {
        return "SeckillExecution{" +
                "productId=" + productId +
                ", state=" + state +
                ", stateInfo='" + stateInfo + '\'' +
                ", killedInfo=" + killedInfo +
                '}';
    }
}
