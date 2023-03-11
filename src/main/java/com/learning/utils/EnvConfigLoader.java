package com.learning.utils;

import com.learning.constants.AppConstants;
import com.learning.constants.Environment;

import java.util.Properties;

public class EnvConfigLoader {

    private final Properties properties;
    private static EnvConfigLoader configLoader;

    private EnvConfigLoader() {
        String env = System.getProperty("env", String.valueOf(Environment.QA)).strip().toUpperCase();
        System.out.println("Running Test in :"+env+" : environment");
        System.out.println("Root Path :"+System.getProperty("user.dir"));
        String basePropFilepath = System.getProperty("user.dir")+"/src/main/resources/properties/";
        switch (Environment.valueOf(env)) {
            case QA -> properties = PropertyReaderBase.propertyLoader(basePropFilepath+"qa.properties");
            case UAT -> properties = PropertyReaderBase.propertyLoader(basePropFilepath+"uat.properties");
            default -> throw new IllegalStateException("INVALID ENV: " + env);
        }
    }

    public static EnvConfigLoader getInstance() {
        if (configLoader == null) {
            configLoader = new EnvConfigLoader();
        }
        return configLoader;
    }

    public String getApplicationUrl() {
        String prop = properties.getProperty(AppConstants.APP_URL);
        if (prop != null) return prop;
        else throw new RuntimeException("property APP_URL is not specified in the stage_config.properties file");
    }
}

