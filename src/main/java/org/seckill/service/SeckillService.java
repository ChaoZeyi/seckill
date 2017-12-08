package org.seckill.service;

/**
 * @author cherry
 * @date 2017/12/7 16:01
 */

import org.seckill.dto.Exposure;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.Repo;
import org.seckill.exception.SeckillCloseException;
import org.seckill.exception.SeckillException;
import org.seckill.exception.SeckillRepeatException;

import java.util.List;

/**
 * 业务逻辑接口：站在使用者的角度设计
 * 主要实现下面几个功能：
 * 1、显示所有的秒杀商品项
 * 2、显示单个秒杀商品项
 * 3、对某个商品选择秒杀时，如果到达秒杀时间，则进入秒杀页面，如果不在秒杀时间范围内，则显示提醒
 * 4、进入秒杀页，点击秒杀，执行数据操作
 * 5、根据秒杀是否成功，显示不同的页面
 *
 */
public interface SeckillService {
    /**
     *
     * @return 展示所有的秒杀商品项
     */
    List<Repo> getSeckillList();

    /**
     *
     * @param productId 商品id
     * @return 给定id的秒杀商品信息
     */
    Repo getById(long productId);

    /**
     * 对给定的秒杀商品id
     * 如果符合秒杀条件，则输出秒杀地址
     * 否则输出系统时间和秒杀时间
     * @param productId
     */
    Exposure exportSeckillUrl(long productId);

    /**
     * 执行秒杀操作
     * @param productId
     * @param userPhone
     * @param md5
     */
    SeckillExecution executeSeckill(long productId, long userPhone, String md5)
        throws SeckillException, SeckillRepeatException, SeckillCloseException;




}
