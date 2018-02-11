package com.tangzq.common;

import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class ConfigTools {

    private static PropertiesLoader gitConfigLoader = new PropertiesLoader("git.properties");

    private static Map<String, String> gitConfigMap = new HashMap<>();

    public static String getConfig(String key) {
        String value = gitConfigMap.get(key);
        if (value == null){
            value = gitConfigLoader.getProperty(key);
            gitConfigMap.put(key, value != null ? value : StringUtils.EMPTY);
        }
        return value;
    }

}
