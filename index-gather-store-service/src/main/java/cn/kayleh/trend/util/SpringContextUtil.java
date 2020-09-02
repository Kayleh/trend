//用于获取 IndexService.
//用了 SpringContextUtil.getBean去重新获取了一次 IndexService，为什么不直接在 fresh 方法里调用 remove, store 方法，而要重新获取一次呢？
//这个是因为 springboot 的机制大概有这么个 bug吧.从已经存在的方法里调用redis相关方法，并不能触发 redis 相关操作，所以只好用这种方式重新获取一次了。
package cn.kayleh.trend.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringContextUtil implements ApplicationContextAware {

    private SpringContextUtil() {
        System.out.println("SpringContextUtil()");
    }

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        System.out.println("applicationContext:" + applicationContext);
        SpringContextUtil.applicationContext = applicationContext;
    }

    public static <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }

}
