package org.seckill.dto;

/**
 * @author cherry
 * @date 2017/12/7 16:23
 */

/**
 * 暴露秒杀地址url
 */
public class Exposure {

    private long productId;
    /**
     * 是否开始秒杀
     */

    private boolean exposed;

    /**
     *  加密后的地址
    */
    private String md5;

    /**
     * 系统时间
     */
    private long now;

    /**
     * 秒杀开始时间
     */
    private long start;

    /**
     * 秒杀结束时间
     */
    private long end;

    public Exposure(long productId, boolean exposed, String md5) {
        this.productId = productId;
        this.exposed = exposed;
        this.md5 = md5;
    }

    public Exposure(long productId, boolean exposed, long now, long start, long end) {
        this.productId = productId;
        this.exposed = exposed;
        this.now = now;
        this.start = start;
        this.end = end;
    }

    public Exposure(long productId, boolean exposed) {
        this.productId = productId;
        this.exposed = exposed;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public boolean isExposed() {
        return exposed;
    }

    public void setExposed(boolean exposed) {
        this.exposed = exposed;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public long getNow() {
        return now;
    }

    public void setNow(long now) {
        this.now = now;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "Exposure{" +
                "productId=" + productId +
                ", exposed=" + exposed +
                ", md5='" + md5 + '\'' +
                ", now=" + now +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}
