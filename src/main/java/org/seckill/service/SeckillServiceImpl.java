package org.seckill.service;

import org.seckill.dao.KilledInfoDao;
import org.seckill.dao.RepoDao;
import org.seckill.dto.Exposure;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.KilledInfo;
import org.seckill.entity.Repo;
import org.seckill.enums.SeckillStateEnum;
import org.seckill.exception.SeckillCloseException;
import org.seckill.exception.SeckillException;
import org.seckill.exception.SeckillRepeatException;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


/**
 * @author cherry
 * @date 2017/12/7 17:04
 */

/**
 * 实现业务逻辑接口
 */
@Service
public class SeckillServiceImpl implements SeckillService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * @Autowired 和 @Resource注入的区别在于，一个是按类型注入，一个是按名字注入，推荐按类型注入，因为我们在进行包扫描时，扫描的是
        类名
     */
    @Autowired
    private RepoDao repoDao;
    @Autowired
    private KilledInfoDao killedInfoDao;

    // 盐值，用于混淆md5
    private final String salt = "cbohis9wr8u34cf43hf87R^&*";

    /**
     * @return 展示所有的秒杀商品项
     */
    @Override
    public List<Repo> getSeckillList() {

        return repoDao.queryAll(0, 5);
    }

    /**
     * @param productId 商品id
     * @return 给定id的秒杀商品信息
     */
    @Override
    public Repo getById(long productId) {
        return repoDao.queryById(productId);
    }

    /**
     * 对给定的秒杀商品id
     * 如果符合秒杀条件，则输出秒杀地址
     * 否则输出系统时间和秒杀时间
     *
     * @param productId
     */
    @Override
    public Exposure exportSeckillUrl(long productId) {
        // 判断是否符合秒杀条件

        // 1:是否存在该商品id
        Repo repo = repoDao.queryById(productId);
        if(repo == null){
            // 不存在该商品id
            return new Exposure(productId, false);
        }
        // 2:当前时间是否满足秒杀时间
        Date startTime = repo.getStartTime();
        Date endTime = repo.getEndTime();
        Date now = new Date();
        if(now.getTime() < startTime.getTime() || now.getTime() > endTime.getTime()){
            // 不满足秒杀时间
            return new Exposure(productId, false, now.getTime(), startTime.getTime(), endTime.getTime());
        }

        // 如果两个条件都满足，则说明可以开始秒杀
        String md5 = getMd5(repo.getProductId());
        return new Exposure(productId, true, md5);
    }
    private String getMd5(long productId){
        String base = productId + "/" +salt;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }

    /**
     * 执行秒杀操作
     *
     * @param productId
     * @param userPhone
     * @param md5
     */
    @Override
    @Transactional
    /**
     * 不是所有的方法都需要事务，如果只有一条修改操作，或者是只读操作，则不需要事务控制
     */
    public SeckillExecution executeSeckill(long productId, long userPhone, String md5)
            throws SeckillException, SeckillRepeatException, SeckillCloseException {
        //判断md5是否匹配
        if(md5 == null || (!md5.equals(getMd5(productId)))){
            throw new SeckillException("seckill data rewrite");

        }
        // 执行秒杀逻辑,减少库存和插入秒杀明细表属于一个事务，如果某条语句执行报错，整个事务都会回滚
        Date now = new Date();
        try {
            int updateCount = repoDao.reduceNum(productId, now);
            if (updateCount <= 0) {
                //没有更新库存,说明库存不足
                throw new SeckillCloseException("seckill is closed");
            } else {
                // 库存足够，可以秒杀
                int insertCount = killedInfoDao.insertKilledInfo(productId, userPhone);
                if (insertCount <= 0) {
                    //说明重复秒杀
                    throw new SeckillRepeatException("repeat seckill");
                } else {
                    KilledInfo killedInfo = killedInfoDao.queryById(productId, userPhone);
                    return new SeckillExecution(productId, SeckillStateEnum.SUCCESS, killedInfo);
                }
            }
        }
        catch(SeckillCloseException e1){
            throw e1;
        }catch(SeckillRepeatException e2){
            throw e2;
        }
        catch(Exception e)
        {
            logger.error(e.getMessage(), e);
            throw new SeckillException("seckill inner error:"+e.getMessage());
        }
    }
}
