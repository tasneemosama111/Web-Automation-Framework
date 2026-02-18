package com.swaglabs.Utils;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Properties;

public class PropertiesUtils {
    private PropertiesUtils(){
        super();
    }

    public final static String PROPERTIES_PATH = "src/main/resources/";
    // Load Properties function to load all data exists on .properties files into system properties
    public static Properties loadProperties() {
        try {
            //khadna object mn properties class
            // 1. بنعمل  Object جديد من class Properties عشان نخزن فيه البيانات اللي هنقراها
            Properties properties = new Properties();
            // 2. بنعرف "شنطة" (Collection) عشان تشيل قائمة بملفات الـ properties اللي هنلاقيها
            Collection<File> propertiesFiles;
            // 3. بنستخدم  FileUtils عشان تدور في فولدر الـ resources وتجيب أي ملف آخره "properties"
            propertiesFiles = FileUtils.listFiles(new File(PROPERTIES_PATH), new String[]{"properties"}, true); //get all files with extension properties
            // 4. بنعمل Loop (دوران) على كل ملف لقيناه في الشنطة عشان نفتحه ونقرأه
            propertiesFiles.forEach(file -> {
                try {
                    // 5. بنفتح الملف ونحمل (load) البيانات اللي جواه (Key و Value) جوه كائن الـ properties
                    properties.load(new FileInputStream(file));
                } catch (IOException ioe) {
                    // لو حصلت مشكلة في ملف واحد، بيطبع رسالة ويكمل لباقي الملفات
                    LogsUtil.error(ioe.getMessage());
                }
                // 6. بنأخذ النسخة اللي قرأناها ونضيفها لخصائص السيستم الحالية عشان "نحدثها"
                properties.putAll(System.getProperties());
                // 6. بنأخذ النسخة اللي قرأناها ونضيفها لخصائص السيستم الحالية عشان "نحدثها"
                System.getProperties().putAll(properties);
            });
            return properties;
        } catch (Exception e) {
            // لو حصلت مشكلة في البحث أو القراءة كلها، بيطبع الخطأ وبيرجع null
            System.out.println("Error loading properties file");
            return null;
        }
    }

    public static String getPropertyValue(String key) {
        try {
            return System.getProperty(key);
        } catch (Exception e) {
          LogsUtil.error(e.getMessage());
            return "";
        }


    }
}
