package cn.kayleh.trend.service;

import cn.hutool.core.collection.CollUtil;
import cn.kayleh.trend.pojo.IndexData;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Kayleh
 * @Date: 2020/9/1 19:14
 */
@Service
@CacheConfig(cacheNames = "index_datas")
public class IndexDataService {
    @Cacheable(key = "'indexData-code-'+ #p0")
    public List<IndexData> get(String code) {
        return CollUtil.toList();
    }
}
