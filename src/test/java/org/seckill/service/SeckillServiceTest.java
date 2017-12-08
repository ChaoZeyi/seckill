package org.seckill.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.dao.RepoDao;
import org.seckill.dto.Exposure;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.Repo;
import org.seckill.exception.SeckillCloseException;
import org.seckill.exception.SeckillRepeatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author cherry
 * @date 2017/12/8 17:08
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml", "classpath:spring/spring-service.xml"})
public class SeckillServiceTest {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private SeckillService seckillService;

    @Test
    public void getSeckillList() throws Exception {
        List<Repo> list = seckillService.getSeckillList();
        logger.info("list={}",list);
    }

    @Test
    public void getById() throws Exception {
        long productId = 1000;
        Repo repo = seckillService.getById(productId);
        logger.info("repo={}",repo);
    }

    @Test
    public void exportSeckillUrl() throws Exception {
        long productId = 1000;
        Exposure exposure = seckillService.exportSeckillUrl(productId);
        logger.info("exposure={}", exposure);
    }

    @Test
    public void executeSeckill() throws Exception {
    }

    @Test
    public void testSeckillLogin() throws Exception{
        long productId = 1004;
        Exposure exposure = seckillService.exportSeckillUrl(productId);
        if(exposure.isExposed())
        {
            // 秒杀开启
            long userPhone = 123456789;
            String md5 = exposure.getMd5();
            try {
                SeckillExecution seckillExecution = seckillService.executeSeckill(productId, userPhone, md5);
                logger.info("result={}", seckillExecution);
            }catch(SeckillRepeatException e){
                logger.error(e.getMessage());
            }catch(SeckillCloseException e){
                logger.error(e.getMessage());
            }


        }else {
            //秒杀未开启
            logger.info("exposure={}", exposure);
        }
    }

}