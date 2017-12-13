package cn.hwolf.common.config;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author hwolf
 * @email h.wolf@qq.com
 * @date 2017/12/3.
 */
public class MyBatisGen {

    public static void main(String[] args) throws Exception {
        String fileName = "/Users/hwolf/IdeaProjects/ssm-common-configuration/src/main/resources/generatorConfig.xml";
        File configFile = new File(fileName);
        List<String> warnings = new ArrayList<>();
        boolean overwrite = true;
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
    }
}