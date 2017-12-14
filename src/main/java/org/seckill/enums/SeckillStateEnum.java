package org.seckill.enums;

/**
 * @author cherry
 * @date 2017/12/8 10:14
 */

/**
 * 使用枚举存储执行状态和状态信息
 * 执行状态和状态信息可以组合成为一个数据字典
 */
public enum SeckillStateEnum{
    SUCCESS(1, "秒杀成功"),
    END(0, "秒杀结束"),
    REPEAT_KILL(-1, "重复秒杀"),
    INNER_ERROR(-2, "系统异常"),
    DATA_REWRITE(-3, "数据篡改");

    private int state;
    private String stateInfo;

    SeckillStateEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }
    //根据state得到整个枚举

    public static SeckillStateEnum getByState(int state){
        // values代表所有的枚举对象
        for(SeckillStateEnum stateEnum : values())
        {
            if(stateEnum.getState() == state) {
                return stateEnum;
            }
        }
        return null;
    }
}
