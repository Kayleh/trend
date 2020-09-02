package cn.kayleh.trend.client;

import cn.hutool.core.collection.CollectionUtil;
import cn.kayleh.trend.pojo.IndexData;
import org.springframework.stereotype.Component;

import java.util.List;

//当熔断发生的时候，对应的方法就会被调用了。
//这里的方法就是指如果 INDEX-DATA-SERVICE 不可用或者不可访问，就会返回个 0000-00-00 出去啦。

/**
 * @Author: Kayleh
 * @Date: 2020/9/2 20:12
 */
@Component
public class IndexDataClientFeignHystrix implements IndexDataClient {
    @Override
    public List<IndexData> getIndexData(String code) {
        IndexData indexData = new IndexData();
        indexData.setClosePoint(0);
        indexData.setDate("0000-00-00");
        return CollectionUtil.toList(indexData);
    }
}
