package cn.ty.example.utils;

import java.sql.Connection;
import java.util.Collection;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;

/**
 * 集合工具类
 */
public final class CollectionUtil {

    /**
     * 判断集合是否为空
     */
    public static boolean isEmpty(Collection<?> connection){
        return CollectionUtils.isEmpty(connection);
    }

    /**
     * 判断集合是否为非空
     */
    public static boolean isNotEmpty(Collection<?> collection){
        return !isEmpty(collection);
    }

    /**
     * 判断Map是否为空
     */
    public static boolean isEmpty(Map<?,?> map){
        return MapUtils.isEmpty(map);
    }

    /**
     * 判断Map是否为非空
     */
    public static boolean isNotEmpty(Map<?,?> map){
        return !isEmpty(map);
    }
}
