package cn.kayleh.trend.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author: Kayleh
 * @Date: 2020/9/3 8:10
 */
@Controller
public class ViewController {
    @GetMapping("/")
    public String view() throws Exception {
        return "view";
    }
}
