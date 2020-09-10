package cn.kayleh.trend.web;

import cn.kayleh.trend.pojo.Index;
import cn.kayleh.trend.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: Kayleh
 * @Date: 2020/8/31 13:15
 */
@RestController
public class IndexController
{
    @Autowired
    IndexService indexService;

//  http://127.0.0.1:8001/freshCodes
//  http://127.0.0.1:8001/getCodes
//  http://127.0.0.1:8001/removeCodes


    //    @GetMapping("/getCodes")
//    public List<Index> get() throws Exception {
//        return indexService.fetch_indexes_from_third_part();
//    }
    @GetMapping("/freshCodes")
    public List<Index> fresh() throws Exception
    {
        return indexService.fresh();
    }

    @GetMapping("/getCodes")
    public List<Index> get() throws Exception
    {
        return indexService.get();
//        return indexService.fetch_indexes_from_third_part();

    }

    @GetMapping("/removeCodes")
    public String remove() throws Exception
    {
        indexService.remove();
        return "remove codes successfully";
    }
}
