package cn.kayleh.trend.web;

import cn.kayleh.trend.pojo.IndexData;
import cn.kayleh.trend.service.BackTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Kayleh
 * @Date: 2020/9/2 20:26
 */
@RestController
public class BackTestController {
    @Autowired
    BackTestService backTestService;

    public Map<String, Object> backTest(@PathVariable("code") String code) throws Exception {
        List<IndexData> allIndexDatas = backTestService.listIndexData(code);
        Map<String, Object> result = new HashMap<>();
        result.put("indexDatas", allIndexDatas);
        return result;
    }
}