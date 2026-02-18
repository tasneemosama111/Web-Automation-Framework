package com.swaglabs.Utils;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class FilesUtils {

    private FilesUtils(){
        super();
    }
    //attachments
    /**
     * وظيفتها: بتدخل الفولدر وتجيب "أحدث ملف" اتعمل له Save أو تعديل
     * بنستخدمها عشان نجيب آخر نسخة من الـ Logs ونحطها في التقرير
     */
 public static File getLatestFiles(String folderPath){
        File folder = new File(folderPath);
        File[] files = folder.listFiles();
        if (files == null || files.length == 0){
            LogsUtil.warn("no files found in directory:" + folderPath);
            return null;
        }
        File latestFile = files[0];
        for (File file : files){
            if (file.lastModified() > latestFile.lastModified()) {
                latestFile = file;
             }
        }
     return  latestFile;

 }





    /**
     * وظيفتها: مسح الفولدر وكل اللي جواه (ملفات أو فولدرات تانية)
     * بنستخدمها عشان ننضف فولدر التقارير القديمة قبل ما نبدأ أي تست جديد
     */
    public static void deleteFiles(File dirPath){
        if (dirPath == null || !dirPath.exists()){
            LogsUtil.warn("directory does not exist:" + dirPath);
            return;
        }
        // . عمل قائمة بكل محتويات الفولدر
        File[] filesList = dirPath.listFiles();
        if (dirPath == null){
            LogsUtil.warn("failed to list files in : " + dirPath);
            return;
        }

        // 3. اللف على كل حاجة جوه الفولدر
        for (File file : filesList){

            if (file.isDirectory()){
                // لو لقيت فولدر.. ادخل جواه ونضفه (الـ Recursion)
                deleteFiles(file);
            } else {
                // لو لقيت ملف.. امسحه فوراً
                try {
                    Files.delete(file.toPath());
                } catch (IOException e){
                    //LogsUtil.error("failed to delete file :"+ file );
                }
            }
        }
    }

    public static void cleanDirectory(File file){
        try {

            FileUtils.deleteQuietly(file);
        } catch (Exception exception){
           // LogsUtil.error(exception.getMessage());
            LogsUtil.info(exception.getMessage());
        }



    }

    public static void renameFile(File oldName, File newName) {

        try {
            // 1. Get the absolute path of the  folder where the old file is located
            File targetFile = oldName.getParentFile().getAbsoluteFile();
            // 2. Create the full path for the new file (Folder + New Name)

            String targetDirectory = targetFile + File.separator + newName ;
            // bn3mel copy ll file el awl bl esm el gded w b3den n-delete the old one
            // 3. Copy the old file and save it with the new name
            FileUtils.copyFile(oldName , new File(targetDirectory));
// 4. Delete the old file after copying is done
            FileUtils.deleteQuietly(oldName);
            // 5. Print a success message with the old and new paths
            LogsUtil.info("Target File Path : \"" + oldName.getPath() + "\" , file was renamed to \"" + newName.getPath() + "\" ");
        }
        catch ( Exception e){
            LogsUtil.error("Filed to rename file : " + e.getMessage());
        }
    }
}
