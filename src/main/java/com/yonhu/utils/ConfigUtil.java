package com.yonhu.utils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigUtil {

    private static String configPath = "C:\\Users\\lizx\\IdeaProjects-mmall\\schedule\\src\\main\\resource\\zhiban.properties";
    private static Properties properties = null;

    public static String getConfigValue(String key) {
        return getProperties().getProperty(key);
    }

    private static Properties getProperties() {
        if (properties == null) {
            synchronized (ConfigUtil.class) {
                init();
            }
        }
        return properties;
    }

    private static void init() {
        try {
            properties = new Properties();
            properties.load(new FileReader(new File(configPath)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
