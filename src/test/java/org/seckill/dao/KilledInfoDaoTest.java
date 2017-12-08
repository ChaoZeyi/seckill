package org.seckill.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.KilledInfo;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * @author cherry
 * @date 2017/12/7 11:12
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class KilledInfoDaoTest {

    @Resource
    private KilledInfoDao killedInfoDao;
    @Test
    public void insertKilledInfo() throws Exception {
        long id = 1001;
        long userPhone = 123456789;
        int insertCount = killedInfoDao.insertKilledInfo(id, userPhone);
        System.out.println(insertCount);
    }

    @Test
    public void queryById() throws Exception {
        long id = 1001;
        long userPhone = 123456789;
        KilledInfo killedInfo = killedInfoDao.queryById(id, userPhone);
        System.out.println(killedInfo);
        System.out.println(killedInfo.getRepo());
    }

}