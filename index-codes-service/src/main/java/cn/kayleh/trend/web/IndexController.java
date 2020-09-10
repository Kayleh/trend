package cn.kayleh.trend.web;

import cn.kayleh.trend.config.IpConfiguration;
import cn.kayleh.trend.pojo.Index;
import cn.kayleh.trend.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: Kayleh
 * @Date: 2020/9/1 13:22
 */
@RestController
public class IndexController
{
    @Autowired
    IndexService indexService;
    @Autowired
    IpConfiguration ipConfiguration;

//  http://127.0.0.1:8011/codes

    @GetMapping("/codes")
    @CrossOrigin
    public List<Index> codes() throws Exception
    {
        System.out.println("current instance's port is " + ipConfiguration.getPort());
        return indexService.get();
    }
}
