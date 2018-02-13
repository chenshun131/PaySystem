package wusc.edu.pay.common.utils;

import org.apache.log4j.Logger;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

public class MethodUtils {

    private static Logger logger = Logger.getLogger(MethodUtils.class);

    public static Object copyProperties(Object targetObject, Map<String, Object> srcObject) throws Exception {
        Class srcClass = null;
        Class targetClass = null;
        Class returnType = null;
        Object returnObject = null;
        Method[] srcGetMethods = (Method[]) null;
        Method[] targetGetMethods = (Method[]) null;
        Method targetGetMethod = null;
        Method targetSetMethod = null;
        String srcGetMethodName = null;
        String targetSetMethodName = null;

        if ((srcObject == null) || (targetObject == null)) {
            return null;
        }
        try {
            srcClass = srcObject.getClass();
            targetClass = targetObject.getClass();
            srcGetMethods = srcClass.getMethods();
            targetGetMethods = targetClass.getMethods();
            for (int i = 0; i < targetGetMethods.length; i++) {
                targetSetMethodName = targetGetMethods[i].getName();
                if (("getValidationKey".equals(targetSetMethodName))
                        || (!"get".startsWith(targetSetMethodName))
                        || ("getClass".equals(targetSetMethodName))
                        || ("getServletWrapper".equals(targetSetMethodName))
                        || ("getMultipartRequestHandler".equals(targetSetMethodName))
                        || ("getCallback".equals(targetSetMethodName))) {
                    continue;
                }
                srcObject.get(targetSetMethodName.substring(3).toLowerCase());
                returnType = srcGetMethods[i].getReturnType();
            }

            for (Method srcGetMethod : srcGetMethods) {
                srcGetMethodName = srcGetMethod.getName();
                try {
                    returnType = srcGetMethod.getReturnType();
                    try {
                        returnObject = srcGetMethod.invoke(srcObject);
                    } catch (IllegalArgumentException e1) {
                        continue;
                    }

                    Class targetType = null;
                    try {
                        targetGetMethod = targetClass.getMethod(srcGetMethodName);

                        targetType = targetGetMethod.getReturnType();
                        targetSetMethodName = "set" + srcGetMethodName.substring(3);
                        targetSetMethod = targetClass.getMethod(targetSetMethodName, targetType);
                    } catch (NoSuchMethodException e1) {
                        continue;
                    }

                    if ((targetType.getName() != null) && ("java.util.Calendar".equalsIgnoreCase(targetType.getName()
                    )) && (returnObject != null)) {
                        continue;
                    }

                    if (returnObject == null) {
                        continue;
                    }
                    targetSetMethod.invoke(targetObject, returnObject);
                } catch (IllegalArgumentException | InvocationTargetException e1) {
                    logger.error(e1);
                    throw e1;
                }
            }
        } catch (SecurityException | IllegalAccessException e) {
            logger.error(e);
            throw e;
        }
        return targetObject;
    }

}