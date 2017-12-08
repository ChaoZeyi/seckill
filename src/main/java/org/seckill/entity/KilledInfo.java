package org.seckill.entity;

import java.util.Date;

/**
 * @author cherry
 * @date 2017/12/5 20:22
 */
public class KilledInfo {
    private long productId;
    private long userPhone;
    private short state;
    private Date createTime;
    // 多对一
    private Repo repo;

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public long getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(long userPhone) {
        this.userPhone = userPhone;
    }

    public short getState() {
        return state;
    }

    public void setState(short state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Repo getRepo() {
        return repo;
    }

    public void setRepo(Repo repo) {
        this.repo = repo;
    }

    @Override
    public String toString() {
        return "KilledInfo{" +
                "productId=" + productId +
                ", userPhone=" + userPhone +
                ", state=" + state +
                ", createTime=" + createTime +
                '}';
    }
}
