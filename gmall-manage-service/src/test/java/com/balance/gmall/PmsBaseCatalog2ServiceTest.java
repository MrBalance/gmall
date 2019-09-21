package com.balance.gmall;

import com.balance.gmall.po.catalog.PmsBaseCatalog2;
import com.balance.gmall.service.catalog.PmsBaseCatalog2Service;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description:
 * @author: yunzhang.du
 * @date: 2019年09月17日
 * @version: v1.0
 * @since: JDK 1.8
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PmsBaseCatalog2ServiceTest {
    @Resource
    PmsBaseCatalog2Service pmsBaseCatalog2Service;

    @Test
    public void selectListByCatalog1IdIdTest() {
        List<PmsBaseCatalog2> pmsBaseCatalog2s = pmsBaseCatalog2Service.selectListByCatalog1IdId(1L);
        pmsBaseCatalog2s.forEach(System.out::println);
    }
}
