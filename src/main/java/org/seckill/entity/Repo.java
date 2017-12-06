package org.seckill.entity;

import java.util.Date;

/**
 * @author cherry
 * @date 2017/12/5 20:17
 */
public class Repo {
    private long product_id;
    private String product_name;
    private int product_num;
    private Date strat_time;
    private Date end_time;
    private Date create_time;

    public long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(long product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public int getProduct_num() {
        return product_num;
    }

    public void setProduct_num(int product_num) {
        this.product_num = product_num;
    }

    public Date getStrat_time() {
        return strat_time;
    }

    public void setStrat_time(Date strat_time) {
        this.strat_time = strat_time;
    }

    public Date getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Date end_time) {
        this.end_time = end_time;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    @Override
    public String toString() {
        return "Repo{" +
                "product_id=" + product_id +
                ", product_name='" + product_name + '\'' +
                ", product_num=" + product_num +
                ", strat_time=" + strat_time +
                ", end_time=" + end_time +
                ", create_time=" + create_time +
                '}';
    }
}
