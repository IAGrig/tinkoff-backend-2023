package edu.hw10.task1;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;

public class RandomObjectGenerator {
    private static final HashMap<Class<?>, Method> CLASS_GENERATOR_MAP = new HashMap<>();

    static {
        try {
            CLASS_GENERATOR_MAP.put(
                int.class,
                ParametersGenerator.class.getMethod("generateInt", Annotation[].class)
            );
            CLASS_GENERATOR_MAP.put(
                long.class,
                ParametersGenerator.class.getMethod("generateLong", Annotation[].class)
            );
            CLASS_GENERATOR_MAP.put(
                double.class,
                ParametersGenerator.class.getMethod("generateDouble", Annotation[].class)
            );
            CLASS_GENERATOR_MAP.put(
                float.class,
                ParametersGenerator.class.getMethod("generateFloat", Annotation[].class)
            );
            CLASS_GENERATOR_MAP.put(
                String.class,
                ParametersGenerator.class.getMethod("generateString", Annotation[].class)
            );
            CLASS_GENERATOR_MAP.put(
                boolean.class,
                ParametersGenerator.class.getMethod("generateBoolean", Annotation[].class)
            );
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> Object nextObject(Class<T> classT, String fabricMethodName) {
        if (fabricMethodName != null) {
            Method fabricMethod = findFabricMethod(classT, fabricMethodName);
            if (fabricMethod == null) {
                throw new IllegalArgumentException("No such fabric method");
            }
            Object[] parametersValues = getParametersValues(fabricMethod.getParameters());
            return getInstance(fabricMethod, parametersValues);
        }

        Constructor<T> constructor = null;
        constructor = (Constructor<T>) classT.getConstructors()[0];
        Object[] parametersValues = getParametersValues(constructor.getParameters());
        try {
            return constructor.newInstance(parametersValues);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> Object nextObject(Class<T> classT) {
        return nextObject(classT, null);
    }

    private Object[] getParametersValues(Parameter[] parameters) {
        Object[] values = new Object[parameters.length];
        for (int i = 0; i < parameters.length; i++) {
            Class<?> classT = parameters[i].getType();
            Annotation[] annotations = parameters[i].getAnnotations();
            Method method = CLASS_GENERATOR_MAP.get(classT);
            try {
                values[i] = method.invoke(null, (Object) annotations);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }
        return values;
    }

    private Method findFabricMethod(Class<?> classT, String methodName) {
        Method[] methods = classT.getMethods();
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                return method;
            }
        }
        return null;
    }

    private <T> T getInstance(Method method, Object[] arguments) {
        T instance;
        try {
            if (method.isAccessible()) {
                instance = (T) method.invoke(arguments);
            } else {
                method.setAccessible(true);
                instance = (T) method.invoke(arguments);
                method.setAccessible(false);
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        return instance;
    }
}
