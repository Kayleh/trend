package cn.kayleh.trend.web;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.kayleh.trend.pojo.IndexData;
import cn.kayleh.trend.pojo.Profit;
import cn.kayleh.trend.pojo.Trade;
import cn.kayleh.trend.service.BackTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * @Author: Kayleh
 * @Date: 2020/9/2 20:26
 */
@RestController
public class BackTestController {
    @Autowired
    BackTestService backTestService;

//    @GetMapping("/simulate/{code}")
//    @CrossOrigin
//    public Map<String, Object> backTest(@PathVariable("code") String code) throws Exception {
//        List<IndexData> allIndexDatas = backTestService.listIndexData(code);
//        Map<String, Object> result = new HashMap<>();
//        result.put("indexDatas", allIndexDatas);
//        return result;
//    }

    @GetMapping("/simulate/{code}/{startDate}/{endDate}")
    @CrossOrigin
    public Map<String, Object> backTest(
            //参数可以接受开始日期和结束日期
            @PathVariable("code") String code,
            @PathVariable("startDate") String strStartDate,
            @PathVariable("endDate") String strEndDate
    ) throws Exception {
        List<IndexData> allIndexDatas = backTestService.listIndexData(code);

        //计算出开始日期和结束日期并返回
        String indexStartDate = allIndexDatas.get(0).getDate();
        String indexEndDate = allIndexDatas.get(allIndexDatas.size() - 1).getDate();

        //根据开始日期和结束日期获取对应日期范围的数据
        allIndexDatas = filterByDateRange(allIndexDatas, strStartDate, strEndDate);

        int ma = 20; //移动平均
        float sellRate = 0.95f;
        float buyRate = 1.05f;
        float serviceCharge = 0f;
        Map<String, ?> simulateResult = backTestService.simulate(ma, sellRate, buyRate, serviceCharge, allIndexDatas);
        List<Profit> profits = (List<Profit>) simulateResult.get("profits");
        //取出交易合集
        List<Trade> trades = (List<Trade>) simulateResult.get("trades");

        //获取年份
        float years = backTestService.getYear(allIndexDatas);
        //计算指数的收益和趋势投资的收益，以及对应的年化收益率。
        float indexIncomeTotal = (allIndexDatas.get(allIndexDatas.size() - 1).getClosePoint() - allIndexDatas.get(0).getClosePoint()) / allIndexDatas.get(0).getClosePoint();
        float indexIncomeAnnual = (float) Math.pow(1 + indexIncomeTotal, 1 / years) - 1;
        float trendIncomeTotal = (profits.get(profits.size() - 1).getValue() - profits.get(0).getValue()) / profits.get(0).getValue();
        float trendIncomeAnnual = (float) Math.pow(1 + trendIncomeTotal, 1 / years) - 1;
        //交易统计
        int winCount = (Integer) simulateResult.get("winCount");
        int lossCount = (Integer) simulateResult.get("lossCount");
        float avgWinRate = (Float) simulateResult.get("avgWinRate");
        float avgLossRate = (Float) simulateResult.get("avgLossRate");

        Map<String, Object> result = new HashMap<>();
        result.put("indexDatas", allIndexDatas);
        result.put("indexStartDate", indexStartDate);
        result.put("indexEndDate", indexEndDate);
        result.put("profits", profits);

        result.put("trades", trades);

        result.put("years", years);
        result.put("indexIncomeTotal", indexIncomeTotal);
        result.put("indexIncomeAnnual", indexIncomeAnnual);
        result.put("trendIncomeTotal", trendIncomeTotal);
        result.put("trendIncomeAnnual", trendIncomeAnnual);
        //交易统计
        result.put("winCount", winCount);
        result.put("lossCount", lossCount);
        result.put("avgWinRate", avgWinRate);
        result.put("avgLossRate", avgLossRate);

        return result;
    }

    private List<IndexData> filterByDateRange(List<IndexData> allIndexDatas, String strStartDate, String strEndDate) {
        if (StrUtil.isBlankOrUndefined(strStartDate) || StrUtil.isBlankOrUndefined(strEndDate))
            return allIndexDatas;

        List<IndexData> result = new ArrayList<>();
        Date startDate = DateUtil.parse(strStartDate);
        Date endDate = DateUtil.parse(strEndDate);

        for (IndexData indexData : allIndexDatas) {

            Date date = DateUtil.parse(indexData.getDate());
            if (date.getTime() >= startDate.getTime() && date.getTime() <= endDate.getTime()) {
                result.add(indexData);
            }
        }
        return result;
    }

}
