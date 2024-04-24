package utils;

import java.io.File;

public class FileReader {

    private static final ClassLoader classLoader = EntityReader.class.getClassLoader();

    public static File getFileByResourcePath(String filePath) {

        try {
            return new File(classLoader.getResource(filePath).getFile());
        } catch (Exception e) {
            throw new RuntimeException("Can't load the file. Check file path. \n" + e);
        }
    }
}
