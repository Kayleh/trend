package cn.kayleh.trend.service;

import cn.hutool.core.collection.CollUtil;
import cn.kayleh.trend.client.IndexDataClient;
import cn.kayleh.trend.pojo.IndexData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @Author: Kayleh
 * @Date: 2020/9/2 20:16
 */
//提供所有模拟回测数据的微服务
@Service
public class BackTestService {
    @Autowired
    IndexDataClient indexDataClient;

    public List<IndexData> listIndexData(String code) {
        List<IndexData> result = indexDataClient.getIndexData(code);
        Collections.reverse(result);

        for (IndexData indexData : result) {
            System.out.println(indexData.getDate());
        }
        return result;
    }
}
