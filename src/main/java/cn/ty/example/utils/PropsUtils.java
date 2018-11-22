package cn.ty.example.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 属性工具类
 */
public class PropsUtils {
    private static final Logger LOGGER= LoggerFactory.getLogger(PropsUtils.class);

    /**
     * 加载属性配置文件
     * @param fileName
     * @return
     */
    public static Properties loadProps(String fileName){
        Properties prop =null;
        InputStream inputStream=null;
        try{
            inputStream=Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
            if(inputStream==null){
                throw new FileNotFoundException(fileName+"file is not found");
            }
            prop =new Properties();
            prop.load(inputStream);
        }catch (IOException e){
            LOGGER.error("load properties file failure", e);
        }finally {
            if(inputStream != null){
                try{
                    inputStream.close();
                }catch (IOException e){
                    LOGGER.error("close input stream failure", e);
                }
            }
        }
        return prop;
    }

    /**
     * 获取字符型属性
     */

    public static String getString(Properties prop, String key){
        return getString(prop, key, "");
    }


    /**
     * 获取字符型属性（可指定默认值）
     */
    public static String getString(Properties properties, String key, String value){
        String defaultValue = value;
        if(properties.contains(key)){
            defaultValue=properties.getProperty(key);
        }
        return value;
    }

    /**
     * 获取数值型属性（默认值为0）
     */
    public static int getInt(Properties prop, String key){
        return getInt(prop, key,0);
    }

    /**
     * 获取数值型属性（可以指定默认值）
     */
    public static int getInt(Properties properties, String key, int defaultValue){
        int value=defaultValue;
        if(properties.contains(key)){
            value=CastUtil.castInt(properties.getProperty(key));
        }
        return value;
    }

    /**
     * 获取布尔型属性（默认值为false）
     */
    public static boolean getBoolean(Properties prop, String key){
        return getBoolean(prop, key, false);
    }
    /**
     * 获取布尔型属性（可指定默认值）
     */
    public static boolean getBoolean(Properties properties, String key, boolean defaultValue){
        boolean value=defaultValue;
        if(properties.contains(key)){
            value=CastUtil.castBoolean(properties.getProperty(key));
        }
        return value;

    }


}
