package cn.hwolf.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PropertiesLoaderSupport;

import java.io.IOException;
import java.util.*;

/**
 * 配置文件查找
 *
 * @author hwolf
 * @email h.wolf@qq.com
 * @date 2017/12/2.
 */
public class ApplicationPropertiesFactoryBean extends PropertiesLoaderSupport
        implements FactoryBean<Properties>, InitializingBean {

    /**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(ApplicationPropertiesFactoryBean.class);
    /**
     * 资源类加载器
     */
    private ResourceLoader resourceLoader = new DefaultResourceLoader();
    /**
     * 资源所在路径
     */
    private String propertiesListLocation = "classpath:/properties.lst";
    /**
     * 配置文件
     */
    private Properties properties;

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public void afterPropertiesSet() throws IOException {
        // 判断加载哪些properties
        this.readProperties();
        // 默认设置找不到资源也不会报错
        this.setIgnoreResourceNotFound(true);
        // 加载properties
        this.properties = mergeProperties();
    }

    @Override
    public Properties getObject() throws IOException {
        return properties;
    }

    @Override
    public Class<Properties> getObjectType() {
        return Properties.class;
    }

    protected void readProperties() throws IOException {
        Resource propertiesListResource = resourceLoader.getResource(propertiesListLocation);
        List<Resource> resources = new ArrayList<Resource>();

        if (!propertiesListResource.exists()) {
            // 默认配置
            logger.info("use default properties");
            resources.add(resourceLoader.getResource("classpath:/application.properties"));
            resources.add(resourceLoader.getResource("classpath:/application.local.properties"));
            resources.add(resourceLoader.getResource("classpath:/application.server.properties"));
        } else {
            // 获取配置文件的内容
            String text = IoUtils.readString(propertiesListResource);

            // 各行读取
            for (String str : text.split("\n")) {
                str = str.trim();

                if (str.length() == 0) {
                    continue;
                }
                //
                resources.add(resourceLoader.getResource(str));
            }
        }

        setLocations(resources.toArray(new Resource[0]));
    }

    /**
     * 获取值
     * @return
     */
    public Map<String, Object> getMap() {
        Map<String, Object> map = new HashMap<String, Object>();

        for (String key : properties.stringPropertyNames()) {
            map.put(key, properties.get(key));
        }

        return map;
    }

    public String getPropertiesListLocation() {
        return propertiesListLocation;
    }

    public void setPropertiesListLocation(String propertiesListLocation) {
        this.propertiesListLocation = propertiesListLocation;
    }
}
