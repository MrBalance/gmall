package com.balance.gmall;

import com.balance.gmall.po.attr.PmsBaseAttrInfo;
import com.balance.gmall.po.attr.PmsBaseAttrValue;
import com.balance.gmall.service.BaseAttrService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: yunzhang.du
 * @date: 2019年09月18日
 * @version: v1.0
 * @since: JDK 1.8
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PmsBaseAttrInfoServiceTest {
    @Resource
    BaseAttrService attrService;

    @Test
    public void saveAttrInfo() {
        PmsBaseAttrInfo pmsBaseAttrInfo = new PmsBaseAttrInfo();
        pmsBaseAttrInfo.setAttrName("颜色");
        pmsBaseAttrInfo.setCatalog3Id(61L);
        List<PmsBaseAttrValue> pmsBaseAttrValues = new ArrayList<>();
        PmsBaseAttrValue pmsBaseAttrValue1 = new PmsBaseAttrValue();
        pmsBaseAttrValue1.setValueName("黑");
        PmsBaseAttrValue pmsBaseAttrValue2 = new PmsBaseAttrValue();
        pmsBaseAttrValue2.setValueName("白");
        pmsBaseAttrValues.add(pmsBaseAttrValue1);
        pmsBaseAttrValues.add(pmsBaseAttrValue2);
        pmsBaseAttrInfo.setAttrValueList(pmsBaseAttrValues);
        attrService.saveAttrInfo(pmsBaseAttrInfo);
    }
}
