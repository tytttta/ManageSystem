package cn.ty.example.utils;



/**
 * 转型操作工具类
 */
public class CastUtil {
    /**
     * 转为String型
     */
    public static String castString(Object object){
        return  CastUtil.castString(object,"");
    }

    /**
     * 转为String型（提供默认值）
     */
    public static String castString(Object obj, String defaultValue){
        return obj!=null? String.valueOf(obj):defaultValue;
    }
    /**
     * 转为double型
     */
    public static double castDouble(Object object){
        return CastUtil.castDouble(object, 0);
    }

    /**
     * 转为double型(提供默认值）
     */
    public static double castDouble(Object object,double defaultValue){
        double value=defaultValue;
        if(object!=null){
            String strValue=castString(object);
            if(StringUtil.isNotEmpty(strValue)){
                try{
                    value=Double.parseDouble(strValue);
                }catch (NumberFormatException e){
                    value=defaultValue;
                }
            }
        }
        return value;
    }

    /**
     * 转为int型
     */
    public static int castInt(Object object){
        return CastUtil.castInt(object, 0);
    }

    /**
     * 转为int型(提供默认值）
     */
    public static int castInt(Object object,int defaultValue){
        int value=defaultValue;
        if(object!=null){
            String strValue=castString(object);
            if(StringUtil.isNotEmpty(strValue)){
                try{
                    value=Integer.parseInt(strValue);
                }catch (NumberFormatException e){
                    value=defaultValue;
                }
            }
        }
        return value;
    }

    /**
     * 转为long型
     */
    public static long castLong(Object object){
        return CastUtil.castInt(object, 0);
    }

    /**
     * 转为double型(提供默认值）
     */
    public static long castInt(Object object,long defaultValue){
        long value=defaultValue;
        if(object!=null){
            String strValue=castString(object);
            if(StringUtil.isNotEmpty(strValue)){
                try{
                    value=Long.parseLong(strValue);
                }catch (NumberFormatException e){
                    value=defaultValue;
                }
            }
        }
        return value;

    }

    /**
     * 转为boolean型
     */
    public static boolean castBoolean(Object object){
        return CastUtil.castBoolean(object, false);
    }

    /**
     * 转为double型(提供默认值）
     */
    public static boolean castBoolean(Object object,boolean defaultValue) {
        boolean value = defaultValue;
        if (object != null) {
            value = Boolean.parseBoolean(castString(value));
        }
        return value;
    }
}
