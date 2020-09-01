package cn.kayleh.trend.job;

import cn.hutool.core.date.DateUtil;
import cn.kayleh.trend.pojo.Index;
import cn.kayleh.trend.service.IndexDataService;
import cn.kayleh.trend.service.IndexService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.List;

/**
 * @Author: Kayleh
 * @Date: 2020/9/1 12:48
 * 任务类，同时刷新指数代码和指数数据。
 */
public class IndexDataSyncJob extends QuartzJobBean {

    @Autowired
    private IndexService indexService;
    @Autowired
    private IndexDataService indexDataService;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("定时启动:" + DateUtil.now());
        List<Index> indexes = indexService.fresh();
        for (Index index : indexes) {
            indexDataService.fresh(index.getCode());
        }
        System.out.println("定时结束：" + DateUtil.now());

    }
}
