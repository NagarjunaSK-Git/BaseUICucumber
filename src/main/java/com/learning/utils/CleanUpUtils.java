package com.learning.utils;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class CleanUpUtils {
    String driverFolderPath ;
    private CleanUpUtils(){
        driverFolderPath = "";
    }

    public static void cleanUpDriverFolders(String driverFolderPath) {
        File directory = new File(driverFolderPath);
        try {
            FileUtils.forceDelete(directory);
        } catch (IOException e) {
            System.out.println("Not able to do initial clean up of drivers folder :"+ e.getMessage());
            e.printStackTrace();
        }
    }
}
