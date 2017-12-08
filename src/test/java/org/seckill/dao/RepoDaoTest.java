package org.seckill.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.Repo;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import org.seckill.dao.RepoDao;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author cherry
 * @date 2017/12/6 20:58
 */

/**
 * 配置spring和junit整合，junit启动时加载springIOC容器
 */
@RunWith(SpringJUnit4ClassRunner.class)
// 告诉junit spring的配置文件
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class RepoDaoTest {


    // 注入DAO实现类依赖
    @Resource
    private RepoDao repoDao;

    @Test
    public void queryById() throws Exception {
        long id = 1000;
        Repo repo = repoDao.queryById(id);
        System.out.println(repo.getProductName());
        System.out.println(repo);
    }

    @Test
    public void queryAll() throws Exception {
        List<Repo> repoList = repoDao.queryAll(0,100);
        for(Repo repo : repoList)
        {
            System.out.println(repo);
        }
    }

    @Test
    public void reduceNum() throws Exception {
        Date killTime = new Date();
        int updateCount = repoDao.reduceNum(1000, killTime);
        System.out.println(updateCount);

    }



}