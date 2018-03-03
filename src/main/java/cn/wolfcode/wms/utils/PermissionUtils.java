package cn.wolfcode.wms.utils;

import java.lang.reflect.Method;

abstract public class PermissionUtils {
    public static String getExpression(Method method) {
        String className = method.getDeclaringClass().getName();
        //获取方法名
        String name = method.getName();
        return className+":"+name;
    }
}
