package com.balance.gmall;

import com.balance.gmall.dao.catalog.PmsBaseCatalog1Dao;
import com.balance.gmall.po.catalog.PmsBaseCatalog1;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description:
 * @Company: 爱用科技有限公司
 * @author: yunzhang.du
 * @date: 2019年09月16日
 * @version: v1.0
 * @since: JDK 1.8
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PmsBaseCatalog1DaoTest {

    @Resource
    PmsBaseCatalog1Dao pmsBaseCatalog1Dao;

    @Test
    public void selectAllTest() {
        List<PmsBaseCatalog1> pmsBaseCatalog1s = pmsBaseCatalog1Dao.selectList(null);
        pmsBaseCatalog1s.forEach(System.out::println);

    }
}
